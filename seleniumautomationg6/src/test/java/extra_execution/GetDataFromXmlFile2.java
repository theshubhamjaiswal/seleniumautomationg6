package extra_execution;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GetDataFromXmlFile2 {
	@Parameters({"un2","pwd2"})
	@Test
	public void login(String username,String password) {
		// string username="admin";
		// String password="admin123"
		//this is hard code we do not write like this
		
		System.out.println(username+" "+password);
	}
}
