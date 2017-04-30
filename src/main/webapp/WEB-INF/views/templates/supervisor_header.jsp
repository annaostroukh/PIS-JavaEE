<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="head.jsp"></jsp:include>

		<li><a href="<c:url value='/supervisor/dashboard' />"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span> Dashboard</a></li>
		<li><a href="<c:url value='/supervisor/clients' />"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> Clients</a></li>
		<li><a href="<c:url value='/supervisor/profile' />"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Profile</a></li>
		<li><a href="<c:url value="/logout" />"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> Log out</a></li>
          </ul>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>
    <!--Main container -->
    <div class="main-container container">