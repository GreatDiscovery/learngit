package com.example.elasticsearch.ops;

import java.util.List;

public interface BulkRequestExecutor {
    boolean bulkIndex(List<String> bulkData);
}
