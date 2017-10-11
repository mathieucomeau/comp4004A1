package client.junit.test;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;

public class MonitorSystemTest {

	@Test
	public void testMonitorSystem() {
		OutputHandler testOH = new OutputHandler();
		Output expectedOut = new Output("Success!", OutputHandler.USER);
		Output result = testOH.monitorSystem();		
		
		assertEquals(expectedOut, result);
	}

}
