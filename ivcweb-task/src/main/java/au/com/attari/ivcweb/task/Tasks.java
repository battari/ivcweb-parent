package au.com.attari.ivcweb.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import au.com.attari.ivcweb.task.factory.ASXEndOfDayProcessor;
import au.com.attari.ivcweb.task.factory.ComsecFileProcessor;
import au.com.attari.ivcweb.task.factory.RecommendationProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Tasks {

	Logger logger = LoggerFactory.getLogger(Tasks.class);

	@Autowired
	ComsecFileProcessor fileProcessor;

	@Autowired
	ASXEndOfDayProcessor endOfDayProcessor;

	@Autowired
	RecommendationProcessor recommendationProcessor;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"MM/dd/yyyy HH:mm:ss");

	@Scheduled(fixedRate = 120000)
	public void performTask() {

		logger.info("Calling scheduler "
				+ dateFormat.format(new Date()));
		fileProcessor.process();
		endOfDayProcessor.process();
	}

	@Scheduled(initialDelay = 1000, fixedRate = 10000)
	public void performDelayedTask() {

//		logger.info("Delayed Regular task performed at "
//				+ dateFormat.format(new Date()));

	}

	@Scheduled(cron = "0 0/15 * * * *")
	public void performTaskEvery15Ninutes() {
		recommendationProcessor.process();
	}
}
