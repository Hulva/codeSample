/**
 * 
 */
package hulva.luva.learn.gson;

import java.util.Arrays;
import java.util.Date;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年12月6日
 *
 */
public class GsonDemoMain {

	public static void main(String[] args) {
		// First way to create a Gson object for faster coding
		Gson gson1 = new Gson();

		// Second way to create a Gson object using GsonBuilder
		Gson gson2 = new GsonBuilder()
				.disableHtmlEscaping()
				.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
				.setPrettyPrinting()
				.serializeNulls()
				.setVersion(1.0)
				.create();
		
		Employee employee = new Employee();
		employee.setId(1);
		employee.setFirstName("Lokesh");
		employee.setLastName("Gupta");
		employee.setRoles(Arrays.asList("ADMIN", "MANAGER"));
		Department department = new Department("Finance");
		employee.setDepartment(department);
		 
		// Convert Java object to JSON format
		System.out.println(gson1.toJson(employee));
		// {"id":1,"firstName":"Lokesh","lastName":"Gupta","roles":["ADMIN","MANAGER"],"department":{"deptName":"Finance"}}
		System.out.println(gson2.toJson(employee));
//		{
//			  "Id": 1,
//			  "FirstName": "Lokesh",
//			  "LastName": "Gupta",
//			  "Roles": [
//			    "ADMIN",
//			    "MANAGER"
//			  ],
//			  "Department": {
//			    "DeptName": "Finance"
//			  }
//			}
		
		// Convert JSON to Java objects
		System.out.println(
			    gson1.fromJson("{'id':1,'firstName':'Lokesh','lastName':'Gupta','roles':['ADMIN','MANAGER'],'department':{'deptName':'Finance'}}", 
			    Employee.class));
		System.out.println(
			    gson2.fromJson("{'Id':1,'FirstName':'Lokesh','LastName':'Gupta','Roles':['ADMIN','MANAGER'],'Department':{'DeptName':'Finance'}}", 
			    Employee.class));
		
		// Custom Serialization and De-serialization
		employee.setBirthDay(new Date());
		 
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Date.class, new DateSerializer());
		gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
		gsonBuilder.setVersion(1.2);
		Gson gson = gsonBuilder.create();
		 
		//Convert to JSON
		System.out.println(gson.toJson(employee));
		 
		//Convert to java objects
		System.out.println(gson.fromJson("{'id':1,'firstName':'Lokesh','lastName':'Gupta','roles':['ADMIN','MANAGER'],'birthDay':'17/06/2014'}"
		                            , Employee.class));
	}

}
