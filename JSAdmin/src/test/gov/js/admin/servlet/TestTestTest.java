package test.gov.js.admin.servlet; 

import gov.js.admin.servlet.TestTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* TestTest Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 29, 2018</pre> 
* @version 1.0 
*/ 
public class TestTestTest {

    TestTest test = new TestTest();
@Before
public void before() throws Exception {

    test.setMsg("123");
} 

@After
public void after() throws Exception {

} 

/** 
* 
* Method: getMsg() 
* 
*/ 
@Test
public void testGetMsg() throws Exception { 
//TODO: Test goes here...

    String msg = test.getMsg();
    Assert.assertEquals(msg, "123");


} 

/** 
* 
* Method: setMsg(String msg) 
* 
*/ 
@Test
public void testSetMsg() throws Exception { 
//TODO: Test goes here... 
} 


} 
