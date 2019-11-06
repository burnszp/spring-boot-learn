package com.friends.springbootjpa.persistence.dao;

import com.friends.springbootjpa.persistence.model.CustomEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomRepositoryTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Autowired
    private CustomRepository customRepository;

    @Test
    @Transactional
    public void  testSimpleSave(){
        CustomEntity customEntity = new CustomEntity(null, "firstName",  "lastName",  "userName", 1, new Timestamp(new Date().getTime()));
        assertThat(customRepository.save(customEntity)).isEqualTo(customRepository.findById(customEntity.getId()).get());
    }


    //1.关键字支持
    //详情见:https://docs.spring.io/spring-data/jpa/docs/2.1.6.RELEASE/reference/html/#repositories.query-methods.query-creation
    //关键字支持:https://docs.spring.io/spring-data/jpa/docs/2.1.6.RELEASE/reference/html/#jpa.query-methods.query-creation
    @Test
    @Transactional
    public void  testKeyword(){
        int totalNumberUsers = 3;
        List<CustomEntity> source = new ArrayList<>(totalNumberUsers);

        for (int i = 1; i <= totalNumberUsers; i++) {

            source.add(new CustomEntity(null, "firstName",  "lastName-" + String.format("%03d", i),  "userName", 1, new Timestamp(new Date().getTime())));
        }
        source.add(new CustomEntity(null, "firstName",  "lastName-",  "userName", 1, new Timestamp(new Date().getTime())));
        source.add(new CustomEntity(null, "firstName",  "lastName-",  "userName", 1, new Timestamp(new Date().getTime())));
        customRepository.saveAll(source);
        assertThat(customRepository.findByFirstNameAndLastName(source.get(0).getFirstName(),source.get(0).getLastName())).contains(source.get(0));
        assertThat (customRepository.findDistinctByFirstName(source.get(0).getFirstName())).containsAll(source);
        assertThat(customRepository.findByLastNameAndFirstNameAllIgnoreCase("lastname-","firstname").size()).isEqualTo(2);
        assertThat(customRepository.findByLastNameOrFirstNameOrderByFirstNameDesc("lastName-","firstName").size()).isEqualTo(5);
    }

    //2.关键字支持并添加分页
    @Test
    @Transactional
    public void  testKeywordAndPageable(){
        List<CustomEntity> source = new ArrayList<>(3);
        source.add(new CustomEntity(null, "firstName",  "lastName",  "userName", 2, new Timestamp(new Date().getTime())));
        source.add(new CustomEntity(null, "firstName",  "lastName",  "userName", 1, new Timestamp(new Date().getTime())));
        source.add(new CustomEntity(null, "firstName",  "lastName",  "userName", 3, new Timestamp(new Date().getTime())));
        customRepository.saveAll(source);
        assertThat(customRepository.findByFirstName("firstName", PageRequest.of(0, 2, Sort.by("status"))).getContent().get(1).getStatus()).isEqualTo(2);
        assertThat(customRepository.findByLastName("lastName", PageRequest.of(0, 2)).getSize()).isEqualTo(2);
        assertThat( customRepository.findByLastName("lastName", Sort.by("status"))).isEqualTo(customRepository.findByUserName("userName", PageRequest.of(0, 6, Sort.by("status"))));
    }

    //3.限制查询结果
    @Test
    @Transactional
    public void  testTopAndFirst(){
        List<CustomEntity> source = new ArrayList<>(3);
        source.add(new CustomEntity(null, "firstName1",  "lastName",  "userName", 2, new Timestamp(new Date().getTime())));
        source.add(new CustomEntity(null, "firstName2",  "lastName",  "userName", 1, new Timestamp(new Date().getTime())));
        source.add(new CustomEntity(null, "firstName3",  "lastName",  "userName", 3, new Timestamp(new Date().getTime())));
        customRepository.saveAll(source);
        assertThat( customRepository.findFirstByOrderByStatusAsc().getFirstName()).isEqualTo("firstName2");
        assertThat( customRepository.findTopByOrderByStatusDesc().getFirstName()).isEqualTo("firstName3");
    }

    //4.流式查询

    @Test
    @Transactional
    public void  testQueryResultStreamType(){
        List<CustomEntity> source = new ArrayList<>(3);
        source.add(new CustomEntity(null, "firstName1",  "lastName",  "userName", 2, new Timestamp(new Date().getTime())));
        source.add(new CustomEntity(null, "firstName2",  "lastName",  "userName", 1, new Timestamp(new Date().getTime())));
        source.add(new CustomEntity(null, null,  "lastName",  "userName", 3, new Timestamp(new Date().getTime())));
        customRepository.saveAll(source);
        assertThat( customRepository.findAllByCustomQueryAndStream()).isInstanceOf(Stream.class);
        assertThat(customRepository.readAllByFirstNameNotNull().count()).isEqualTo(2);
    }


    //5.使用@Query进行查询，参数为占位符
    @Test
    @Transactional
    public void  testQueryByQueryAnnotationAndPlaceholderParameters()  {
        List<CustomEntity> source = new ArrayList<>(3);
        String current = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format( new Date());
        Timestamp time = Timestamp.valueOf(current);
        source.add(new CustomEntity(null, "firstName1",  "1lastName",  "userName1", 1,time) );
        source.add(new CustomEntity(null, "firstName2",  "2lastName",  "userName2", 2, time));
        customRepository.saveAll(source);
        assertThat(customRepository.findByCreateTimeEquals(time).size()).isEqualTo(2);
        assertThat( customRepository.findByFirstName("firstName1")).isEqualTo(source.get(0));
        assertThat(customRepository.findByUserName("userName2")).isEqualTo(source.get(1));
        assertThat(customRepository.findByLastNameEndsWith("lastName")).containsAll(source);
        assertThat(customRepository.findByAndSort("lastName",Sort.by(Sort.Direction.DESC,"status")).get(0).getStatus()).isEqualTo(2);
        assertThat(customRepository.findByCreateTimeByQuery(time,PageRequest.of(0,1)).getContent().size()).isEqualTo(1);
        assertThat(customRepository.findByAsArrayAndSort("firstName", Sort.by(Sort.Direction.DESC, "status")).get(0).length).isEqualTo(2);
    }

    //6.使用@Query进行查询，使用命名参数
    @Test
    @Transactional
    public void  testQueryByQueryAnnotationAndNamedParameters()  {
        List<CustomEntity> source = new ArrayList<>(2);
        source.add(new CustomEntity(null, "firstName1",  "1lastName",  "userName1", 1,null) );
        source.add(new CustomEntity(null, "firstName2",  "2lastName",  "userName2", 2, null));
        customRepository.saveAll(source);
        assertThat(customRepository.findByLastNameOrFirstName("1lastName","firstName1")).isEqualTo(source.get(0));
    }

    //7.使用@Query进行删除和修改
    @Test
    @Transactional
    public void  testModifyingAnnotation()  {
        CustomEntity customEntityN = new CustomEntity(null, "firstName1", "1lastName", "userName1", 1, null);
        customRepository.save(customEntityN);
        customRepository.deleteByMyId(customEntityN.getId());
        customRepository.setFixedFirstNameFor("firstName2",customEntityN.getId());
    }

}