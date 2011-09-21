<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="security" uri="http://www.stripes-stuff.org/security.tld" %>

<stripes:layout-render name="/WEB-INF/jsp/layout/mainLayout.jsp" pageTitle="Server Browser">
    <stripes:layout-component name="contents">
        <%--Imports--%>
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/resources/style/jqueryFileTree.css"/>
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/resources/script/contextMenu/jquery.contextMenu.css"/>

        <script type='text/javascript'
                src="${pageContext.request.contextPath}/resources/script/jqueryFileTree/jqueryFileTree.js"></script>
        <script type='text/javascript'
                src="${pageContext.request.contextPath}/resources/script/jquery.jeditable.mini.js"></script>
        <script type='text/javascript'
                src="${pageContext.request.contextPath}/resources/script/contextMenu/jquery.contextMenu.js"></script>
        <%
            String browsePath = session.getAttribute("browsePath").toString();
        %>
        <script type='text/javascript'
                >$(document).ready(function() {
            $('#browser_container').fileTree({
                root: '<%=browsePath%>',
                script: '${pageContext.request.contextPath}/resources/script/jqueryFileTree/jqueryFileTree.jsp',
                expandSpeed: 1000,
                collapseSpeed: 1000,
                folderEvent: 'click',
                multiFolder: false
            }, function(file) {
                alert(file);
            });


        });

        $(document).ajaxComplete(function() {

            $('.edit').each(function() {
                $(this).editable('resources/script/jqueryFileTree/renameFile.jsp', {
                    indicator : 'Saving...',
                    event     : "rename",
                    name : 'newValue',
                    submitdata : {
                        path: $(this).attr('rel'),
                        oldValue: $(this).text()
                    }
                });
            });


            $(".edit").contextMenu({
                        menu: 'myMenu'
                    },
                    function(action, el, pos) {
                        /*alert(
                         'Action: ' + action + '\n\n' +
                         'Element path: ' + $(el).attr('rel') + '\n\n'

                         );*/
                        if (action == "rename") {
                            el.trigger("rename");
                        }
                    });
        });</script>

        Browsing  ${browsePath} on the server

        <stripes:form beanclass="MediaManager.action.ServerBrowserActionBean" focus="">





            <div id="browser_container">

            </div>

            <ul id="myMenu" class="contextMenu">
                <li class="rename"><a href="#rename">Rename</a></li>
                <li class="cut separator"><a href="#cut">Cut</a></li>
                <li class="copy"><a href="#copy">Copy</a></li>
                <li class="paste"><a href="#paste">Paste</a></li>
                <li class="delete"><a href="#delete">Delete</a></li>
                <li class="quit separator"><a href="#quit">Quit</a></li>
            </ul>

        </stripes:form>

    </stripes:layout-component>
</stripes:layout-render>