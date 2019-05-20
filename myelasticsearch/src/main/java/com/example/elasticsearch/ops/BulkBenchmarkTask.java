package com.example.elasticsearch.ops;

import com.example.elasticsearch.BenchmarkTask;
import com.sun.javafx.scene.shape.PathUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BulkBenchmarkTask implements BenchmarkTask {
    private final BulkRequestExecutor requestExecutor;
    private final String indexFilePath;
    private final int warmupIterations;
    private final int measurementIterations;
    private final int bulkSize;
    private LoadGenerator generator;
    private ExecutorService executorService;

    public BulkBenchmarkTask(BulkRequestExecutor requestExecutor, String indexFilePath, int warmupIterations, int measurementIterations, int bulkSize) {
        this.requestExecutor = requestExecutor;
        this.indexFilePath = indexFilePath;
        this.warmupIterations = warmupIterations;
        this.measurementIterations = measurementIterations;
        this.bulkSize = bulkSize;
    }
    @Override
    public void setUp(SampleRecorder sampleRecorder) {
        BlockingQueue<List<String>> bulkQueue = new ArrayBlockingQueue<>(256);
        BulkIndexer runner = new BulkIndexer(bulkQueue, warmupIterations, measurementIterations, sampleRecorder, requestExecutor);
        executorService = Executors.newSingleThreadExecutor((r -> new Thread(r, "bulk-index-runner")));
        executorService.submit(runner);
        generator = new LoadGenerator(PathUtils.get(indexFilePath), bulkQueue, bulkSize);
    }
    @Override
    public void run() {

    }

    @Override
    public void tearDown() {

    }

    private static final class LoadGenerator {
        private final Path bulkDataFile;
        private final BlockingQueue<List<String>> bulkQueue;
        private final int bulkSize;

        public LoadGenerator(Path bulkDataFile, BlockingQueue<List<String>> bulkQueue, int bulkSize) {
            this.bulkDataFile = bulkDataFile;
            this.bulkQueue = bulkQueue;
            this.bulkSize = bulkSize;
        }

        public void execute() {
            try {
                BufferedReader reader = Files.newBufferedReader(bulkDataFile, StandardCharsets.UTF_8);
                String line;
                int bulkIndex = 0;
                List<String> bulkData = new ArrayList<>(bulkSize);
                while ((line = reader.readLine()) != null) {
                    if (bulkIndex == bulkSize) {
                        sendBulk(bulkData);
                        bulkData = new ArrayList(bulkSize);
                        bulkIndex = 0;
                    }
                    bulkData.add(line);
                    bulkIndex++;
                }
                if (bulkIndex > 0) {
                    sendBulk(bulkData);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private void sendBulk(List<String> bulkData) throws InterruptedException {
            bulkQueue.put(bulkData);
        }
    }
}
