package com.example.elasticsearch;

public class BenchmarkMain {
    public static void main(String[] args) {
        AbstractBenchmark<?> benchmark = null;
        benchmark.run(args);
    }
}
