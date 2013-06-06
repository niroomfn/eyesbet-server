<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
   import="com.eyesbet.business.domain.Game,com.eyesbet.business.domain.Bet,java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%	
	Bet bet = (Bet)session.getAttribute("bet");
	List<Game> list = bet.getGames();
 String betType = bet.getBetType().toString();
 String moneylineStyle = "";

%>
<head>
<LINK rel="stylesheet" type="text/css" media="screen"
      href="scripts/style.css">

 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bet Stream</title>

<script type="text/javascript">
 var betType = "<%=betType%>";
function validateOverUnder(game,over_under) {
	
	under =  document.getElementById(game+"_under");
	over  =  document.getElementById(game+"_over");
	
	if (over_under == "over") {
		
		under.value = "";
	} else {
		over.value = "";
	}
	
	if (betType == "straightWages") {
		 favorite = game.substring(0,game.indexOf("_"));
		 underdog = game.substring(game.indexOf("_")+1, game.length);
		 id = favorite+"_spreadPoint";
		 e = document.getElementById(id);
		 e.value = "";
		 id = underdog + "_spreadPoint";
		 e = document.getElementById(id);
		 e.value = "";
		
	}

}

function validateBetType(betType) {
	inputs = document.getElementsByTagName("input");
	
	if (betType == "moneyline") {
	for (var i=0; i< inputs.length; i++) {
		input = inputs[i];
		if (input.type == "text") {
			input.disabled = "disabled";
		} else if (input.type == "radio" && input.name.indexOf("moneyline") > 0) {
			input.disabled = "";
		}
	}
	
	} else if (betType == "points") {
		
		for (var i=0; i< inputs.length; i++) {
			input = inputs[i];
			if (input.type == "radio" && input.name.indexOf("moneyline") > 0) {
				input.checked = false;
				input.disabled = "disabled";
			} else if (input.type == "text" ) {
				input.disabled = "";
			}
		}
		
	}
	
	
}

function validatePointSpread(game) {
	if (betType == "straightWages") {
		over = document.getElementById(game+"_over");
		under = document.getElementById(game+"_under");
		over.value = "";
		under.value="";
	}
	
	
}

</script>


</head>
<body onload="validateBetType('points')" >



<form name="myform" action="<%=request.getContextPath()%>/saveBet" >



<div class="betTable">
<table  width="100%" style=" table-layout:fixed; border-bottom: 1px;border-bottom-style: solid;" cellpadding="5" >
<tr  style="font-weight: bold;" >
<td width="30%">

 </td>
<td  width="25%" style="border-right: 1px;border-right-style: solid;border-left: 1px;border-left-style: solid; ">
<input type="radio"   name="betType" value="moneyline" onclick="validateBetType('moneyline')"/> Money Line </td>
<td > <input type="radio" checked="checked" name="betType"  value="points" onclick="validateBetType('points')"/> Points </td>

</tr>
</table>

<table style="table-layout: fixed;"  cellpadding="5" width="100%">
<tr style="font-weight: bold;">
<td width="30%" > Team </td>
<td width="25%">Money Line</td>

<td >Spread Point</td>
<td >Over/Under</td>


</tr>
</table>
<table style="table-layout: fixed" width="100%" cellpadding="5">

<%
	for (Game g: list) {
%>


<tr >

<td width="30%"><%=g.getHome().getName() %> </td>
<td><input type="radio" id="<%=g.getGameBetName()+"_moneyline" %>" name="<%=g.getGameBetName()+"_moneyline" %>" value="<%=g.getHome().getName() %>"  /> </td>

<td style="<%=moneylineStyle%>">

<select name="<%=g.getHome().getName() %>_fu" >
 <option value="+" > + </option>
 <option value="-" > - </option>
</select>
<input type="text" id="<%=g.getHome().getName() %>_spreadPoint" name="<%=g.getHome().getName() %>_spreadPoint" size="3" onkeydown="validatePointSpread('<%=g.getGameBetName()  %>')" /> </td>
<td style="<%=moneylineStyle%>">Over <input type="text" id="<%=g.getGameBetName() %>_over" name="<%=g.getGameBetName() %>_over" size="3" id="<%=g.getGameBetName() %>_over" name="<%=g.getGameBetName() %>_over" onkeydown="validateOverUnder('<%=g.getGameBetName() %>','over')"/> </td>
</tr>

<tr  >
<td><%=g.getAway().getName() %>  </td>
<td width="25%"><input type="radio" id="<%=g.getGameBetName()+"_moneyline" %>" name="<%=g.getGameBetName() %>_moneyline" value="<%=g.getAway().getName() %>"  /> </td>

<td style="<%=moneylineStyle%>">
<select name="<%=g.getAway().getName() %>_fu" >
 <option value="+" > + </option>
 <option value="-" > - </option>
</select>
<input type="text" id="<%=g.getAway().getName() %>_spreadPoint" name="<%=g.getAway().getName() %>_spreadPoint" size="3" onkeydown="validatePointSpread('<%=g.getGameBetName() %>')" /> </td>

<td style="<%=moneylineStyle%>">Under <input type="text" size="3" id="<%=g.getGameBetName() %>_under" name="<%=g.getGameBetName() %>_under" onkeydown="validateOverUnder('<%=g.getGameBetName() %>','under')"/> </td>
</tr>
<tr><td style="border-bottom-width:1px;border-bottom-style:solid;" colspan="4"></td></tr>

<% } %>

</table>

<input type="submit" name="submit" value="Save Bet" />

</div>
</form>



</body>
</html>