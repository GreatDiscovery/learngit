package org.elasticsearch.node;

import org.elasticsearch.common.inject.Injector;

import java.io.Closeable;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;

public abstract class Node implements Closeable {
    private Injector injector;
    public Node start() {
        injector.getInstance(MappingUpdateAction.class).setClient(client);
        injector.getInstance(IndicesService.class).start();
        injector.getInstance(IndicesClusterStateService.class).start();
        injector.getInstance(SnapshotsService.class).start();
        injector.getInstance(SnapshotShardsService.class).start();
        injector.getInstance(RoutingService.class).start();
        injector.getInstance(SearchService.class).start();
        nodeService.getMonitorService().start();
        // 建立集群信息
        final ClusterService clusterService = injector.getInstance(ClusterService.class);
        final NodeConnectionService nodeConnectionService = injector.getInstance(NodeConnnectionService.class).start();
        clusterService.set(nodeConnectionService);
        injector.getInstance(ResourceWatcherService.class).start();
        injector.getInstance(GatewayService.class).start();
        Discovery discovery = injector.getInstance(Discovery.class);
        clusterService.getMasterService().setClusterStatePublisher(discovery::publish);
        TransportService transportService = injector.getInstance(TransportService.class);
        transportService.getTaskManager().setTaskResultsService(injector.getInstance(TaskResultsService.class));
        transportService.start();
        // 恢复磁盘上的原始信息
        final MetaData onDiskMetadata;
        if (DiscoveryNode.isMasterNode(settings) || DiscoveryNode.isDataNode(settings)) {
            onDiskMetadata = injector.getInstance(GatewayMetaState.class).loadMetaState();
        } else {
            onDiskMetadata = MetaData.EMPTY_META_DATA;
        }
        assert onDiskMetadata != null : "metadata is null but shouldn't";
        validateNodeBeforeAcceptingRequests(new BootstrapContext(settings, onDiskMetadata), transportService.boundAddress(), pluginService
        .filterPlugins(Plugin.class).stream().flatMap(p -> p.getBootstrapChecks().stream)).collect(Collections.toList()));
        clusterService.addStateApplier(transportService.getTaskManager());
        // 在传输模块开始之后，
        discovery.start();
        clusterService.start();
        transportService.acceptIncomingRequest();
        discovery.startInitialJoin();
        final TimeValue initialStateTimeout = DiscoverySettings.INITIAL_STATE_TIMEOUT_SETTINGS.get(settings);
        if (initialStateTimeout.millis() > 0) {
            final ThreadPool thread = injector.getInstance(ThreadPool.class);
            ClusterState clusterState = clusterService.state();
            ClusterStateObserver observer = new ClusterStateObserver(clusterState, clusterService, null);
            // 判断集群master是否加入
            if (clusterState.nodes().getMasterNodeId() == null) {
                logger.debug("waiting to join the cluster. timeout [{}]", initialStateTimeout);
                final CountDownLatch latch = new CountDownLatch(1);
                observer.waitForNextChange(new ClusterStateObserver.Listener() {
                    public void onNewClusterState(ClusterState state) {
                        latch.countDown();
                    }
                }, state -> state.nodes().getMasterNodeId() != null, initialStateTimeout);
                latch.await();
            }
        }

        if (NetworkModule.HTTP_ENABLED.get(settings)) {
            injector.getInstance(HttpServerTransport.class).start();
        }

        if (WRITE_PORTS_FILE_SETTING.get(settings)) {
            if (NetworkModule.HTTP_ENABLE.get(settings)) {
                HttpServerTransport http = injector.getInstance(HttpServerTransport.class);
            }
            TransportService transport = injector.getInstance(Transportservice.class);
        }
        System.out.println("started");
        pluginService.filterPlugins(ClusterPlugin.class).forEach(ClusterPlugin::onNodeStarted);

    }
}
