
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="security" uri="http://www.stripes-stuff.org/security.tld" %>

<stripes:layout-render name="/WEB-INF/jsp/layout/mainLayout.jsp" pageTitle="Home">
    <stripes:layout-component name="contents">

    Hi, I'm the Stripes Calculator. I can only do addition. Maybe, some day, a nice programmer
    will come along and teach me how to do other things?



    <stripes:form beanclass="MediaManager.action.HomePageActionBean" focus="">



      This is the home page.

    </stripes:form>

        </stripes:layout-component>
</stripes:layout-render>
