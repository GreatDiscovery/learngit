package com.example.elasticsearch.rest;

import com.example.elasticsearch.AbstractBenchmark;
import com.example.elasticsearch.client.Request;
import com.example.elasticsearch.client.Response;
import com.example.elasticsearch.client.RestClient;
import com.example.elasticsearch.ops.BulkRequestExecutor;

import java.util.List;

public class RestClientBenchmark extends AbstractBenchmark<RestClient> {
    @Override
    protected RestClient client(String benchmarkTargetHost) {
        return RestClient;
    }

    @Override
    protected BulkRequestExecutor bulkRequestExecutor(RestClient client, String index, String type) {
        return null;
    }

    private static final class RestBulkRequestExecutor implements BulkRequestExecutor {
        private final RestClient client;
        private final String actionMetaData;

        public RestBulkRequestExecutor(RestClient client, String actionMetaData) {
            this.client = client;
            this.actionMetaData = actionMetaData;
        }

        @Override
        public boolean bulkIndex(List<String> bulkData) {
            StringBuilder bulkRequestBody = new StringBuilder();
            for (String bulkItem : bulkData) {
                bulkRequestBody.append(actionMetaData);
                bulkRequestBody.append(bulkItem);
                bulkRequestBody.append("\n");
            }

            Request request = new Request("POST", "/geonames/type/_bulk");
            request.setJsonEntity(bulkRequestBody.toString());
            Response response = client.performRequest(request);
            return false;
        }
    }
}
