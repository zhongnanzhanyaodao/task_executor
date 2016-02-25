package com.kingdee.internet.util;

import org.msgpack.MessagePack;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.IOException;

public class MessagePackUtils {
    private MessagePackUtils() {
        throw new UnsupportedOperationException();
    }

    public static final MessagePack MSG_PACK = new MessagePack();

    public static <T> byte[] pack(T value) {
        try {
            return MSG_PACK.write(value);
        } catch (IOException e) {
            throw new SerializationException("trying to pack value failed.", e);
        }
    }

    public static <T> T unpack(byte[] bytes, Class<T> type) {
        if(bytes == null || bytes.length <= 0) return null;
        try {
            return MSG_PACK.read(bytes, type);
        } catch (IOException e) {
            throw new SerializationException("trying to unpack value failed.", e);
        }
    }
}
