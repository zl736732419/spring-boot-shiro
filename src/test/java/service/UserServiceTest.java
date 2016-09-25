package service;

import com.zheng.domain.User;
import com.zheng.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhenglian on 2016/9/24.
 */
public class UserServiceTest extends BaseServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void save() {
        User user = new User("lisi", "admin", 0);
        userService.save(user);
        System.out.println("保存用户成功!");
    }

    @Test
    public void get() {
        User user = userService.get(5L);
        System.out.println(user);
    }

    @Test
    public void delete() {
        userService.delete(5L);
    }
}
