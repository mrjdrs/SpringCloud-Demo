package com.jdr.maven.feign.customer.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zhoude
 * @date 2019/10/13 17:09
 */
@FeignClient(name = "ad-model")
public interface AdRemoteService {

    @GetMapping("/getUserAd/{account}")
    String login(@PathVariable String account);
}
