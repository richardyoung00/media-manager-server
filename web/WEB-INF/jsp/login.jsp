<%@ page import="MediaManager.model.User" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<html>
<head><title>Login page</title></head>

<body>

<s:errors/>
<s:form beanclass="MediaManager.action.LoginActionBean" focus="">

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
            <td><s:text name="username" value="${user.username}"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><s:password name="password"/></td>
        </tr>
        <tr>
            <td>
                <s:submit name="login" value="login"/>

            </td>


        </tr>

    </table>


</s:form>
</body>
</html>