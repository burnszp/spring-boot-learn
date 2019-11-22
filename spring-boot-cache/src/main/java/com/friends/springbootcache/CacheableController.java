package com.friends.springbootcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CacheableController extends CacheInfo{

    @Autowired
    private CacheableService cacheableService;

    @GetMapping("testNoParamCache")
    public  RModel testNoParamCacheable(){
        CacheableService.CacheableModel cacheableModel = cacheableService.testNoParamCache();
        RModel cache = super.cache();
        cache.setRDate(cacheableModel);
        return cache;
    }

    @GetMapping("testNoParamCache2")
    public  RModel testNoParamCacheable2(){
        CacheableService.CacheableModel cacheableModel = cacheableService.testNoParamCache2();
        RModel cache = super.cache();
        cache.setRDate(cacheableModel);
        return cache;
    }



    @GetMapping("testOneParamCache")
    public  RModel testOneParamCache(String name){
        CacheableService.CacheableModel cacheableModel = cacheableService.testOneParamCacheMethod(name);
        RModel cache = super.cache();
        cache.setRDate(cacheableModel);
        return cache;
    }


    @GetMapping("testMultipleParamCache")
    public  RModel testMultipleParamCache(String name,Integer age){
        CacheableService.CacheableModel cacheableModel = cacheableService.testMultipleParamCacheMethod(name,age);
        RModel cache = super.cache();
        cache.setRDate(cacheableModel);
        return cache;
    }
}
