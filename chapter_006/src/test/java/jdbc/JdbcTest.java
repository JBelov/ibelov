package jdbc;

import org.junit.Test;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class JdbcTest {

    @Test
    public void test() {
        LocalTime start = LocalTime.now();
        App app = new App();
        assertThat(app.start(1000000, "lite"), is(500000500000L));
        LocalTime stop = LocalTime.now();
        long millis = ChronoUnit.MILLIS.between(start, stop);
        System.out.printf("Time spent is about %d mills", millis);
        assertTrue(millis < 300000);
    }
}