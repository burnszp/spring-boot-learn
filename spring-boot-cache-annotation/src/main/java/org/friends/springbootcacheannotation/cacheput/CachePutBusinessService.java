package org.friends.springbootcacheannotation.cacheput;

import org.friends.springbootcacheannotation.BusinessModel;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class CachePutBusinessService {

    @CachePut(value = "updateCache", key = "targetClass+ methodName +#p0")
    public BusinessModel updateMethod(BusinessModel businessModel) {
        return businessModel;
    }

}
