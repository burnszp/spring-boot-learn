package com.friends.springbootjson;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.friends.springbootjson.entity.*;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;

public class SpringBootJacksonAnnotationTest {

    // ============================serialization annotations
    //1
    @Test
    public void testJsonAnyGetter() throws Exception {
        UserJsonAnyGetter ujg = new UserJsonAnyGetter();
        ujg.setUserName("Rose");
        Map<String, String> properties = new HashMap<>();
        properties.put("character1","geeky brother");
        properties.put("character2","biologist");
        ujg.setProperties(properties);
        String result = new ObjectMapper().writeValueAsString(ujg);
        System.out.println(result);
        assertThat(result,containsString("character1"));
        assertThat(result,containsString("character2"));
    }


    class  UserJsonAnyGetter  {
        private String userName;
        private Map<String, String> properties;
        @JsonAnyGetter
        public Map<String, String> getProperties() {
            return properties;
        }

        public void setProperties(Map<String, String> properties) {
            this.properties = properties;
        }
        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

    }

    //2
    @Test
    public void testUserJsonGetter() throws JsonProcessingException {
        final UserJsonGetter ujg = new UserJsonGetter("Joey",1);
        final String result = new ObjectMapper().writeValueAsString(ujg);
        System.out.println(result);
        assertThat(result, containsString("Joey"));
        assertThat(result, containsString("1"));
    }


    class  UserJsonGetter  {
        private String userName;
        private Integer id;
        @JsonGetter("name")
        public String getUserName() {
            return userName;
        }
        public void setUserName(String userName) {
            this.userName = userName;
        }


        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
        public UserJsonGetter(String userName, int id) {
            this.userName=userName;
            this.id=id;
        }


    }

    //3
    @Test
    public void testUserJsonPropertyOrder() throws JsonProcessingException {
        final UserJsonPropertyOrder ujg = new UserJsonPropertyOrder("Joey",1);
        final String result = new ObjectMapper().writeValueAsString(ujg);
        System.out.println(result);
        assertThat(result, containsString("Joey"));
        assertThat(result, containsString("1"));
    }

    @JsonPropertyOrder({  "id","userName" })
    class  UserJsonPropertyOrder  {
        private String userName;
        private Integer id;
        public String getUserName() {
            return userName;
        }
        public void setUserName(String userName) {
            this.userName = userName;
        }


        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
        public UserJsonPropertyOrder(String userName, int id) {
            this.userName=userName;
            this.id=id;
        }
    }


    //4
    @Test
    public void testUserJsonRawValue() throws JsonProcessingException {
        String json="{\"character2\":\"biologist\",\"character1\":\"geeky brother\"}";
        final UserJsonRawValue ujg = new UserJsonRawValue(json,1);
        final String result = new ObjectMapper().writeValueAsString(ujg);
        System.out.println(result);
        assertThat(result, containsString(json));
    }

    class  UserJsonRawValue  {
        @JsonRawValue
        private String character;
        private Integer id;

        public String getCharacter() {
            return character;
        }

        public void setCharacter(String character) {
            this.character = character;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
        public UserJsonRawValue(String character, int id) {
            this.character=character;
            this.id=id;
        }
    }
    //5
    @Test
    public void testUserJsonValue() throws JsonProcessingException {
        final String result = new ObjectMapper().writeValueAsString(UserJsonValue.JOEY);
        System.out.println(result);
        assertThat(result,is("1.78"));
    }

    enum  UserJsonValue  {
      JOEY(1.78),MONICA(1.65),CHANDLER(1.75),PHOEBE(1.69);
        private  final double height;
        UserJsonValue(double height) {
            this.height = height;
        }
        @JsonValue
        public double getHeight() {
            return height;
        }
    }

    //6
    @Test
    public void testUserJsonRootName() throws JsonProcessingException {
        final UserJsonRootName ujg = new UserJsonRootName("handsome",1);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final String result =mapper.writeValueAsString(ujg);
        System.out.println(result);
        assertThat(result, containsString("user"));
    }

    @JsonRootName(value = "user")
    class  UserJsonRootName  {
        private String character;
        private Integer id;

        public String getCharacter() {
            return character;
        }

        public void setCharacter(String character) {
            this.character = character;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
        public UserJsonRootName(String character, int id) {
            this.character=character;
            this.id=id;
        }
    }

    //7
    @Test
    public void testUserJsonSerialize() throws JsonProcessingException, ParseException {
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        final String toParse = "2018-12-10 02:30:00";
        final Date date = df.parse(toParse);
        final UserJsonSerialize event = new UserJsonSerialize( date,1);

        final String result = new ObjectMapper().writeValueAsString(event);
        assertThat(result, containsString(toParse));
    }



// ============================Deserialization Annotations

    //1
    //反序列化@JsonCreator配合@JsonProperty,将username映射为实体类中的name
    @Test
    public void testUserJsonCreator() throws IOException {
        final String json = "{\"id\":1,\"username\":\"joey\"}";
        final UserJsonCreator bean = new ObjectMapper().readerFor(UserJsonCreator.class)
                .readValue(json);
        assertEquals("joey", bean.name);
    }
    //2
    // @JacksonInject,表示使用外部注入值，而不是从json中获取
    @Test
    public void whenDeserializingUsingJsonInject_thenCorrect()
            throws IOException {

        String json = "{\"name\":\"Joey\"}";

        InjectableValues inject = new InjectableValues.Std()
                .addValue(int.class, 1);
        BeanWithInject bean = new ObjectMapper().reader(inject)
                .forType(BeanWithInject.class)
                .readValue(json);

        assertEquals("Joey", bean.name);
        assertEquals(1, bean.id);
    }

    //3
    //@JsonAnySetter将平级的key：value参数装载到Map中
    @Test
    public void testUserJsonAnySetter() throws IOException {
        final String json = "{\"name\":\"Rose\",\"character2\":\"biologist\",\"character1\":\"geeky brother\"}";

        final UserJsonAnySetter bean = new ObjectMapper().readerFor(UserJsonAnySetter.class)
                .readValue(json);
        assertEquals("Rose", bean.name);
        assertEquals("biologist", bean.getProperties()
                .get("character2"));

    }

    //4
    //@JsonSetter是@JsonProperty的替代品，主要用在setter中，用于适配实体类和json不相符的情况
    @Test
    public void whenDeserializingUsingJsonSetter_thenCorrect() throws IOException {
        final String json = "{\"id\":1,\"name\":\"Joey\"}";
        final BeanWithGetter bean = new ObjectMapper().readerFor(BeanWithGetter.class)
                .readValue(json);
        assertEquals("Joey", bean.getTheName());
    }

    //5
    //自定义反序列化方式，继承`StdDeserializer<Date>`接口，实现`deserialize`方法
    @Test
    public void whenDeserializingUsingJsonDeserialize_thenCorrect() throws IOException {
        final String json = "{\"name\":\"Rose\",\"eventDate\":\"2018-12-10 02:30:00\"}";

        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        final EventWithSerializer event = new ObjectMapper().readerFor(EventWithSerializer.class)
                .readValue(json);
        assertEquals("2018-12-10 02:30:00", df.format(event.eventDate));
    }
    //6
    //@JsonAlias定义反序列化过程为属性的一个或多个的别名，多别名映射到同一个字段上
    @Test
    public void whenDeserializingUsingJsonAlias_thenCorrect() throws IOException {
        String json = "{\"fName\": \"Joseph\", \"lastName\": \"Tribbiani\"}";
        AliasBean aliasBean = new ObjectMapper().readerFor(AliasBean.class).readValue(json);
        assertThat(aliasBean.getFirstName(), is("Joseph"));
    }
    // ============================Inclusion  annotations

    //1
    //@JsonIgnoreProperties是一个类级注释，可以标记忽略多个属性
    //@JsonIgnore忽略单个属性
    @Test
    public void whenSerializingUsingJsonIgnoreProperties_thenCorrect()
            throws JsonProcessingException {
        BeanWithIgnore bean = new BeanWithIgnore(1, "Joey","I'm Joey");
        String result = new ObjectMapper()
                .writeValueAsString(bean);
        System.out.println(result);
        assertThat(result, containsString("Joey"));
        assertThat(result, not(containsString("id")));
    }
    //2
    //3
    //@JsonIgnoreType代表忽略某个类型的所有属性
    @Test
    public void whenSerializingUsingJsonIgnoreType_thenCorrect() throws JsonProcessingException {
        final UserWithIgnoreType.Name name = new UserWithIgnoreType.Name("Joey", "Rose");
        final UserWithIgnoreType user = new UserWithIgnoreType(1, name);

        final String result = new ObjectMapper().writeValueAsString(user);
        System.out.println(result);
        assertThat(result, containsString("1"));
        assertThat(result, not(containsString("Joey")));
        assertThat(result, not(containsString("Rose")));
    }

    //4
    // @JsonInclude序列化的过程中排除empty/null/default
    @Test
    public void whenSerializingUsingJsonInclude_thenCorrect()
            throws JsonProcessingException {
        MyBean bean = new MyBean(1, null);
        String result = new ObjectMapper()
                .writeValueAsString(bean);
        System.out.println(result);
        assertThat(result, containsString("1"));
        assertThat(result, not(containsString("name")));
    }
    //5
    //@JsonAutoDetect可以覆盖属性显示定义的作用域范围
    @Test
    public void whenSerializingUsingJsonAutoDetect_thenCorrect()
            throws JsonProcessingException {

        PrivateBean bean = new PrivateBean(1, "Joey");
        String result = new ObjectMapper()
                .writeValueAsString(bean);
        System.out.println(result);
        assertThat(result, containsString("1"));
        assertThat(result, containsString("Joey"));
    }

    // ============================ General annotations
    //1
    //@JsonProperty注释，处理非标准getter和setter，适配json和实例类的中属性不一致
    @Test
    public void whenUsingJsonProperty_thenCorrect() throws IOException {
        final BeanJsonProperty bean = new BeanJsonProperty(1, "Joey");

        final String result = new ObjectMapper().writeValueAsString(bean);
        assertThat(result, containsString("Joey"));
        assertThat(result, containsString("1"));
        System.out.println(result);
        final BeanJsonProperty resultBean = new ObjectMapper().readerFor(BeanJsonProperty.class)
                .readValue(result);
        assertEquals("Joey", resultBean.getName());
    }
    //2
    //@JsonFormat控制序列化和反序列化的日期格式
    @Test
    public void whenSerializingUsingJsonFormat_thenCorrect() throws IOException, ParseException {
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        final String toParse = "2018-12-20 02:30:00";
        final Date date = df.parse(toParse);
        final EventWithFormat event = new EventWithFormat("party", date);

        final String result = new ObjectMapper().writeValueAsString(event);
        System.out.println(result);
        assertThat(result, containsString(toParse));
        EventWithFormat eventWithFormat = new ObjectMapper().readValue(result, EventWithFormat.class);
        assertThat(eventWithFormat.getEventDate(),equalTo(date) );
    }
    //3
    //@JsonUnwrapped,序列和反序列化的时候将包内的值提取到外层或者注入到内层
    @Test
    public void whenSerializingUsingJsonUnwrapped_thenCorrect()
            throws IOException {
        UnwrappedUser.Name name = new UnwrappedUser.Name("Joseph", "Tribbiani");
        UnwrappedUser user = new UnwrappedUser(1, name);

        String result = new ObjectMapper().writeValueAsString(user);
        System.out.println(result);
        assertThat(result, containsString("Joseph"));
        assertThat(result, not(containsString("name")));
    }
    //4
    //@JsonView指定序列化和反序列化视图
    @Test
    public void whenSerializingUsingJsonView_thenCorrect() throws JsonProcessingException, JsonProcessingException {
        final Item item = new Item(2, "roommate", "Joey");

        final String result = new ObjectMapper().writerWithView(Views.Public.class)
                .writeValueAsString(item);
        System.out.println(result);
        assertThat(result, containsString("roommate"));
        assertThat(result, containsString("2"));
        assertThat(result, not(containsString("Joey")));
    }
    //5
    //@JsonManagedReference和@JsonBackReference注释可以确定父/子关系从而相互引用导致的循环问题
    @Test
    public void whenSerializingUsingJacksonReferenceAnnotation_thenCorrect() throws JsonProcessingException {
        final UserWithRef user = new UserWithRef(1, "Joey");
        final ItemWithRef item = new ItemWithRef(2, "coffee", user);
        user.addItem(item);
        //以上会形成一个递归关系
        final String result = new ObjectMapper().writeValueAsString(item);
        System.out.println(result);
        assertThat(result, containsString("coffee"));
        assertThat(result, containsString("Joey"));
        assertThat(result, not(containsString("userItems")));
    }

    //6
    // @JsonIdentityInfo处理无限递归问题
    @Test
    public void whenSerializingUsingJsonIdentityInfo_thenCorrect() throws JsonProcessingException {
        final UserWithIdentity user = new UserWithIdentity(1, "Joey");
        final ItemWithIdentity item = new ItemWithIdentity(2, "coffee", user);
        user.addItem(item);
        //以上会形成一个递归关系
        final String result = new ObjectMapper().writeValueAsString(item);
        System.out.println(result);
        assertThat(result, containsString("coffee"));
        assertThat(result, containsString("Joey"));
        assertThat(result, containsString("userItems"));
    }

}