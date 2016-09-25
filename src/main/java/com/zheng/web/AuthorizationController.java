package com.zheng.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhenglian on 2016/9/25.
 */
@Controller
public class AuthorizationController {
    @RequestMapping("/403")
    public String unAuthorization() {
        return "403";
    }
}
