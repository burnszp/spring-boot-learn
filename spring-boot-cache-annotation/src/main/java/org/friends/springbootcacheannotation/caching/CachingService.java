package org.friends.springbootcacheannotation.caching;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CachingService {
    @Caching(evict = { @CacheEvict("primary"), @CacheEvict(cacheNames="secondary", key="#p0") })
    public void importBooks(String deposit, Date date){}
}
