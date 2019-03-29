package others;

/**
 * @author gavin
 * @date 2018/12/21 9:35
 */

import java.io.*;
import java.nio.charset.Charset;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoteShellExecutor {

    private static final Logger logger = LoggerFactory.getLogger(RemoteShellExecutor.class);
    private Connection conn;
    private String ip;
    private String osUsername;
    private String password;
    private static final int TIME_OUT = 1000 * 5 * 60;

    public RemoteShellExecutor(String ip, String usr, String pasword) {
        this.ip = ip;
        this.osUsername = usr;
        this.password = pasword;
    }

    private boolean login() throws IOException {
        conn = new Connection(ip);
        conn.connect();
        return conn.authenticateWithPassword(osUsername, password);
    }

    /**
     * 执行脚本
     *
     * @param cmds
     * @return
     * @throws Exception
     */
    public int exec(String cmds) throws Exception {
        BufferedReader stdOut = null;
        BufferedReader stdErr = null;
        String outStr = "";
        String outErr = "";
        int ret = -1;
        try {
            if (login()) {
                // Open a new {@link Session} on this connection
                Session session = conn.openSession();
                // Execute a command on the remote machine.
                session.execCommand(cmds);
                stdOut = new BufferedReader(new InputStreamReader(new StreamGobbler(session.getStdout())));
                stdErr = new BufferedReader(new InputStreamReader(new StreamGobbler(session.getStderr())));
                while ((outStr = stdOut.readLine()) != null) {
                    logger.info("outStr=" + outStr);
                }
                while ((outErr = stdErr.readLine()) != null) {
                    logger.info("outErr=" + outErr);
                }
                session.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);
                ret = session.getExitStatus();
            } else {
                throw new Exception("登录远程机器失败" + ip); // 自定义异常类
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
            IOUtils.closeQuietly(stdOut);
            IOUtils.closeQuietly(stdErr);
        }
        return ret;
    }

    public static void main(String args[]) throws Exception {
        RemoteShellExecutor executor = new RemoteShellExecutor("192.168.80.140", "root", "123456");
        Elasticdump elasticdump = new Elasticdump();
        elasticdump.setInputIp("http://10.221.128.136:9200");
        elasticdump.setInputIndex("log-2018.12.21");
        elasticdump.setOutputFile("/home/gavin/data/" + elasticdump.getInputIndex() + ".json");
        elasticdump.setType("data");
        StringBuilder sb = new StringBuilder();
        sb.append("/home/gavin/data/testbash.sh").append(" ").append(elasticdump.getInputIp())
                .append(" ").append(elasticdump.getInputIndex())
                .append(" ").append(elasticdump.getOutputFile())
                .append(" ").append(elasticdump.getType());
        System.out.println(sb.toString());
        executor.exec(sb.toString());
    }
}

class Elasticdump {
    private String inputIp;
    private String inputIndex;
    private String inputFile;
    private String outputIp;
    private String outputIndex;
    private String outputFile;
    private String type;

    public String getInputIp() {
        return inputIp;
    }

    public void setInputIp(String inputIp) {
        this.inputIp = inputIp;
    }

    public String getInputIndex() {
        return inputIndex;
    }

    public void setInputIndex(String inputIndex) {
        this.inputIndex = inputIndex;
    }

    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public String getOutputIp() {
        return outputIp;
    }

    public void setOutputIp(String outputIp) {
        this.outputIp = outputIp;
    }

    public String getOutputIndex() {
        return outputIndex;
    }

    public void setOutputIndex(String outputIndex) {
        this.outputIndex = outputIndex;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}