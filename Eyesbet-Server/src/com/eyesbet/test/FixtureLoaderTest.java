package com.eyesbet.test;

import java.text.SimpleDateFormat;
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
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		String date = formatter.format(new Date());
		for (int i=0; i < 4; i++) {
			
			fixture = new Fixture();
			fixture.setAway(awayList[i]);
			fixture.setHome(homeList[i]);
			fixture.setId((awayList[i] + homeList[i]).hashCode());
			fixture.setSchedule(date+" 0"+(i+1)+":00 PM");
			
			list.add(fixture);
			
		}
		
		
		fixtures.setFixtures(list, Leagues.NBA);
		
	}
	
	

}
