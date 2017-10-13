/*
 * LibrarianAddBookTest class
 * Mathieu Comeau Oct 12 2017
 * 
 * This is a STORY TEST class that tests user story #1
 */

package client.junit.test;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;

public class LibrarianAddBookTest {

	
	@Test
	public void testLibrarianAddBook() 
	{
		OutputHandler testOH = new OutputHandler();
		InputHandler testIH = new InputHandler();
		
		//Step i: librarian enter clerk
		ServerOutput expected0 = new ServerOutput("Please Input The Password:", OutputHandler.CLERKLOGIN);
		ServerOutput result0 = testIH.processInput("CLERK", OutputHandler.FINISHWAITING);
		boolean match0 = ((expected0.getOutput().equals(result0.getOutput())) && (expected0.getState() == result0.getState()));
		assertTrue(match0);
		
		//Step i: librarian input password
		Output expected1 = new Output("What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.", OutputHandler.CLERK);
		Output result1 = testOH.clerkLogin("admin");
		assertEquals(expected1, result1);
		
		//Step ii: librarian chooses Create Title option
		ServerOutput expected2 = new ServerOutput("Please Input Title Info:'ISBN,title'", OutputHandler.CREATETITLE);
		ServerOutput result2 = testIH.processInput("create title", OutputHandler.CLERK);
		boolean match2 = expected2.getOutput().equals(result2.getOutput()) && expected2.getState() == result2.getState();
		assertTrue(match2);
		
		//Step ii: librarian enter title information
		Output expected3 = new Output("Success!", OutputHandler.CLERK);
		Output result3 = testOH.createTitle("1313131313131,Uncle John's Bathroom Reader");
		assertEquals(expected3, result3);
		
		//Step iii: librarian chooses Create Item option
		ServerOutput expected4 = new ServerOutput("Please Input Item Info:'ISBN'", OutputHandler.CREATEITEM);
		ServerOutput result4 = testIH.processInput("create item", OutputHandler.CLERK);
		boolean match4 = expected4.getOutput().equals(result4.getOutput()) && expected4.getState() == result4.getState();
		assertTrue(match4);
		
		//Step iv, v: librarian enter item information
		Output expected5 = new Output("Uncle John's Bathroom Reader", OutputHandler.CLERK);
		Output result5 = testOH.createItem("1313131313131");
		assertEquals(expected5, result5);
	}

}
