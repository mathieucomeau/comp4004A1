package client.junit.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ LibrarianAddBookTest.class, LibrarianAddUserTest.class, LibrarianLogOutTest.class,
		LibrarianMainMenuTest.class, LibrarianMonitorSystemTest.class,
		 MonitorSystemTest.class, UserBorrowTest.class, UserLogOutTest.class, UserMainMenuTest.class, UserRenewTest.class, UserReturnTest.class, LibrarianRemoveItemsTest.class, LibrarianRemoveTitleTest.class, LibrarianRemoveUsersTest.class, })
public class StoryTestSuite {

}
