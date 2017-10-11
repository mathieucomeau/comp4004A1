package client.junit.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;

public class ItemTest {

	@Parameter(0)
	public String t_ISBN_exists = "9781442667181"; 	//title already exists
	
	@Parameter(1)
	public String t_ISBN_invalid = "1234";			//too short
	
	@Parameter(2)
	public String t_ISBN_notexist = "5558558558543";	//does not exist
	
	@Parameter(3)
	public String t_ISBN_loaned = "9781442668584";		//loaned by Zhibo
	
	@Parameter(4)
	public String t_ISBN_delete_notexist = "5558558558543,1";	//does not exist
	
	@Parameter(5)
	public String t_ISBN_delete_loaned = "9781442668584,1";		//loaned by Zhibo
	
	@Parameter(6)
	public String t_ISBN_delete_exists = "9781442667181,1"; 	//title already exists
	
	
	
	@Test
	public void testISBNExists() {
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("Courtesy lost", OutputHandler.CLERK);
		Output result = testOH.createItem(t_ISBN_exists);
		
		assertEquals(expectedOut, result);
	}
	
	@Test
	public void testISBNInvalid()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("Your input should in this format:'ISBN',ISBN should be a 13-digit number", OutputHandler.CREATEITEM);
		Output result = testOH.createItem(t_ISBN_invalid);
		
		assertEquals(expectedOut, result);
	}
	
	@Test
	public void testISBNNotExists()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("The Title Does Not Exists!", OutputHandler.CREATETITLE);
		Output result = testOH.createItem(t_ISBN_notexist);
		
		assertEquals(expectedOut, result);
	}
	
	@Test
	public void testRemoveNonExistant()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("The Item Does Not Exist!", OutputHandler.CLERK);
		Output result = testOH.deleteItem(t_ISBN_delete_notexist);
		
		assertEquals(expectedOut, result);
	}
	
	@Test
	public void testRemoveLoaned()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("Active Loan Exists!", OutputHandler.CLERK);
		Output result = testOH.deleteItem(t_ISBN_delete_loaned);
		
		assertEquals(expectedOut, result);
	}
	
	@Test
	public void testRemoveSuccessful()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("Success!", OutputHandler.CLERK);
		Output result = testOH.deleteItem(t_ISBN_delete_exists);
		
		assertEquals(expectedOut, result);
	}

}
