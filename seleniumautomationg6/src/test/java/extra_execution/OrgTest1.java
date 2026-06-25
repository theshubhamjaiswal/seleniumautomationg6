package extra_execution;

import org.testng.annotations.Test;

public class OrgTest1 {
	
		
		@Test(groups ="reg")
		public void createOrg() {
		
			System.out.println("org Created");
			
		}
		
		@Test(groups ="reg")
		public void modifyOrg() {
		
			System.out.println("org modify");
			
		}
		
		@Test(groups ="system")
		public void deleteOrg() {
		
			System.out.println("org deleted");
			
		}
		

	}

