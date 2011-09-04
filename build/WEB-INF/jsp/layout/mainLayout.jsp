<<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>

<stripes:layout-definition>
    <html>
        <head>
            <title>${pageTitle}</title>

            <stripes:layout-component name="html_head"/>

            <stripes:layout-component name="pageResources">
                <jsp:include page="/WEB-INF/jsp/layout/_pageResources.jsp"/>
            </stripes:layout-component>

        </head>
        <body>
            <stripes:layout-component name="header">
                <jsp:include page="/WEB-INF/jsp/layout/_header.jsp"/>
            </stripes:layout-component>

            <stripes:layout-component name="menu">
                <jsp:include page="/WEB-INF/jsp/layout/_menu.jsp"/>
            </stripes:layout-component>

            <div class="pageContent">
                <stripes:layout-component name="contents"/>
            </div>

            <stripes:layout-component name="footer">
                <jsp:include page="/WEB-INF/jsp/layout/_footer.jsp"/>
            </stripes:layout-component>
        </body>
    </html>
</stripes:layout-definition>