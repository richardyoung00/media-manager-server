<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:layout-definition>
    <html>
    <head>
        <title>${pageTitle}</title>

        <s:layout-component name="html_head"/>

        <s:layout-component name="pageResources">
            <jsp:include page="/WEB-INF/jsp/layout/_pageResources.jsp"/>
        </s:layout-component>

    </head>
    <body>
    <s:layout-component name="header">
        <jsp:include page="/WEB-INF/jsp/layout/_header.jsp"/>
    </s:layout-component>

    <s:layout-component name="menu">
        <jsp:include page="/WEB-INF/jsp/layout/_menu.jsp"/>
    </s:layout-component>

    <div class="pageContent">
        <s:layout-component name="contents"/>
    </div>

    <s:layout-component name="footer">
        <jsp:include page="/WEB-INF/jsp/layout/_footer.jsp"/>
    </s:layout-component>
    </body>
    </html>
</s:layout-definition>