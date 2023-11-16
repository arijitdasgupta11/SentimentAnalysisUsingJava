package com.analysis.brand;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DateChangeTracker {

	private static String lastDate;

	public static void startAnalysis() {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(DateChangeTracker::checkDateChange, 0, 15, TimeUnit.SECONDS);
	}

	private synchronized static void checkDateChange() {
		String currentDate = getCurrentDate();

		if (!currentDate.equals(lastDate)) {

			System.out.println("-----------------------sending-----------------------");
			lastDate = currentDate;
			Subscribers.sendMail();
		}

	}

	private synchronized static String getCurrentDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(new Date());
	}
}
