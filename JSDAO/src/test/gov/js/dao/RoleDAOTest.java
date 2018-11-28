package test.gov.js.dao; 

import gov.js.dao.RoleDAO;
import gov.js.dto.RoleDTO;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import static org.junit.Assert.assertNotNull;

/** 
* RoleDAO Tester. 
* 
* @author <Authors name> 
* @since <pre>ÎåÔÂ 18, 2018</pre> 
* @version 1.0 
*/ 
public class RoleDAOTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: addnew(String roleName) 
* 
*/ 
@Test
public void testAddnew() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: update(long roleId, String roleName) 
* 
*/ 
@Test
public void testUpdate() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: markDeleted(int roleId) 
* 
*/ 
@Test
public void testMarkDeleted() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: toDTO(ResultSet rs) 
* 
*/ 
@Test
public void testToDTO() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getById(int id) 
* 
*/ 
@Test
public void testGetById() throws Exception { 
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
* Method: addRoleIds(int adminUserId, int[] roleIds) 
* 
*/ 
@Test
public void testAddRoleIds() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: updateRoleIds(int adminUserId, int[] roleIds) 
* 
*/ 
@Test
public void testUpdateRoleIds() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getByAdminUserId(int adminUserId) 
* 
*/ 
@Test
public void testGetByAdminUserId() throws Exception { 
//TODO: Test goes here...
    RoleDAO dao = new RoleDAO();
    RoleDTO[] roles = dao.getByAdminUserId(1);
    System.out.println("123123");
    System.out.println(roles.length);
    for(int i=0; i < roles.length; i++){
        System.out.println(roles[i].getId());
        System.out.println(roles[i].getName());
    }
    System.out.println();
    assertNotNull(roles);

} 


} 
