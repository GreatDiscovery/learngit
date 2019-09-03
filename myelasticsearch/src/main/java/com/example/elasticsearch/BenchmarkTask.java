package com.example.elasticsearch;

public interface BenchmarkTask {
    void setUp(SampleRecorder sampleRecorder);

    void run();

    void tearDown();
}
