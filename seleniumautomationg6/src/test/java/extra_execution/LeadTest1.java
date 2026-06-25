package extra_execution;

import org.testng.annotations.Test;

public class LeadTest1 {



	

		@Test(groups ="reg")
		public void createLead() {
		
			System.out.println("Lead Created");
			
		}
		
		@Test(groups ="smoke")
		public void modifyLead() {
		
			System.out.println("Lead modify");
			
		}
		
		@Test(groups ="system")
		public void deleteLead() {
		
			System.out.println("Lead deleted");
			
		}
		
	}

