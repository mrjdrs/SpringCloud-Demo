package com.jdr.maven.feign.customer.api;

import com.jdr.maven.feign.customer.model.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoude
 * @date 2019/10/13 16:47
 */
@RestController
@RequestMapping("/user")
public class LoginApi {

    private static Map<String, UserDTO> USER_INFO = new HashMap<>();

    static {
        for (int i = 0; i < 3; i++) {
            USER_INFO.put("ZD" + i, new UserDTO("ZD" + i, "jdr" + i));
        }
    }

    @GetMapping("/login/{account}/{password}")
    public String login(@PathVariable String account, @PathVariable String password) {
        UserDTO userDTO = USER_INFO.get(account);
        if (userDTO == null) {
            return "FAILED";
        }

        boolean result = userDTO.getPassword().equalsIgnoreCase(password);
        if (!result) {
            return "FAILED";
        }

        // 调用广告接口


        return "SUCCESS";
    }
}
