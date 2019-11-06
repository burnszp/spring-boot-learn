package com.friends.springbootjpa.persistence.dao;

import com.friends.springbootjpa.persistence.model.CustomEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public interface CustomRepository extends JpaRepository<CustomEntity,Integer> {

    List<CustomEntity> findByFirstNameAndLastName(String firstName, String lastName);

    // Enables the distinct flag for the query
    List<CustomEntity> findDistinctByFirstName(String firstName);

    // Enabling ignoring case for an individual property
    List<CustomEntity> findByLastNameIgnoreCase(String lastName);
    // Enabling ignoring case for all suitable properties
    List<CustomEntity> findByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName);

    // Enabling static ORDER BY for a query
    List<CustomEntity> findByLastNameOrderByFirstNameAsc(String lastName);
    List<CustomEntity> findByLastNameOrFirstNameOrderByFirstNameDesc(String lastName,String firstName);




    Page<CustomEntity> findByLastName(String lastName, Pageable pageable);

    Slice<CustomEntity> findByFirstName(String firstName, Pageable pageable);

    List<CustomEntity> findByLastName(String lastName, Sort sort);

    List<CustomEntity> findByUserName(String userName, Pageable pageable);


    CustomEntity  findFirstByOrderByStatusAsc();

    CustomEntity  findTopByOrderByStatusDesc();



    @Query("select u from CustomEntity u")
    Stream<CustomEntity> findAllByCustomQueryAndStream();

    Stream<CustomEntity> readAllByFirstNameNotNull();

    @Query("select u from CustomEntity u")
    Stream<CustomEntity> streamAllPaged(Pageable pageable);


    @Query("select u from CustomEntity u where u.firstName = ?1")
    CustomEntity findByFirstName(String firstName);



    @Query("select u from CustomEntity u where u.lastName like %?1")
    List<CustomEntity> findByLastNameEndsWith(String lastName);

    List<CustomEntity> findByCreateTimeEquals(Date createTime);

    //若执行sql的原生查询使用nativeQuery标志设置为true
    @Query(value = "SELECT * FROM custom WHERE   user_name=?1", nativeQuery = true)
    CustomEntity findByUserName(String userName);

    //Query中的分页
    @Query(value = "SELECT * FROM custom WHERE create_time = ?1",
            countQuery = "SELECT count(*) FROM custom",
            nativeQuery = true)
    Page<CustomEntity> findByCreateTimeByQuery(Date createTime, Pageable pageable);

    //查询并添加动态排序
    @Query("select u from CustomEntity u where u.lastName like %?1")
    List<CustomEntity> findByAndSort(String lastName, Sort sort);

    //返回数组
    @Query("select u.id, LENGTH(u.firstName) as fn_len from CustomEntity u where u.firstName like ?1%")
    List<Object[]> findByAsArrayAndSort(String lastName, Sort sort);


    //使用命名参数
    @Query("select u from CustomEntity u where u.firstName = :firstName or u.lastName = :lastName")
    CustomEntity findByLastNameOrFirstName(@Param("lastName") String lastName,
                                   @Param("firstName") String firstName);


    @Modifying
    @Query("delete from CustomEntity u where u.id = ?1")
    void deleteByMyId(Integer id);

    @Modifying
    @Query("update CustomEntity u set u.firstName = ?1 where u.id = ?2")
    int setFixedFirstNameFor(String firstName, Integer id);
}
