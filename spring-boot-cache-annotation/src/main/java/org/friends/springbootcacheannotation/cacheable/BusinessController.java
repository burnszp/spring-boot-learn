package org.friends.springbootcacheannotation.cacheable;

import org.friends.springbootcacheannotation.BusinessModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @GetMapping("/cacheable")
    public BusinessModel CacheableMethod(BusinessModel businessModel){
       return   businessService.cacheableMethod(businessModel);
    }

    @GetMapping("/cacheableFix")
    public BusinessModel CacheableMethodFix(BusinessModel businessModel){
        return   businessService.CacheableMethodFix(businessModel);
    }

    @GetMapping("/cacheableSpEL")
    public BusinessModel CacheableMethodSpEL(BusinessModel businessModel){
        return   businessService.CacheableMethodSpEL(businessModel);
    }

    @GetMapping("/cacheableSpEL2")
    public BusinessModel CacheableMethodSpEL2(BusinessModel businessModel){
        return   businessService.CacheableMethodSpEL2(businessModel.getName(),businessModel.getAge(),businessModel.getSex(),businessModel.getDate());
    }

}
