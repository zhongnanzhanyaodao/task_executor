package com.kingdee.internet.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisManager {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public <T> T rightPop(final String key, final Class<T> type) {
        return redisTemplate.execute(new RedisCallback<T>() {
            @Override
            public T doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] bytes = connection.rPop(RedisManager.this.serializeString(key));
                return MessagePackUtils.unpack(bytes, type);
            }
        });
    }

    public <T> void leftPush(final String key, final T value) {
        leftPush(key, value, 0, null);
    }

    public <T> boolean leftPush(final String key, final T value, final long timeout, final TimeUnit unit) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] rawKey = RedisManager.this.serializeString(key);
                connection.lPush(rawKey, MessagePackUtils.pack(value));
                if(timeout > 0) {
                    connection.expire(rawKey, unit.toSeconds(timeout));
                }
                return true;
            }
        });
    }

    public boolean expire(final String key, final long timeout, TimeUnit unit) {
        return redisTemplate.boundListOps(key).expire(timeout, unit);
    }

    private byte[] serializeString(String source) {
        return redisTemplate.getStringSerializer().serialize(source);
    }
}
