<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  import="java.util.List,java.util.Set,com.eyesbet.business.domain.*" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="scripts/jquery-1.8.3.js"></script>

<script type="text/javascript">
var http= document.URL;
 http = http.substring(0, http.lastIndexOf("/")) + "/monitor";

<% if (request.getAttribute("close") != null) { %>
 window.close();
 <% } %>

var go = true;
var spans = document.getElementsByTagName("span");
function sendData() {
 
 $(document).ready(function() {  


$.ajax({
        type: 'POST',
        url: http,
        dataType: "xml",
        success: function (xml) { 
     		document.getElementById("startupMessage").style.display = "none";
   			document.getElementById("content").style.display = "";
         $(xml).find("bet").each(function()
  			{
		    setBetStatus($(this).attr("s"), $(this).attr("id"));
		  }); 

         $(xml).find("game").each(function()
  			{
		    setGameScores($(this).attr("h"), $(this).attr("hs"), $(this).attr("a"), $(this).attr("as"),$(this).attr("st"),$(this).attr("stt"),$(this).attr("id"));
		  }); 
         
        
        
       /* if (data.Result)
        {                
           
           
           alert(data);
        } */
        }, 
        error: function(xhr, textStatus, error){
       alert( error);
    },
        }); 
        });

}

function setBetStatus(status, id) {
	color ="";
	
	if (status >= 50) {
		color = "green";
	} else {
		color = "red";
	}
	element = document.getElementById(id);
	element.style.background = color;
	element.innerHTML = (status + "%");
}



function setGameScores(f, fs, u, us, st, stt, id) {

   
  for (var i=0; i < spans.length; i++) {
  	if (spans[i].id == id) {
  	    if (st >= 50) {
  	     spans[i].style.color = "green";
  	    } else {
  	    	spans[i].style.color = "red";
  	    }
  	    spans[i].innerHTML =  stt;
  	}
  	if (spans[i].id == f) {
  		if (fs > us) {
  		 spans[i].style.color = "green";
  		} else {
  			spans[i].style.color = "red";
  		}
  		spans[i].innerHTML = fs;
  	}
  	if (spans[i].id == u) {
  		if (us > fs) {
  		 spans[i].style.color = "green";
  		} else {
  			spans[i].style.color = "red";
  		}
  		spans[i].innerHTML = us;
  	}
  }

}

function sendDataInterval(go ) {

if (go) {
  
   setInterval('sendData()',6000);
  
  	
 } else {
   window.close();
 }
}


function closeWindow() {
 document.getElementById('closewindow').submit(); 
}

</script>

</head>
<body onload="sendDataInterval(true)">
 <div align="center" style="font-weight:bold; font-size:large; margin-top:30px;" id="startupMessage">Loading live games....</div>
<div id="content" style="display:none"	>
<table   width="100%" style="background-color:black;" cellpadding="5" cellspacing="2">
<tr style="color:white; font-weight: bold;">
<td>Non Live Games</td>  <td>Live Games </td>  <td width="5%">Status</td>
</tr>


<%
Bets bets = (Bets)session.getAttribute("trackBets");
List<Bet> list = bets.getBets();


 for (Bet bet: list) {
 
 %>
 <tr   style="font-weight:bold;  background-color: silver;">
 <td colspan="2" >
   
   Bet Type: <%=bet.getBetType() %>
 
 </td>
 <td  id="<%=bet.getId() %>" align="center">  </td>
 </tr>
<tr  style="background-color: silver;">
 <td style="width:50%">

 
 <table >
  <% 
  	List<Game> nonLiveGames = bet.getNonLiveGames();;
  	for (Game game: nonLiveGames) {
   %>
    <tr ><td> <%=game.getAway().getName() %></td> <td> <%=game.getAway().getScore() %>  </td> <td>@</td> <td> <%=game.getHome().getName() %></td>
    <td> <%=game.getHome().getScore() %> </td><td style="font-weight: bold; "> <span style="color:green" > <%=game.getStatusTypeText() %> </span> </td> </tr>
    <tr>
    <td colspan="6"	> 
    <ul compact="compact" type="disc"  style="margin-top:1px;" >
    <%GameBet gamebet =  game.getBet() ;
     
       if (gamebet.isMoneyline()) {
          out.println("<li><b>Money Line:</b> "+gamebet.getMoneyline()+"</li>");
        }if (gamebet.isOverUnder()) {
           if (gamebet.isOver())
           out.println("<li><b>Over:</b> "+gamebet.getOverPoints()+"</li>");
           else 
             out.println("<li><b>Under:</b> "+gamebet.getUnderPoints()+"</li>");
            
        }if (gamebet.isSpreadPoint()) {
            
             out.println("<li><b>Point Spread:</b> "+gamebet.getSpreadPointTeam()+": "+gamebet.getSpreadPointAndSign()+"</li>");
            
        
        }
     
     
      %>
      
      </ul>
     </td>
    </tr>
  
  <% } %>
  </table>
 
 
 </td>
 
 

  <td width="50%" colspan="2" id="score_<%=bet.getId() %>">
 <table >
  <% 
  	Set<Game> games = bet.getLiveGames();
  	for (Game game: games) {
   %>
    <tr ><td> <%=game.getAway().getName() %></td> <td> <span style="font-weight:bold; width:10%" id="<%=game.getAway().getName()%>"></span></td> <td>@</td> <td> <%=game.getHome().getName() %></td>
    <td> <span style="font-weight:bold; width:15%" id="<%=game.getHome().getName()%>"></span></td><td  style="font-weight: bold" >  <span style="font-weight: bold" id="<%=game.getGameId()  %>"></span> </td> </tr>
    <tr>
    <td colspan="6"	> 
    <ul compact="compact" type="disc"  style="margin-top:1px;" >
    <%GameBet gamebet =  game.getBet() ;
     
       if (gamebet.isMoneyline()) {
          out.println("<li><b>Money Line:</b> "+gamebet.getMoneyline()+"</li>");
        }if (gamebet.isOverUnder()) {
           if (gamebet.isOver())
           out.println("<li><b>Over:</b> "+gamebet.getOverPoints()+"</li>");
           else 
             out.println("<li><b>Under:</b> "+gamebet.getUnderPoints()+"</li>");
            
        }if (gamebet.isSpreadPoint()) {
            
             out.println("<li><b>Point Spread:</b> "+gamebet.getSpreadPointTeam()+": "+gamebet.getSpreadPointAndSign()+"</li>");
            
        
        }
     
     
      %>
      
      </ul>
     </td>
    </tr>
  
  <% } %>
  </table>
  </td>  
</tr>
<tr style="background-color: white"><td colspan="3"></td></tr>
<%} %>
</table>
<form id="closewindow" action="closeWindow">
<div><input type="submit" value="Close Window" onclick="closeWindow()" /></div>
</form>
</div>
</body>


</html>