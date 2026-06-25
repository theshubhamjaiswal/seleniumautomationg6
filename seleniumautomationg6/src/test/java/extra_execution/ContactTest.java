package extra_execution;

import org.testng.annotations.Test;

public class ContactTest {

		
		@Test(groups ="Smoke")
		public void createContact() {
		
			System.out.println("Contact Created");
			}
		
		@Test(groups ="reg")
		public void modifyContact() {
		
			System.out.println("Contact modify");
			
		}
		
		@Test(groups ="system")
		public void deleteContact() {
		
			System.out.println("Contact deleted");
			
		}
		
	}


