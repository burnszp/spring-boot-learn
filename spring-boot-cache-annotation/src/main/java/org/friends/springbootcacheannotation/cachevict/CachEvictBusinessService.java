package org.friends.springbootcacheannotation.cachevict;

import org.friends.springbootcacheannotation.BusinessModel;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class CachEvictBusinessService {

    //存储缓存
    @Cacheable(value = "temp",key = "#p0.name")
    public BusinessModel save(BusinessModel businessModel) {
        return businessModel;
    }

    //删除指定name的缓存
    @CacheEvict(value="temp",key="#name")
    public void delete(String name) {
        //delete  name define cache
    }

    //方法调用后清除定义空间内的缓存
    @CacheEvict(value="temp",allEntries=true)
    public void deleteAll() {
     //delete all
    }

    //方法调用前清除清除定义空间内的缓存
    @CacheEvict(value="temp",allEntries=true,beforeInvocation=true)
    public void deleteAllBefore() {
       //delete all
    }
}
