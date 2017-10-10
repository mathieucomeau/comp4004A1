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
	
	
	@Test
	public void testISBNExists() {
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("Success!", 2);
		Output result = testOH.createItem(t_ISBN_exists);
		
		assertEquals(expectedOut, result);
	}
	
	@Test
	public void testISBNInvalid()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("Your input should in this format:'ISBN',ISBN should be a 13-digit number", 6);
		Output result = testOH.createItem(t_ISBN_invalid);
		
		assertEquals(expectedOut, result);
	}
	
	@Test
	public void testISBNNotExists()
	{
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("The Title Does Not Exists!", 5);
		Output result = testOH.createItem(t_ISBN_notexist);
		
		assertEquals(expectedOut, result);
	}

}