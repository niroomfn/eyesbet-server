<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div style="margin-left:20px;margin-top:20px">

 <form action="register">
 
  <table>
  
   <tr> <td>First Name: </td> <td> <input type="text" name="firstName" /> </td>  </tr>
     <tr> <td>Last Name: </td> <td> <input type="text" name="lastName" /> </td>  </tr>
       <tr> <td>Email: </td> <td> <input type="text" name="email" /> </td>  </tr>
         <tr> <td>City: </td> <td> <input type="text" name="city" /> </td>  </tr>
         <tr> <td>Username: </td> <td> <input type="text" name="username" /> </td>  </tr>
             <tr> <td>Password: </td> <td> <input type="password" name="password" /> </td>  </tr>
             <tr> <td>Confirm Password: </td> <td> <input type="password" name="confirmPassword" /> </td>  </tr>
      
  
  </table>
 <div style="margin-top:20px">
 <input type="submit" name="submit" value="Register" />
 </div>
 </form>




</div>


</body>
</html>