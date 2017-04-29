<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="head.jsp"></jsp:include>

            <li><a href="<c:url value='/manager/meetings' />"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span> Meetings</a></li>
            <li><a href="<c:url value='/manager/profile' />"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Profile</a></li>
            <li><a href="<c:url value="/logout" />"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> Log out</a></li>
          </ul>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>
    <!--Main container -->
    <div class="main-container container">