/*
 * LibrarianMainMenu class
 * Mathieu Comeau Oct 12 2017
 * 
 * This is a STORY TEST class that tests user story #13
 */

package client.junit.test;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;

public class LibrarianMainMenuTest {

	
	@Test
	public void testLibrarianMainMenu() 
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
		
		//Rule i: librarian chooses Create Title option
		ServerOutput expected2 = new ServerOutput("Please Input Title Info:'ISBN,title'", OutputHandler.CREATETITLE);
		ServerOutput result2 = testIH.processInput("create title", OutputHandler.CLERK);
		boolean match2 = expected2.getOutput().equals(result2.getOutput()) && expected2.getState() == result2.getState();
		assertTrue(match2);
		
		//Step ii: librarian chooses main menu option
		ServerOutput expected3 = new ServerOutput("What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.", InputHandler.CLERK);
		ServerOutput result3 = testIH.processInput("main menu", OutputHandler.CREATETITLE);
		assertEquals(expected3, result3);
	}

}
