<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   import="com.eyesbet.business.domain.Fixtures" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Eyesbet Fixtures</title>

<script type="text/javascript">

 function command(cmd) {
 
  command = document.getElementById("cmd");
  
  command.value = cmd;
 
  document.myform.submit();
 
 }


</script>



</head>
<body>

<div style="margin-top:20px;margin-lef:20px;margin-bottom:20px">

<form action="fixtures"  name="myform">
<select name="league">
<%  Fixtures fixtures = Fixtures.getInstance();  

String [] leagues = fixtures.getLeagues();
for (String league: leagues) {
%>
 <option><%=league %> </option>
 <% } %>
</select>


<div style="margin-top:20px">
 Provide Dates: <input type="text" name="dates" size="30"  />

 </div>
 
 <div style="margin-top:20px">    
 <input type="hidden" id="cmd" name="cmd" />
 <input type="submit" name="submit" value="Load Default" onclick="command('defalue')" />
 <input type="submit" name="submit" value="Load for dates" onclick="command('dates')"  />
 
 </div>
 
</form>


</div>



</body>
</html>