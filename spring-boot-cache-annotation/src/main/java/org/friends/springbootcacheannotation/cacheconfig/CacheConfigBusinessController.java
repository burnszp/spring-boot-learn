package org.friends.springbootcacheannotation.cacheconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CacheConfigBusinessController {

    @Autowired
    private CacheConfigBusinessService businessService;

    @GetMapping("/cacheconfig")
    public String[] CacheconfigMethod(String ids){
       return   businessService.CacheconfigMethod(ids);
    }



}
