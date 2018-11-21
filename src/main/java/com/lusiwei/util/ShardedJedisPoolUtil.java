package com.lusiwei.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Component
@Slf4j
public class ShardedJedisPoolUtil {

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    public ShardedJedis getResource(){
        return shardedJedisPool.getResource();
    }


    public void closeJedis(ShardedJedis shardedJedis){
        try {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }catch (Exception e){
            log.info("jedis close exception,e:{}",e);
        }
    }

}
