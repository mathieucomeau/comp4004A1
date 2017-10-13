package client.junit.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({MonitorSystemTest.class, UserTest.class, LoanTest.class, FeeTest.class, ItemTest.class, TitleTest.class })
public class TestSuite {

}
