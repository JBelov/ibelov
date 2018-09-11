package sqlru;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class App {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static ResourceBundle resource;
    private static DataBase dataBase;
    private static Parser parser;

    /**
     * Returns current database class.
     *
     * @return database.
     */
    public static DataBase getDataBase() {
        return dataBase;
    }

    /**
     * Returns current parser class.
     *
     * @return database.
     */
    public static Parser getParser() {
        return parser;
    }

    /**
     * Returns current resources bundle.
     *
     * @return database.
     */
    public static ResourceBundle getResource() {
        return resource;
    }

    public static void main(String[] args) {
        String config;
        if (args.length > 0) {
            config = args[0];
        } else {
            config = "app";
        }

        resource = ResourceBundle.getBundle(config);
        dataBase = new DataBase();
        parser = new Parser();

        String cron = resource.getString("cron");
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try {
            scheduler = schedulerFactory.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        JobDetail job = JobBuilder.newJob(Job.class)
                .withIdentity("parser")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger")
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .forJob("parser")
                .build();
        try {
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            LOGGER.error(e.getMessage(), e);
        }


    }

}

