package com.friends.springbootcache;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheableService {

    @Cacheable(value = "CacheNoParam" )
    public CacheableModel testNoParamCache() {
        return new CacheableModel("Joey",22);
    }

    @Cacheable(value = "CacheNoParam" )
    public CacheableModel testNoParamCache2() {
        return new CacheableModel("Chandler",25);
    }
    
    @Cacheable(value = "testOneParamCache" )
    public CacheableModel testOneParamCacheMethod(String name) {
        return new CacheableModel(name,44);
    }

    @Cacheable(value = "testMultipleParamCache" )
    public CacheableModel testMultipleParamCacheMethod(String name, Integer age) {
        return new CacheableModel(name,age);
    }

    @Data
    @AllArgsConstructor
     class   CacheableModel{
            private String name;
            private Integer age;
     }

}
