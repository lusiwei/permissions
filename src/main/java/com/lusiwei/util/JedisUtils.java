package com.lusiwei.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: lusiwei
 * @Date: 2018/11/14 10:17
 * @Description:
 */
@Component
@Slf4j
public class JedisUtils {
    private final ShardedJedisPoolUtil shardedJedisPoolUtil;

    @Autowired
    public JedisUtils(ShardedJedisPoolUtil shardedJedisPoolUtil) {
        this.shardedJedisPoolUtil = shardedJedisPoolUtil;
    }

//    public void setUserAcl(Integer userId,Set<Integer> aclIds){
//        //获得redis连接
//        ShardedJedis resource = shardedJedisPoolUtil.getResource();
//        //1.序列化为字符串
//        //json
//        try {
//            resource.setex(Constant.USER_ACL_PRE + userId, 600, JsonUtil.object2string(aclIds));
//        }catch (Exception e){
//            log.info("缓存用户权限异常.....e:{}",e);
//        }finally {
//            shardedJedisPoolUtil.closeJedis(resource);
//        }
//    }
//    public Set<Integer> getUserAcl(Integer userId){
//        //获得redis连接
//        ShardedJedis resource = shardedJedisPoolUtil.getResource();
//
//        String s = resource.get(Constant.USER_ACL_PRE + userId);
//        if(s==null){
//            return null;
//        }
//        shardedJedisPoolUtil.closeJedis(resource);//应该写在finally里面
//        return JsonUtil.string2object(s, new TypeReference<Set<Integer>>() {
//            @Override
//            public int compareTo(TypeReference<Set<Integer>> o) {
//                return super.compareTo(o);
//            }
//        });
//
//    }
}
