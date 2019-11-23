package org.friends.springbootcacheannotation.cacheable;

import org.friends.springbootcacheannotation.BusinessModel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class BusinessService {
    //使用默认值
    @Cacheable("cacheableSpace")
    public BusinessModel cacheableMethod(BusinessModel businessModel) {
        return  businessModel;
    }

    //使用固定值
    @Cacheable(value = "cacheableSpace" ,key = "'fixKey'")
    public BusinessModel CacheableMethodFix(BusinessModel businessModel) {
        return businessModel;
    }

    //使用SpEL
    @Cacheable(value = "cacheableSpace" ,key = "'target:'+target+'-methodName:'+methodName+'-targetClass:'+targetClass+'-method:'+method+'-args:'+#businessModel")
    public BusinessModel CacheableMethodSpEL(BusinessModel businessModel) {
        return businessModel;
    }


    @Cacheable(value = "cacheableSpace" ,key = "#p0+#p1+#sex+#a3")
    public BusinessModel CacheableMethodSpEL2(String  name, Integer age, Boolean sex, Timestamp date) {
        return new BusinessModel(name,age, sex,date);
    }
}
