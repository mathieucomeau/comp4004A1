/*
 * LibrarianRemoveItemsTest class
 * Mathieu Comeau Oct 12 2017
 * 
 * This is a STORY TEST class that tests user story #4
 */

package client.junit.test;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;

public class LibrarianRemoveItemsTest {

	
	@Test
	public void testLibrarianRemoveItems() 
	{
		OutputHandler testOH = new OutputHandler();
		InputHandler testIH = new InputHandler();
		
		//Step i: librarian input clerk
		ServerOutput expected0 = new ServerOutput("Please Input The Password:", OutputHandler.CLERKLOGIN);
		ServerOutput result0 = testIH.processInput("CLERK", OutputHandler.FINISHWAITING);
		boolean match0 = ((expected0.getOutput().equals(result0.getOutput())) && (expected0.getState() == result0.getState()));
		assertTrue(match0);
		
		//Step i: librarian input password
		Output expected1 = new Output("What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.", OutputHandler.CLERK);
		Output result1 = testOH.clerkLogin("admin");
		assertEquals(expected1, result1);
		
		//Step ii: librarian chooses Create Title option
		ServerOutput expected2 = new ServerOutput("Please Input Item Info:'ISBN,copynumber'", OutputHandler.DELETEITEM);
		ServerOutput result2 = testIH.processInput("delete item", OutputHandler.CLERK);
		boolean match2 = expected2.getOutput().equals(result2.getOutput()) && expected2.getState() == result2.getState();
		assertTrue(match2);
		
		//Step ii, iii: librarian enter title information
		Output expected3 = new Output("Success!", OutputHandler.CLERK);
		Output result3 = testOH.deleteItem("9781442667181,1");
		assertEquals(expected3, result3);
	}

}
