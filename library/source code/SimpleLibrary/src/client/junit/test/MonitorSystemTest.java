package client.junit.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.model.Title;
import server.logic.model.User;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;

public class MonitorSystemTest {

	@Test
	public void testMonitorSystem() {
		OutputHandler testOH = new OutputHandler();
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
		Output expectedOut = new Output(outStr, OutputHandler.CLERK);
		Output result = testOH.monitorSystem();		
		
		assertEquals(expectedOut, result);
	}

}
