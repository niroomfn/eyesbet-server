<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  import="java.util.Set,com.eyesbet.business.domain.User,com.eyesbet.business.domain.FixtureMap,com.eyesbet.business.domain.Fixture,java.util.Map,java.util.List"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Eyesbet</title>

 
 <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" /> 
 <script src="http://code.jquery.com/jquery-1.8.3.js"></script> 
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />  
	
 
<script> 
       $(function() { 
              $( "#leagues" ).tabs();    });
                  
                  
       </script>
 
<script type="text/javascript">

function validateStraightWage() {
 betType = document.getElementById("betType").value;
 games = document.forms[1].games;
 
 if (betType == "") {
  alert("Please select a bet type to continue");
  return false;
 }
 if (betType == "straightWages") {
 
 	count = 0;
 	for (var i=0; i < games.length; i++) {
 	  if (games[i].checked == true) {
 	  	count++;
 	  }
 	}
 	
 	if (count > 1) {
 	 alert("You must only select one game for Straight Wages");
 	 return;
 	 
 	} else if (count == 1) {
 		document.forms[1].submit();
 		return;
 	}
 
 }
 
 document.forms[1].submit();

}


</script>



</head>
<body>

 <%FixtureMap map = (FixtureMap)request.getAttribute("fixtures");%>

<div style="margin-left:10px;margin-top:20px">

 <div style="margin-bottom:20px">
 <%
   User user = (User)session.getAttribute("user");
   if (user != null) {
  %>
 <div style="margin-bottom:20px;margin-top:20px">Welcome <%=(user.getFirstName() + " " + user.getLastName()) %>  </div>
  <% } else { out.println("Please login or register before you continue");} %>
 <form action="login" method="post">
  <table>
  <% if (request.getAttribute("error") != null) { %>
  <tr> <td colspan="2" style="color:red">Invalid username or password </td>  </tr>
  <% } else if (request.getAttribute("login") != null) { %>
   <tr> <td colspan="2" style="color:red"><%=request.getAttribute("login") %></td>  </tr>
   <% } %>
   <tr> <td>Username: </td> <td> <input type="text" name="username" /> </td> </tr>
    <tr> <td>Password: </td> <td> <input type="password" name="password" /> </td> </tr>
 
  </table>
 <div ><input style="margin-right:15px;margin-top:15px" type="submit" name="submit" value="Login" />   <a href="register.jsp" > Register </a> </div>
  </form>
  <hr/>
 </div>
 <form action="createBet" method="post">
 <div>
 <select id="betType" name="betType" id="betType">
  <option value="">Select a bet type </option>
  <option value="straightWages"> Straight Wages </option>
  <option value="parlay"> Parlay </option>
 </select>
</div>
<div style="width:100%;font-size: small;margin-top:20px" id="leagues">
<ul>       
 <li><a href="#NFL">NFL</a></li>  
      <li><a href="#NBA">NBA</a></li>      
         <li><a href="#MLB">MLB</a></li>  
         <li><a href="#NHL">NHL</a></li>
         
    </ul>
 
 
  <div id="NFL">
  <% Set<Fixture> nflList = map.getFixtures("NFL"); %>

<table style="table-layout: fixed"  width="60%" >
<tr style="font-weight: bold;">
<td width="3%">Select</td>
<td width="10%" >Game</td>
<td width="10%">Schedule</td>
</tr>



</table>
  
  </div>
  <div id="NBA" >
  
   <% Set<Fixture> nbaList = map.getFixtures("NBA"); %>

<table style="table-layout: fixed"  width="60%" >
<tr style="font-weight: bold;">
<td width="3%">Select</td>
<td width="10%" >Game</td>
<td width="10%">Schedule</td>
</tr>


  
  </table>
  
  </div>
  <div id="MLB" ></div>
  <div id="NHL" ></div>
 </div>

</form>
<div style="margin-top:20px"><input type="submit" name="submit" value="Create Bet" onclick="return validateStraightWage()"/></div>

</div>
</body>
</html>