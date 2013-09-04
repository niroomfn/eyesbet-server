 package com.eyesbet.business.domain;
 
 public enum BetType
 {  
   spreadPoint("sp"), moneyline("mn"), overUnder("ou"), parlay("parlay"), straightWages("sw"), points("points");
		
			private String shortName;
			
			private BetType(String shortName) {
				
				this.shortName = shortName;
			}
			
			public String getShortName() {
				
				return shortName;
			}

 }

