package com.clic.ccdbaas.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ping工具类
 */
public class PingUtil {

    /**
     * 是否能ping通
     *
     * @param ipAddress ip
     * @return 是否成功
     * @throws Exception 异常
     */
    public static boolean ifPing(String ipAddress) throws Exception {
        int timeOut = 3000;  //超时应该在3钞以上
        boolean status = false;
        status = InetAddress.getByName(ipAddress).isReachable(timeOut);

        return status;
    }

    /**
     * ping，并输出结果
     *
     * @param ipAddress ip
     * @throws Exception 异常
     */
    public static void ping(String ipAddress) throws Exception {
        String line = null;
        Process pro = Runtime.getRuntime().exec("ping " + ipAddress);
        BufferedReader buf = new BufferedReader(new InputStreamReader(pro.getInputStream(), "GBK"));
        while ((line = buf.readLine()) != null)
            System.out.println(line);
    }

    /**
     * 是否能ping通，并输出结果
     *
     * @param ipAddress ip
     * @param pingTimes 尝试次数
     * @param timeOut   超时时长
     * @return 是否成功
     */
    public static boolean ifPing(String ipAddress, int pingTimes, int timeOut) {
        BufferedReader in = null;
        Runtime r = Runtime.getRuntime();  // 将要执行的ping命令,此命令是windows格式的命令
        String pingCommand = "ping " + ipAddress + " -n " + pingTimes + " -w " + timeOut;
        try {
            System.out.println(pingCommand);
            Process p = r.exec(pingCommand);
            if (p == null) {
                return false;
            }
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            int connectedCount = 0;
            String line = null;
            while ((line = in.readLine()) != null) {
                connectedCount += getCheckResult(line);
            }   // 如果出现类似=23ms TTL=62这样的字样,出现的次数=测试次数则返回真
            return connectedCount == pingTimes;
        } catch (Exception ex) {
            ex.printStackTrace();   // 出现异常则返回假
            return false;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 若line含有=18ms TTL=16字样,说明已经ping通,返回1,否則返回0.
     *
     * @param line line
     * @return 1：能ping通；0：ping不通
     */
    private static int getCheckResult(String line) {
        Pattern pattern = Pattern.compile("(\\d+ms)(\\s+)(TTL=\\d+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            return 1;
        }
        return 0;
    }
}
