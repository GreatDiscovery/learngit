package org.elasticsearch.cli;

import java.util.HashMap;
import java.util.Map;

public abstract class EnvironmentAwareCommand extends Command {
    @Override
    protected void execute(Terminal terminal) throws Exception {
        final Map<String, String> settings = new HashMap<>();
        putSystemPropertyIfSettingIsMissing(settings, "path.data", "es.path.data");
        execute(terminal, createEnv(terminal, settings));
    }

    private static void putSystemPropertyIfSettingIsMissing(final Map<String, String> settings, final String setting, final String keys) {}
}
