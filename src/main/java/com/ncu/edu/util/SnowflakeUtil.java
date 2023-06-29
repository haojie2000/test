package com.ncu.edu.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author tan
 * @Date 2021/10/16 16:23
 */
@Component
public class SnowflakeUtil {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private long workerId = 0;//为终端ID
    private long dataCenterId = 1;//数据中心ID
    private Snowflake snowflake = IdUtil.createSnowflake(workerId,dataCenterId);
    @PostConstruct
    public void init(){
        workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
    }
    public synchronized String snowflakeId(){
        return String.valueOf(snowflake.nextId());
    }
    public synchronized long snowflakeId(long workerId,long dataCenterId){
        Snowflake snowflake = IdUtil.createSnowflake(workerId, dataCenterId);
        return snowflake.nextId();
    }
}

