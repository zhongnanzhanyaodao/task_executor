package com.kingdee.internet.security;

import com.kingdee.internet.util.CommonUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

public class CryptosTest {
    public static final Logger logger = LoggerFactory.getLogger(Cryptos.class);

    @Test
    public void aesTest() {
        String iv = "wmKWvDAKPPZcDgNh";
        String password = "af25414ea7ef616ghce2bf0bc811895c";

        logger.info(Encodes.encodeBase64(iv.getBytes(Charset.forName("utf-8"))));
        logger.info(Encodes.encodeBase64(password.getBytes(Charset.forName("utf-8"))));

        String source = Encodes.encodeBase64(Cryptos.aesEncrypt("pfx147258".getBytes(),
                Encodes.decodeBase64("YWYyNTQxNGVhN2VmNjE2Z2hjZTJiZjBiYzgxMTg5NWM="),
                Encodes.decodeBase64("d21LV3ZEQUtQUFpjRGdOaA=="),
                16));
        logger.info(source);
    }

    @Test
    public void testState() {
        Context context = new Context();
        context.setState(States.INIT);
        context.process();
    }

    interface State {
        boolean process(Context context);
    }

    enum States implements State {
        INIT {
            @Override
            public boolean process(Context context) {
                States.name = context.name();
                context.setState(PRINT);
                return true;
            }
        }, PRINT {
            @Override
            public boolean process(Context context) {
                logger.info(name);
                return false;
            }
        };

        static String name;
    }

    class Context {
        String name() {
            return "Yondy";
        }

        State state;

        public State getState() {
            return state;
        }

        public void setState(State state) {
            this.state = state;
        }

        public void process() {
            while (state.process(this));
        }
    }
}