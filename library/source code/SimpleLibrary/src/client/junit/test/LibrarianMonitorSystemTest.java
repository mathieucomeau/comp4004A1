/*
 * LibrarianMonitorSystemTest class
 * Mathieu Comeau Oct 12 2017
 * 
 * This is a STORY TEST class that tests user story #6
 */

package client.junit.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import server.logic.handler.InputHandler;
import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;
import server.logic.model.Title;
import server.logic.model.User;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;

public class LibrarianMonitorSystemTest {

	
	@Test
	public void testLibrarianMonitorSystem() 
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
		
		//Step ii: librarian chooses Monitor System option
		ServerOutput expected2 = new ServerOutput("Monitoring System", InputHandler.MONITORSYSTEM);
		ServerOutput result2 = testIH.processInput("monitor system", OutputHandler.CLERK);
		boolean match2 = expected2.getOutput().equals(result2.getOutput()) && expected2.getState() == result2.getState();
		assertTrue(match2);
		
		//Step ii, iii: librarian monitors system
		List<Title> titleList = TitleTable.getInstance().getTitleTable();
		List<User> userList = UserTable.getInstance().getUserTable();
		
		String outStr = "";
		
		outStr += "ALL BOOK TITLES: \n";
		for(int i = 0; i < titleList.size(); i++)
		{
			outStr += titleList.get(i).getBooktitle();
			outStr += "\n";
		}
		
		outStr += "\n";
		outStr += "ALL USERS: \n";
		for(int i = 0; i < userList.size(); i++)
		{
			outStr += userList.get(i).getUsername();
			outStr += "\n";
		}
		
		Output expected3 = new Output(outStr, OutputHandler.CLERK);
		Output result3 = testOH.monitorSystem();
		assertEquals(expected3, result3);
	}

}
