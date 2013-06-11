 package com.eyesbet.business.domain;
 
 import java.util.HashMap;
 import java.util.Set;
 
 @SuppressWarnings("serial")
public class FixtureMap extends HashMap<String, Set<Fixture>>
 {
   public Set<Fixture> getFixtures(String league)
   {
     return get(Fixtures.Leagues.valueOf(league));
   }
 }
