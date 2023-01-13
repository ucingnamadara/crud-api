package id.kawahedukasi.service;

import io.quarkus.scheduler.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class SchedulerService {
    Logger logger = LoggerFactory.getLogger(SchedulerService.class);

    @Scheduled(every = "5s")
    public void generateKawahedukasi(){
        logger.info("kawahedukasi");
    }

    @Scheduled(cron = "0 0 18 ? * SAT,SUN *")
    public void generateEveryWeekend(){
        logger.info("WEEKEND!");
    }

    @Scheduled(cron = "59 59 23 31 12 ? *")
    public void generateHappyDayOfYear(){
        logger.info("HAPPY LAST DAY OF YEAR!");
    }
}
