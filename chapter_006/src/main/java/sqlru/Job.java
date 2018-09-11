package sqlru;

import org.quartz.JobExecutionContext;

import java.sql.Timestamp;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class Job implements org.quartz.Job {
    @Override
    public void execute(JobExecutionContext context) {
        DataBase dataBase = App.getDataBase();
        Parser parser = App.getParser();
        Timestamp lastUpdate = dataBase.getLast();
        dataBase.add(parser.parse(lastUpdate));
    }
}
