package org.elasticsearch.cli;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class Command implements Closeable {

    private final String description;
    private final Runnable beforeMain;

    public Command(String description, Runnable beforeMain) {
        this.description = description;
        this.beforeMain = beforeMain;
    }

    private Thread shutdownHookThread;

    public final int main(String[] args, Terminal terminal) throws Exception {
        if (addShutdownHook()) {
            shutdownHookThread = new Thread(() -> {
                try {
                    this.close();
                } catch (final IOException e) {
                    try (
                            StringWriter sw = new StringWriter();
                            PrintWriter pw = new PrintWriter(sw)) {
                        e.printStackTrace(pw);
                        terminal.println(sw.toString());
                    } catch (final IOException impossible) {
                        throw new AssertionError(impossible);
                    }
                    ;
                }
            });
            Runtime.getRuntime().addShutdownHook(shutdownHookThread);
        }

        beforeMain.run();

        mainWithoutErrorHandling(args, terminal);


    }

    void mainWithoutErrorHandling(String[] args, Terminal terminal) throws Exception {
        // 缓存所有options参数
        execute(terminal);
    }

    protected abstract void execute(Terminal terminal) throws Exception;

    @Override
    public void close() throws IOException {

    }

    protected boolean addShutdownHook() {
        return true;
    }
}
