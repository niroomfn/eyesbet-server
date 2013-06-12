package com.eyesbet.test;

import java.util.HashSet;
import java.util.Set;

import com.eyesbet.business.domain.Fixture;
import com.eyesbet.business.domain.Fixtures;
import com.eyesbet.business.domain.Fixtures.Leagues;
public class FixtureLoaderTest {
	
	
	
	
	public void loadNBAFixtures() {
		
		Fixture fixture = null;
		Fixtures fixtures = Fixtures.getInstance();
		Set<Fixture> list = new HashSet<Fixture>();
		
		for (int i=0; i < 3; i++) {
			
			fixture = new Fixture();
			fixture.setAway("Away_"+i);
			fixture.setHome("home_"+i);
			fixture.setId(i);
			fixture.setSchedule("06-12-2013 0"+(i+1)+":00:00 PM");
			
			list.add(fixture);
			
		}
		
		
		fixtures.setFixtures(list, Leagues.NBA);
		
	}
	
	

}
