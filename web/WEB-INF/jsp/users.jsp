<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>


<s:layout-render name="/WEB-INF/jsp/layout/mainLayout.jsp" pageTitle="Users">
    <s:layout-component name="contents">

        <s:form beanclass="MediaManager.action.UserActionBean" focus="">

            <table>
                <tr>
                    <td>ID</td>
                    <td>Username</td>
                    <td>Password</td>
                    <td>Roles</td>
                </tr>
                <c:forEach var="user" items="${actionBean.users}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.password}</td>
                        <c:forEach var="role" items="${user.roles}">
                            <td>${role.name}</td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>




        </s:form>

    </s:layout-component>
</s:layout-render>