package com.clic.ccdbaas.utils;

import java.util.ArrayList;
import java.util.List;

public class IpSegmentUtils {

    /**
     * 判断是否在该网段中
     *
     * @param ipSegment 子网范围 x.x.x.x/xx 形式
     */
    public static boolean isIpAddressInIpSegment(String ipAddress, String ipSegment) {
        String[] networkList = ipAddress.split("\\.");
        int ipNum = (Integer.parseInt(networkList[0]) << 24)
                | (Integer.parseInt(networkList[1]) << 16)
                | (Integer.parseInt(networkList[2]) << 8)
                | Integer.parseInt(networkList[3]);

        // 拿到主机数
        int type = 0;
        if (!ipSegment.contains("/")) {
            return false;
        }
        if (ipSegment.replaceAll(".*/", "").length() > 2) {
            type = getNetMask(ipSegment.replaceAll(".*/", ""));
        } else {
            type = Integer.parseInt(ipSegment.replaceAll(".*/", ""));
        }
        int ipCount = 0xFFFFFFFF << (32 - type);

        String maskIp = ipSegment.replaceAll("/.*", "").trim();
        String[] maskIps = maskIp.split("\\.");

        int cidrIpNum = (Integer.parseInt(maskIps[0]) << 24)
                | (Integer.parseInt(maskIps[1]) << 16)
                | (Integer.parseInt(maskIps[2]) << 8)
                | Integer.parseInt(maskIps[3]);

        return (ipNum & ipCount) == (cidrIpNum & ipCount);
    }

    /**
     * 利用正则表达式判断字符是否为IP
     */
    public static boolean isCorrectIp(String mainIp) {
        String ipRegex = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";    //IP地址的正则表达式
        if (mainIp.matches(ipRegex)) {
            String[] ipArray = mainIp.split("\\.");
            for (int i = 0; i < ipArray.length; i++) {
                int number = Integer.parseInt(ipArray[i]);
                if (number < 0 || number > 255) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据子网掩码转换为掩码位 如 255.255.255.252转换为掩码位 为 30
     *
     * @param netmarks
     * @return
     */
    public static int getNetMask(String netmarks) {
        StringBuilder sb;
        String str;
        int inetmask = 0;
        int count = 0;
        String[] ipList = netmarks.split("\\.");
        for (int n = 0; n < ipList.length; n++) {
            sb = toBin(Integer.parseInt(ipList[n]));
            str = sb.reverse().toString();
            count = 0;
            for (int i = 0; i < str.length(); i++) {
                i = str.indexOf('1', i);
                if (i == -1) {
                    break;
                }
                count++;
            }
            inetmask += count;
        }
        return inetmask;
    }

    private static StringBuilder toBin(int x) {
        StringBuilder result = new StringBuilder();
        result.append(x % 2);
        x /= 2;
        while (x > 0) {
            result.append(x % 2);
            x /= 2;
        }
        return result;
    }

    /**
     * 查找两个IP地址之间的IP ipv4
     *
     * @param startIp
     * @param endIp
     * @return
     */
    public static List<String> findIPsForIpv4(String startIp, String endIp) {
        long startNumber = ipv4ToNumber(startIp) + 1;
        long endNumber = ipv4ToNumber(endIp) + 1;
        List<String> ips = new ArrayList<String>();

        for (long i = startNumber; i < endNumber; i++) {
            ips.add(numberToIpv4(i));
        }
        return ips;
    }

    /**
     * 数字转换成IPv4地址
     *
     * @param number
     * @return
     */
    private static String numberToIpv4(long number) {
        String ip = "";
        List<String> ips = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            ips.add(String.valueOf(number % 256));
            number = number >> 8;
        }
        for (int i = ips.size() - 1; i >= 0; i--) {
            ip = ip.concat(ips.get(i));
            if (i > 0) {
                ip = ip.concat(".");
            }
        }
        return ip;
    }

    /**
     * IPv4地址转换成数字
     *
     * @param ip
     */
    private static long ipv4ToNumber(String ip) {
        long rs = 0;
        if (ip == null || ip.isEmpty()) {
            return rs;
        }
        String[] ips = ip.split("\\.");
        for (int i = 0; i < ips.length; i++) {
            rs += Integer.parseInt(ips[i]) * Math.pow(256, (3 - i));
        }
        return rs;
    }

    /**
     * 判断主机ip是否为测试机(10.18 10.30 10.38)
     *
     * @param mainIp
     * @return
     */
    public static boolean isTestMachineIp(String mainIp) {
        if (mainIp.contains(";")) {
            String[] mainIpList = mainIp.split(";");
            mainIp = mainIpList[0];
        }
        String[] temp = mainIp.split("\\.");
        if (!temp[0].equals("10")) {
            return false;
        } else {
            return temp[1].equals("18") || temp[1].equals("30") || temp[1].equals("38");
        }
    }
}
