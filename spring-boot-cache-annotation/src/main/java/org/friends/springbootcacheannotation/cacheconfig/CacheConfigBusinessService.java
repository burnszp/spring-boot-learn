package org.friends.springbootcacheannotation.cacheconfig;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
@CacheConfig(cacheNames = {"myCahce"})
public class CacheConfigBusinessService {
    @Cacheable(key = "targetClass + methodName +#p0")//此处不需value
    public String[] CacheconfigMethod(String ids) {
        String[]  id=null;
        if(!StringUtils.isEmpty(ids)){
            id=  ids.split(",");
        }
        return id;
    }
}
