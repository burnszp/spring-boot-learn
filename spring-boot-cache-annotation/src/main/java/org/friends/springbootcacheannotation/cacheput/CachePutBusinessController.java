package org.friends.springbootcacheannotation.cacheput;

import org.friends.springbootcacheannotation.BusinessModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CachePutBusinessController {

    @Autowired
    private CachePutBusinessService businessService;

    @GetMapping("/update")
    public BusinessModel updateMethod(BusinessModel businessModel){
       return   businessService.updateMethod(businessModel);
    }

}
