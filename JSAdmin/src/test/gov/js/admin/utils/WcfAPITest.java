package test.gov.js.admin.utils; 

import gov.js.admin.utils.WcfAPI;
import gov.js.admin.utils.WcfResult;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/** 
* WcfAPI Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 30, 2018</pre> 
* @version 1.0 
*/ 
public class WcfAPITest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: GroupByCountyTotal(int regionId) 
* 
*/ 
@Test
public void testGroupByCountyTotal() throws Exception { 
//TODO: Test goes here...
    WcfResult result = WcfAPI.GroupByCountyTotal(5224);
    assertNotNull(result);
    assertEquals(result.getD().getHospital(),0);
    assertEquals(result.getD().getCkjfz(),33);
    assertEquals(result.getD().getRegionname(),"毕节市");
    assertEquals(result.getD().getCkjtpy(),0);
    assertEquals(result.getD().getCnyfdy(),98);
    assertEquals(result.getD().getHxcy(),54);
}
} 
