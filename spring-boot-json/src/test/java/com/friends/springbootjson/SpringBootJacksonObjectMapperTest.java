package com.friends.springbootjson;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.friends.springbootjson.entity.Apartment;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class SpringBootJacksonObjectMapperTest {

    //`writeValue`对象序列化的时候可以直接将需要序列化的数据格式化成文件
    @Test
    public void testWriteValueAsFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Apartment apartment = new Apartment("Joey", "Chandler");
        objectMapper.writeValue(new File("target/apartment.json"), apartment);
    }
    //`writeValueAsString`和`writeValueAsBytes`，对象可以直接序列化为字符串和字节
    @Test
    public void testWriteValueAsStringAndByte() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Apartment apartment = new Apartment("Joey", "Chandler");
        String result = objectMapper.writeValueAsString(apartment);
        System.out.println(result);
        byte[] bytes = objectMapper.writeValueAsBytes(apartment);
        System.out.println(new String(bytes));
        assertEquals(result,new String(bytes));
    }
    //`readValue`，将json串反序列化为对象
    @Test
    public void testReadValueAsBeanFromJson() throws IOException {
        String json = "{ \"roommate\" : \"Joey\", \"payRentMan\" : \"Chandler\" }";
        Apartment apartment = new ObjectMapper().readValue(json, Apartment.class);
        assertEquals(apartment.getPayRentMan(),"Chandler");
    }

    //`readValue`，将json串反序列化为对象,可以将file文件作为数据源也可以读取网络URL流中数据
    @Test
    public void testReadValueAsBeanFromFileAndURL() throws IOException {
        Apartment apartment = new ObjectMapper().readValue(new File("target/apartment.json"), Apartment.class);
        //Apartment apartment = new ObjectMapper().readValue(new URL("target/apartment.json"), Apartment.class);
        assertEquals(apartment.getPayRentMan(),"Chandler");
    }

    //将Json解析为Jackson JsonNode对象，从特定的节点中检索数据
    @Test
    public void testReadTree() throws IOException {
        String json = "{ \"roommate\" : \"Joey\", \"payRentMan\" : \"Chandler\" }";
        JsonNode jsonNode = new ObjectMapper().readTree(json);
        assertEquals(jsonNode.get("payRentMan").asText(),"Chandler");
    }

    //解析json数组到list中
    @Test
    public void testReadValueAsListBean() throws IOException {
        String jsonApartmentArray =
                "[{ \"roommate\" : \"Joey\", \"payRentMan\" : \"Chandler\" }, { \"roommate\" : \"Rachel\", \"payRentMan\" : \"Monica\" }]";
       List<Apartment>  apartments=  new ObjectMapper().readValue(jsonApartmentArray,new TypeReference<List<Apartment>>(){});
        assertEquals(apartments.size(),2);
    }

    //将数据映射到Map对象中
    @Test
    public void testReadValueAsMap() throws IOException {
        String json = "{ \"roommate\" : \"Joey\", \"payRentMan\" : \"Chandler\" }";
        Map<String, Object> map
                =  new ObjectMapper().readValue(json, new TypeReference<Map<String,Object>>(){});
        assertEquals(map.size(),2);
    }

    //解决反序列化为bean时json多出字段导致UnrecognizedPropertyException异常
    //第一种方式使用readTree反序列化为JsonNode
    //第二种方式configure方式忽略多余字段
    @Test
    public void testMoreProperty() throws IOException {
        String json = "{ \"roommate\" : \"Joey\", \"animal\" : \"dark\"}";
        JsonNode jsonNode = new ObjectMapper().readTree(json);
        assertEquals(jsonNode.get("animal").asText(),"dark");
        ObjectMapper objectMapper= new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Apartment apartment = objectMapper.readValue(json, Apartment.class);
        assertEquals(apartment.getRoommate(),"Joey");
    }

    //处理日期有很多种方式，着重介绍2种
    //第一种使用注解@JsonFormat
    //第二种将日期格式化规则写入到ObjectMapper中
    @Test
    public void testDateFormat() throws IOException {
        Request request = new Request();
        Apartment apartment = new Apartment("Joey", "Chandler");
        request.setApartment(apartment);
        request.setBillingDate(new Date());
        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        objectMapper.setDateFormat(df);
        String string = objectMapper.writeValueAsString(request);
        System.out.println(string);
    }
    //序列化和反序列化嵌套list,如下
    @Test
    public void testDateFormat2() throws IOException {
        ArrayList<RequestList> requestLists = Lists.newArrayList(new RequestList(Lists.newArrayList(new Apartment("Joey", "Chandler")),1), new RequestList(Lists.newArrayList(new Apartment("Joey", "Chandler")),2));
        ObjectMapper objectMapper = new ObjectMapper();
        String string = objectMapper.writeValueAsString(requestLists);
        System.out.println(string);
        List<RequestList> m =  new ObjectMapper().readValue(string, new TypeReference<List<RequestList>>(){});
        assertEquals(m.size(),2);
    }
}
//注意，Jackson反序列化json为对象的时候，使用的是对象空参构造器，在我们自定义构造器的时候，需要覆写空参构造器

class RequestList
{
    private List<Apartment> apartment;

    private Integer id;

    public RequestList() {
    }

    public RequestList(List<Apartment> apartment, Integer id) {
        this.apartment = apartment;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Apartment> getApartment() {
        return apartment;
    }

    public void setApartment(List<Apartment> apartment) {
        this.apartment = apartment;
    }
}

 class Request
{
    private Apartment apartment;
    private Date billingDate;

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Date getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }
}