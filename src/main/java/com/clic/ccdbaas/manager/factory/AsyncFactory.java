package com.clic.ccdbaas.manager.factory;

import com.clic.ccdbaas.entity.LocalOperLog;
import com.clic.ccdbaas.service.LocalOperLogService;
import com.clic.ccdbaas.utils.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * @author chenjianhua
 * @version 1.0
 * @date 2023/4/25 13:35
 * @email chenjianhua@bmsoft.com.cn
 */
public class AsyncFactory
{

    /**
     * 操作日志记录
     *
     * @return 任务task
     */
    public static TimerTask recordOper(final String resId,final String resType,final String resName,final String userName,final int businessType,final String attribute,final Object originalValue,final Object resValue,final int status)
    {
        return new TimerTask()
        {
            @Override
            public void run()
            {
                // 远程查询操作地点
                LocalOperLog operLog = new LocalOperLog();
                operLog.setResId(resId);
                operLog.setOperName(userName);
                operLog.setAttribute(attribute);
                operLog.setBusinessType(businessType);
                operLog.setOriginVal(originalValue);
                operLog.setResVal(resValue);
                operLog.setResType(resType);
                operLog.setResName(resName);
                operLog.setStatus(status);
                //resId不可能变  排除
                if(!"resId".equals(attribute)){
                    SpringUtils.getBean(LocalOperLogService.class).insertOperlog(operLog);
                }

            }
        };
    }
}
