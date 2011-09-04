<%@ page import="MediaManager.model.User" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Login page</title></head>

<body>

<stripes:errors/>
<stripes:form beanclass="MediaManager.action.LoginActionBean" focus="">

    <table>
        <tr>
            <td>
                <%

                    User u = (User) request.getSession().getAttribute("user");
                    if (u != null) {

                %>
                Welcome <%=u.getUsername()%>
                <%
                } else {
                %>
                No user logged in <%=request.getSession().getAttribute("test")%>
                <%
                    }
                %>
            </td>
        </tr>
        <tr>
            <td>Username:</td>
            <td><stripes:text name="username" value="${user.username}"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><stripes:password name="password"/></td>
        </tr>
        <tr>
            <td>
                <stripes:submit name="login" value="login"/>

            </td>


        </tr>

    </table>


</stripes:form>
</body>
</html>