<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

			<jsp:include page="head.jsp"></jsp:include>

			<li><a href="<c:url value='/admin-users' />"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> Users</a></li>
            <li><a href="<c:url value='/admin-cars' />"><span class="glyphicon glyphicon-road" aria-hidden="true"></span> Cars</a></li>
            <li><a href="<c:url value='/admin-profile' />"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Profile</a></li>
          	<li><a href="<c:url value="/logout" />"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> Log out</a></li>
          </ul>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>
    <!--Main container -->
    <div class="main-container container">