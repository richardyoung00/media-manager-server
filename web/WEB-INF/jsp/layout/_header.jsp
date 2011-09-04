<%@ page import="MediaManager.model.User" %>
<div class="header">
    <%
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
    %>
    Logged in as: <%=user.getUsername()%> Logout
    <%
        }
    %>


</div>

