package service;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

/**
 * Created by zhenglian on 2016/9/25.
 */
public class ShiroUserTest {

    @Test
    public void createUser() {
        String username = "user";
        String password = "123456";
        String salt = "helloworld";

        SimpleHash hash = new SimpleHash("md5", password,
                ByteSource.Util.bytes(username+salt), 2);
        String msg = hash.toHex();
        System.out.println(msg);
    }

}
