package test.gov.js.service; 

import gov.js.service.ReportService;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* ReportService Tester. 
* 
* @author <Authors name> 
* @since <pre>ÁùÔÂ 1, 2018</pre> 
* @version 1.0 
*/ 
public class ReportServiceTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: addnew(ReportDTO report) 
* 
*/ 
@Test
public void testAddnew() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: update(ReportDTO report) 
* 
*/ 
@Test
public void testUpdate() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: markDeleted(int reportId) 
* 
*/ 
@Test
public void testMarkDeleted() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getById(int reportId) 
* 
*/ 
@Test
public void testGetById() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getAll(int companyId) 
* 
*/ 
@Test
public void testGetAllCompanyId() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getAll() 
* 
*/ 
@Test
public void testGetAll() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getAll(Date startTime, Date endTime) 
* 
*/ 
@Test
public void testGetAllForStartTimeEndTime() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getFirstDayOfMonth(int year, int month) 
* 
*/ 
@Test
public void testGetFirstDayOfMonth() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getLastDayOfMonth(int year, int month) 
* 
*/ 
@Test
public void testGetLastDayOfMonth() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getFirstDayOfNextMonth(String dateStr) 
* 
*/ 
@Test
public void testGetFirstDayOfNextMonth() throws Exception { 
//TODO: Test goes here...
    ReportService reportService = new ReportService();

    //System.out.println(reportService.getFirstDayOfNextMonth("2018-05-31"));
}

@Test
public void getfirstDayOfNextQuarter() throws Exception {
//TODO: Test goes here...
    ReportService reportService = new ReportService();
    //System.out.println(reportService.getfirstDayOfNextQuarter (2018, 5));
}

@Test
public void getfirstDayOfNowQuarter() throws Exception {
//TODO: Test goes here...
    ReportService reportService = new ReportService();

    System.out.println(reportService.getfirstDayOfNowQuarter(2018,5));
}



} 
