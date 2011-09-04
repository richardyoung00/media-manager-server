<%@ page import="MediaManager.action.LoginActionBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="security" uri="http://www.stripes-stuff.org/security.tld" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>



  </head>
  <body>

  <%
      String redirectURL = "home.action";
      response.sendRedirect(redirectURL);
  %>



  </body>
</html>