package com.eyesbet.business.quartz;

import java.util.Calendar;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.eyesbet.app.thread.SaxFeedThread;
import com.eyesbet.business.domain.Fixture;
import com.eyesbet.business.domain.Fixtures;
import com.eyesbet.business.domain.Fixtures.Leagues;
import com.eyesbet.business.domain.Params;
import com.eyesbet.business.domain.RequestBuilder;
import com.eyesbet.util.DateTime;

public class DailyFixtureJob implements Job {

	private static Logger logger = Logger.getLogger(DailyFixtureJob.class);

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		Calendar calendar = Calendar.getInstance();

		logger.info("Start of execute (Date: "+ DateFormatUtils.format(calendar.getTime(), "MM-dd-yyyy hh:mm")+ ")...");

		Params params = new Params(DateTime.getDefaultTimeZone());
		params.setGames("fixtures");

		String url = null;
		Set<Fixture> list = null;
		Leagues[] leagues = Fixtures.Leagues.values();
		SaxFeedThread thread = null;

		calendar.add(Calendar.DAY_OF_MONTH, 2);

		params.setDate(DateTime.convertDateToFeedDate(calendar.getTime()));
		Fixtures fixtures = Fixtures.getInstance();
		for (Leagues league : leagues) {
			try {
				list = new TreeSet<Fixture>();

				url = new String(RequestBuilder.buildRequest(league, params));

				thread = new SaxFeedThread(list, url, league);
				thread.run();
				logger.info("League: "+league.toString() + " Size: "+list.size());
				if (list.size() > 0)
					fixtures.appendFixtures(list, league);
			} catch (Exception e) {
				logger.error("",e);
			}
		}
		
		
		logger.info("End of execute daily fixtures.");

	}

}
