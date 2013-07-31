package com.eyesbet.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.eyesbet.business.domain.Fixture;
import com.eyesbet.business.domain.Fixtures;
import com.eyesbet.business.domain.Fixtures.Leagues;
public class FixtureLoaderTest {
	
	
	private String [] homeList = {"Bulls","Knicks","Lakers","Spurs"};
	private String [] awayList = { "Heat", "Magic", "Bobcats", "Nuggets" };

	
	public void loadNBAFixtures() {
		
		Fixture fixture = null;
		Fixtures fixtures = Fixtures.getInstance();
		Set<Fixture> list = new HashSet<Fixture>();
		SimpleDateFormat formatter = new SimpleDateFormat("EEEE MMM dd yyyy");
		Date today = new Date();
		String date = formatter.format(today);
		
		for (int i=0; i < 3; i++) {
			fixture = new Fixture();
			fixture.setAway(awayList[i]);
			fixture.setHome(homeList[i]);
			fixture.setId((awayList[i] + homeList[i]).hashCode());
			fixture.setSchedule(date+" 0"+(i+1)+":00 PM");
			list.add(fixture);
			
		}
		
		// add a future game
		
		fixture = new Fixture();
		fixture.setAway(awayList[3]);
		fixture.setHome(homeList[3]);
		fixture.setId((awayList[3] + homeList[3]).hashCode());
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		date = formatter.format(cal.getTime());
		fixture.setSchedule(date+" 0"+4+":00 PM");
		list.add(fixture);
		
		fixtures.setFixtures(list, Leagues.NBA);
		
	}
	
	
	
	
	public static void main (String [] args) {
		
		
		FixtureLoaderTest test = new FixtureLoaderTest();
		test.loadNBAFixtures();
	}
	
	

}
