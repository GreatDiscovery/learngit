package com.example.elasticsearch;

import com.example.elasticsearch.ops.BulkBenchmarkTask;

import java.io.Closeable;

public abstract class AbstractBenchmark<T extends Closeable> {
    protected abstract T client(String benchmarkTargetHost);

    protected abstract BulkRequestExecutor bulkRequestExecutor(T client, String index, String type);

    public final void run(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("usage: [search|bulk]");
            System.exit(1);
        }
        switch (args[0]) {
            case "search":
                runSearchBenchmark(args);
                break;
            case "bulk":
                runBulkIndexBenchmark(args);
                break;
            default:
                System.err.println("Unknown benchmark type [" + args[0] + "]");
                System.exit(1);
        }
    }

    private void runSearchBenchmark(String[] args) {

    }

    private void runBulkIndexBenchmark(String[] args) throws Exception {
        if (args.length != 7) {
            System.err.println("usage: 'bulk' benchmarkTargetHostIp indexFilePath indexName typeName numberOfDocuments bulkSize");
            System.exit(1);
        }
        String benchmarkTargetHost = args[1];
        String indexFilePath = args[2];
        String indexName = args[3];
        String typeName = args[4];
        int totalDocs = Integer.valueOf(args[5]);
        int bulkSize = Integer.valueOf(args[6]);
        int totalIterationCount = (int) Math.floor(totalDocs / bulkSize);
        int warmupIterations = (int) (0.4d * totalIterationCount);
        int iterations = totalIterationCount - warmupIterations;

        T client = client(benchmarkTargetHost);
        BenchmarkRunner benchmark = new BenchmarkRunner(warmupIterations, iterations, new BulkBenchmarkTask(
                bulkRequestExecutor(client, indexName, typeName), indexFilePath, warmupIterations, iterations, bulkSize
        ));

        try {
            runTrials(() -> {
                runGc();
                benchmark.run();
            });
        } finally {
            client.close();
        }
    }

    private void runTrials(Runnable runner) {
        int totalWarmupTrialRuns = 1;
        for (int run = 1; run <= totalWarmupTrialRuns; run++) {
            System.out.println("======================");
            System.out.println(" Warmup trial run " + run + "/" + totalWarmupTrialRuns);
            System.out.println("======================");
            runner.run();
        }

        int totalTrialRuns = 5;
        for (int run = 1; run <= totalTrialRuns; run++) {
            System.out.println("================");
            System.out.println(" Trial run " + run + "/" + totalTrialRuns);
            System.out.println("================");

            runner.run();
        }
    }

    private void runGc() {
//        long previousCollections = getTotalGcCount();
    }
}
