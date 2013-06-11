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

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.domain.BetType
 * JD-Core Version:    0.6.2
 */