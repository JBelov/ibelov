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

    public static void main(String[] args) {
        String config;
        if (args.length > 0) {
            config = args[0];
        } else {
            config = "app";
        }
        ResourceBundle resource = ResourceBundle.getBundle(config);
        DataBase dataBase = new DataBase(resource);
        Parser parser = new Parser(resource);
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
            scheduler.getContext().put("parser", parser);
            scheduler.getContext().put("database", dataBase);
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            LOGGER.error(e.getMessage(), e);
        }


    }

}

