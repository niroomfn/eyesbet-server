<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  import="com.eyesbet.business.domain.*,java.util.Set,java.util.List"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
 <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" /> 
 <script src="scripts/jquery-1.8.3.js"></script> 
 <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>  
 <link rel="stylesheet" href="/resources/demos/style.css" />
 <script type="text/javascript">
                           

 $(function() { $( "#types" ).accordion({ 
 	    collapsible: true 
 	    }); 
 	   });

function openWindow() {
 
 var url= document.URL;
 url = url.substring(0, url.lastIndexOf("/")) + "/trackBets";

 var x = screen.width - 200;
  var y = screen.height - 200;
window.open(url,'Tracker','width='+x+',height='+y+',toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no, resizable=yes'); 


}
 </script>

</head>
<body>
<div style="margin-left:10px;margin-right:10px;margin-top:15px">
 <a href="displayLeagues" >Create Bet</a>

 <%
 Bets bets = (Bets)session.getAttribute("bets");
 if (bets == null || bets.size() == 0) {
  %>
 
 <div>No saved bets found for your account</div>
 <% } else { %>
 <form name="myform">
 <div id="types" style="width:100%">
<%
	
	Set<String> set = bets.keySet();
	List<Bet> list = null;
	for (String type: set) {
		list = bets.get(type);
 %>
 	
 
   <h3><%=type %> bets   </h3>
   <div>
  
 	
 	<%
 	 List<Game> games = null;
 	 for (Bet bet: list) {
 	     games = bet.getGames();
 	 %>
 	 
 	 <table  width="100%" style=" font-size:small;margin-bottom:20px;border-style: solid;border-width: 2px;border-color: black" cellpadding="5">
 	   <tr  style="background-color:silver;">
 	   <td colspan="3">
 	    
 	    <table style="width:100%;table-layout: fixed; font-size: small">
 	     <tr> <td width="40%">
 	     
 	 	</td> 
 	   <td style=" font-weight: bold" width="40%" > 
 	   
 	    Status:  <%=bet.getStatusText() %>
 	   
 	   
 	    </td> <td align="right"> <a href="removeBet?betId=<%=bet.getId()%>">Remove Bet</a></td> </tr>
 	    
 	    </table>

 	   </td> </tr>
 	   <% for (Game game: games) { %>
 	
 	  
 	
 	 <% if (game.getBet().isMoneyline()) {%>
 	    <tr  ><td width="40%"  ><%=game.getTeams() %></td>  <td >Schedule: <%=game.getSchedule() %>   ( <%=game.getStatusTypeText() %> )  </td>
 	   
 	   <td width="15%" align="right">
 	    Status: <%=game.getStatusText() %>
 	   
 	   </td></tr>
 	  <tr><td colspan="3" style="border-bottom: 1px;border-bottom-style: solid;">
 	 <b>Money Line:</b> <%=game.getBet().getMoneyline() %>
 	 </td> </tr>
 	 <% } else if (game.getBet().isOverUnder()) { %>
 	 	   <tr  ><td width="40%"  ><%=game.getTeams() %></td>  <td >Schedule: <%=game.getSchedule() %>   ( <%=game.getStatusTypeText() %> )  </td>
 	   
 	   <td width="15%" align="right">
 	    Status: <%=game.getOnverUnderStatusText() %>
 	   
 	   </td></tr>
 	   <tr><td colspan="3" style="border-bottom: 1px;border-bottom-style: solid;">
 	   <% if (game.getBet().isUnder()) { 
 	   
 	    out.print("<b>Under:</b> "+game.getBet().getUnderPoints());
 	    } else if (game.getBet().isOver()) {
 	      out.print("<b>Over:</b> "+game.getBet().getOverPoints());
 	    }
 	    
 	    }
 	   
 	   %>    
 	   </td></tr>
 	

 	 <% if (game.getBet().isSpreadPoint()) { %>
 	   <tr  ><td width="40%"  ><%=game.getTeams() %></td>  <td >Schedule: <%=game.getSchedule() %>   ( <%=game.getStatusTypeText() %> )  </td>
 	   
 	   <td width="15%" align="right">
 	    Status: <%=game.getSpreadPointStatusText() %>
 	   
 	   </td></tr>
 	 <tr><td colspan="3" style="border-bottom: 1px;border-bottom-style: solid;">
 	 <b>Spread Point:</b> <%=game.getBet().getSpreadPointTeam() %>: <%=game.getBet().getSpreadPointAndSign() %>
 	 </td></tr>
 	 <%}  %>
 	  
 	    <%} %>
 	   
 	 </table>
 	 
 	 <% } %>
 	 
  </div>
   
   <% } %>
    
  </div>
  <% if (session.getAttribute("liveGames") != null ) { %>
  <div style="margin-top:20px;margin-left:10%" ><input type="button" name="submit" value="Track Live Games" onclick="openWindow()" /></div>  
   <%} %>
  </form>
  <% } %>
</div>
</body>
</html>