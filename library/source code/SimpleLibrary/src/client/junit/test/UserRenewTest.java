/*
 * UserRenewTest class
 * Mathieu Comeau Oct 12 2017
 * 
 * This is a STORY TEST class that tests user story #8
 */

package client.junit.test;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;

public class UserRenewTest {

	
	@Test
	public void testRenewBook() 
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
		
		//Step ii: user chooses renew option
		ServerOutput expected2 = new ServerOutput("Please Input Title Info:'useremail,ISBN,copynumber'", OutputHandler.RENEW);
		ServerOutput result2 = testIH.processInput("renew", OutputHandler.USER);
		boolean match2 = expected2.getOutput().equals(result2.getOutput()) && expected2.getState() == result2.getState();
		assertTrue(match2);
		
		//Step iii: user enters title information
		Output expected3 = new Output("Outstanding Fee Exists!", OutputHandler.USER);
		Output result3 = testOH.renew("zhibo@carleton.ca,9781442668584,1");		
		assertEquals(expected3, result3);
		
		//step iv: user has outstanding fees
		UserPayFeeTest uf = new UserPayFeeTest();
		uf.testPayFee();
		
		//step iii, v: user enters title information, successful
		Output expected4 = new Output("Success!", OutputHandler.USER);
		Output result4 = testOH.renew("zhibo@carleton.ca,9781442668584,1");		
		assertEquals(expected3, result3);
		
	}

}
