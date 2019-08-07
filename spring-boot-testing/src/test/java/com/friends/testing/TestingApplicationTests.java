package com.friends.testing;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestingApplicationTests {

    @Test
    @Ignore
    public void contextLoads() {
        System.out.println("ignore");
    }

    @BeforeClass
    public static void beforeClassTest() {
        System.out.println("before class test");
    }

    @Before
    public void beforeTest() {
        System.out.println("before test");
    }

    @Test
    public void Test1() {
        System.out.println("test 1+1=2");
        Assert.assertEquals(2, 1 + 1);
    }

    @Test
    public void Test2() {
        System.out.println("test 2+2=4");
        Assert.assertEquals(4, 2 + 2);
    }

    @After
    public void afterTest() {
        System.out.println("after test");
    }

    @AfterClass
    public static void afterClassTest() {
        System.out.println("after class test");
    }

    @Test(timeout = 1000)
    public void testTimeOut() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("done!");
    }

    @Test(expected = NullPointerException.class)
    public void testNullException() {
        throw new NullPointerException();
    }

}
