package client.junit.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TitleTest.class, ItemTest.class, UserTest.class })
public class TestSuite {

}
