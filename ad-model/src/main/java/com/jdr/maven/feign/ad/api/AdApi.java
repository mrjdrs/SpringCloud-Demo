package com.jdr.maven.feign.ad.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoude
 * @date 2019/10/13 16:47
 */
@RestController
@RequestMapping("/ad")
public class AdApi {

    @GetMapping("/getUserAd/{account}")
    public String login(@PathVariable String account) {
        return "这是" + account + "的广告";
    }
}
