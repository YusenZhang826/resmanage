package com.clic.ccdbaas.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author chenjianhua
 * @version 1.0
 * @date 2023/8/23 10:58
 * @email chenjianhua@bmsoft.com.cn
 */
public class MathUtils {

    /**
     * 求环比,计算公式=(本期数据-上期数据)/上期数据 *100%
     * @param bCount 本期数据
     * @param sCount 上期数据
     * @return
     */
    public static String d2dRatio(Long bCount,Long sCount){
        //差
        Long differ = bCount - sCount;
        //除数
        BigDecimal denominator = BigDecimal.valueOf(differ);
        //被除数
        BigDecimal numerator = BigDecimal.valueOf(sCount);
        //百分比
        String ratio;
        if(sCount==0){//被除数为零，无意义
            ratio = new DecimalFormat("#########0.00%").format(denominator);
        }else{
            //商
            BigDecimal quotient = denominator.divide(numerator,6, BigDecimal.ROUND_HALF_DOWN);
            ratio = new DecimalFormat("#########0.00%").format(quotient);
        }
        return ratio;
    }

    /**
     * 求环比,计算公式=(本期数据-上期数据)/上期数据 *100%
     * @param bCount 本期数据
     * @param sCount 上期数据
     * @return
     */
    public static String d2dRatio(BigDecimal bCount, BigDecimal sCount){
        //差
        BigDecimal differ = bCount.subtract(sCount);
        //除数
        BigDecimal denominator = differ;
        //被除数
        BigDecimal numerator = sCount;
        //百分比
        String ratio;
        if(sCount.compareTo(new BigDecimal(0))==0){//被除数为零，无意义
            ratio = new DecimalFormat("#########0.00%").format(denominator);
        }else{
            //商
            BigDecimal quotient = denominator.divide(numerator,6, BigDecimal.ROUND_HALF_DOWN);
            int i = quotient.compareTo(BigDecimal.ZERO);
            if(i<0){
                ratio = new DecimalFormat("#########0.00%").format(quotient);
            }else{
                ratio = "+"+new DecimalFormat("#########0.00%").format(quotient);
            }

        }
        return ratio;
    }
}
