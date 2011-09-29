<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<ul id="nav">
    <li class="current"><a href="${contextPath}/home">Home</a></li>
    <%--<li><a href="http://www.ndesign-studio.com">My Projects</a>
        <ul>
            <li><a href="http://www.ndesign-studio.com">N.Design Studio</a>
                <ul>
                    <li><a href="http://www.ndesign-studio.com/portfolio">Portfolio</a></li>
                    <li><a href="http://www.ndesign-studio.com/wp-themes">WordPress Themes</a></li>
                    <li><a href="http://www.ndesign-studio.com/wallpapers">Wallpapers</a></li>
                    <li><a href="http://www.ndesign-studio.com/tutorials">Illustrator Tutorials</a></li>
                </ul>
            </li>
            <li><a href="http://www.webdesignerwall.com">Web Designer Wall</a>
                <ul>
                    <li><a href="http://jobs.webdesignerwall.com">Design Job Wall</a></li>
                </ul>
            </li>
            <li><a href="http://icondock.com">IconDock</a></li>
            <li><a href="http://bestwebgallery.com">Best Web Gallery</a></li>
        </ul>
    </li>--%>
    <li><a href="#">Admin</a>
        <ul>
            <li><a href="${contextPath}/users">Users</a></li>

            <li><a href="${contextPath}/server/movies">Settings</a></li>
        </ul>
    </li>
    <li><a href="#">Server Browser</a>
        <ul>
            <li><a href="${contextPath}/server/series">Tv Series</a></li>
            <li><a href="${contextPath}/server/movies">Movies</a></li>
            <li><a href="${contextPath}/server/music">Music</a></li>
            <li><a href="${contextPath}/server/incoming">Incoming</a></li>
        </ul>
    </li>
    <li><a href="#">About</a></li>
    <li><a href="#">Contact Us</a></li>
</ul>