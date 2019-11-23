package org.friends.springbootcacheannotation.cachevict;

import org.friends.springbootcacheannotation.BusinessModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CachEvictBusinessController {

    @Autowired
    private CachEvictBusinessService businessService;

    @GetMapping("/save")
    public BusinessModel save(BusinessModel businessModel) {
        return businessService.save(businessModel);
    }

    @GetMapping("/delete")
    public void delete(String name) {
        businessService.delete(name);
    }

    @GetMapping("/deleteAll")
    public void deleteAll() {
        businessService.deleteAll();
    }

    @GetMapping("/deleteAllBefore")
    public void deleteAllBefore() {
        businessService.deleteAllBefore();
    }
}
