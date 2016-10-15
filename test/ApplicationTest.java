import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllers.Application;
import play.db.Database;
import play.db.Databases;

import com.google.common.collect.ImmutableMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

    Database database;
    @Before
    public void setupDatabase(){
         database = Databases.inMemory(
                "mydatabase",
                ImmutableMap.of(
                        "MODE", "MYSQL"
                ),
                ImmutableMap.of(
                        "logStatements", true
                )
        );

    }

    @After
    public void shutdownDatabase(){
        database.shutdown();
    }

    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a, equalTo(2));
    }
    @Test
    public void emailFormatCheck(){
        Application a = new Application();
        assertEquals(true,a.checkEmailFormat("abcd@xyz.com"));
        assertEquals(false,a.checkEmailFormat("abcd@xyz"));
        assertEquals(false,a.checkEmailFormat("@xyz"));
        assertEquals(false,a.checkEmailFormat("ab@xyz."));
    }
}
