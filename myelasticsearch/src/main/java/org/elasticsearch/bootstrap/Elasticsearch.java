package org.elasticsearch.bootstrap;


import org.elasticsearch.cli.Terminal;

import java.nio.file.Path;
import java.security.Permission;

public class Elasticsearch {

    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager() {
            @Override
            public void checkPermission(Permission perm) {

            }
        });
        final Elasticsearch elasticsearch = new Elasticsearch();
        int status = main(args, elasticsearch, Terminal.DEFAULT);
        if (status != ExitCodes.OK) {
            exit(status);
        }
    }

    static int main(final String[] args, final Elasticsearch elasticsearch, final Terminal terminal) {
        return elasticsearch.main(args, terminal);
    }

    protected void execute(Terminal terminal, Environment env) {
        // 打印版本等信息
        init();
    }

    void init(final boolean daemonize, final Path pidFile, final boolean quiet, Environment initialEnv) {
        Bootstrap.init(!daemonize, pidFile, quiet, initialEnv);
    }
}
