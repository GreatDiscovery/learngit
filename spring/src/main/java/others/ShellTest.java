package others;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * java调用本地脚本
 */
public class ShellTest {

    public static void main(String[] args) {
        InputStreamReader stdISR = null;
        InputStreamReader errISR = null;
        Process process = null;
        String command = "/root/testJava/testbash.sh";
        try {
            // 设置权限
            ProcessBuilder builder = new ProcessBuilder("/bin/chmod", "755", command);
            Process process1 = builder.start();
            process1.waitFor();
            ProcessBuilder builder2 = new ProcessBuilder(command);
            process = builder2.start();
            int runningStatus = process.waitFor();
            /// 直接获取
//            process = Runtime.getRuntime().exec(command);
//            int exitValue = process.waitFor();

            String line = null;
            // 防止缓冲区满导致java挂起
            stdISR = new InputStreamReader(process.getInputStream());
            BufferedReader stdBR = new BufferedReader(stdISR);
            while ((line = stdBR.readLine()) != null) {
                System.out.println("STD line:" + line);
            }

            errISR = new InputStreamReader(process.getErrorStream());
            BufferedReader errBR = new BufferedReader(errISR);
            while ((line = errBR.readLine()) != null) {
                System.out.println("ERR line:" + line);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stdISR != null) {
                    stdISR.close();
                }
                if (errISR != null) {
                    errISR.close();
                }
                if (process != null) {
                    process.destroy();
                }
            } catch (IOException e) {
                System.out.println("正式执行命令：" + command + "有IO异常");
            }
        }
    }
}
