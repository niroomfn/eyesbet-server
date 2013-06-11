package com.eyesbet.app.thread;

import com.eyesbet.app.xml.FeedSaxParser;
import com.eyesbet.app.xml.FixtureHandler;
import com.eyesbet.business.domain.Fixture;
import com.eyesbet.business.domain.Fixtures;
import java.util.Set;

public class SaxFeedThread extends FeedThread {
	public SaxFeedThread(Set<Fixture> list, String url, Fixtures.Leagues league) {
		super(list, url, league);
	}

	public void run() {
		FixtureHandler handler = new FixtureHandler(league);
		try
		{
			new FeedSaxParser(this.url, handler);
			
			this.list.addAll(handler.getFixtures());
			}
		catch (Exception e)
		{
			e.printStackTrace();
			}
		}
	
}
