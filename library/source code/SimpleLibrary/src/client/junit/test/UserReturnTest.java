/*
 * UserReturnTest class
 * Mathieu Comeau Oct 12 2017
 * 
 * This is a STORY TEST class that tests user story #10
 */

package client.junit.test;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;

public class UserReturnTest {

	
	@Test
	public void testReturnBook() 
	{
		OutputHandler testOH = new OutputHandler();
		InputHandler testIH = new InputHandler();
		
		//Step i: user enter user
		ServerOutput expected0 = new ServerOutput("Please Input Username and Password:'username,password'", OutputHandler.USERLOGIN);
		ServerOutput result0 = testIH.processInput("USER", OutputHandler.FINISHWAITING);
		boolean match0 = ((expected0.getOutput().equals(result0.getOutput())) && (expected0.getState() == result0.getState()));
		assertTrue(match0);
		
		//Step i: user enter password
		Output expected1 = new Output("What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.", OutputHandler.USER);
		Output result1 = testOH.userLogin("zhibo@carleton.ca,zhibo");
		assertEquals(expected1, result1);
		
		//Step ii: user chooses return option
		ServerOutput expected2 = new ServerOutput("Please Input Item Info:'useremail,ISBN,copynumber'", OutputHandler.RETURN);
		ServerOutput result2 = testIH.processInput("return", OutputHandler.USER);
		boolean match2 = expected2.getOutput().equals(result2.getOutput()) && expected2.getState() == result2.getState();
		assertTrue(match2);
		
		//Step iii, v: user enters title information
		Output expected3 = new Output("Success!", OutputHandler.USER);
		Output result3 = testOH.returnBook("zhibo@carleton.ca,9781442668584,1");
		assertEquals(expected3, result3);
	}

}
