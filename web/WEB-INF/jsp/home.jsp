<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout/mainLayout.jsp" pageTitle="Home">
    <s:layout-component name="contents">

        Hi, I'm the Stripes Calculator. I can only do addition. Maybe, some day, a nice programmer
        will come along and teach me how to do other things?


        <s:form beanclass="MediaManager.action.HomePageActionBean" focus="">


            This is the home page.

        </s:form>

    </s:layout-component>
</s:layout-render>
