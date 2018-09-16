package sqlru;

import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;

import java.sql.Timestamp;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class Job implements org.quartz.Job {
    @Override
    public void execute(JobExecutionContext context) {
        DataBase dataBase = null;
        Parser parser = null;
        try {
            dataBase = (DataBase) context.getScheduler().getContext().get("database");
            parser = (Parser) context.getScheduler().getContext().get("parser");

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        Timestamp lastUpdate = dataBase.getLast();
        dataBase.add(parser.parse(lastUpdate));
    }
}
