<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="templates/admin_header.jsp"></jsp:include>


<div class="row">
		<c:choose>
			<c:when
				test="${not empty users}">
			</c:when>
		</c:choose>
        <div class="col-md-12">
            <h1>Users</h1>
            <a href="<c:url value = "/admin/users/new" />" /><button class="btn btn-info">Add user</button></a>
        	</br>
        	</br>
            <table id="table" class="table table-striped table-bordered" cellspacing="0" width="100%">
		        <thead>
		            <tr>
		                <th>Name</th>
		                <th>Surname</th>
		                <th>Position</th>
		                <th>Email</th>
		                <th>Start date</th>
		                <th width="75">Action</th>
		            </tr>
		        </thead>
		        <tfoot>
		            <tr>
		                <th>Name</th>
		                <th>Surname</th>
		                <th>Position</th>
		                <th>Email</th>
		                <th>Start date</th>
		                <th></th>
		            </tr>
		        </tfoot>
		        <tbody>
		        	<c:if test="${empty users}">
				      <tr>
				        <td>No users to display</td>
				        <td></td>
				        <td></td>
				        <td></td>
				        <td></td>
				        <td></td>
				      </tr>
				    </c:if>
		            <c:if test="${not empty users}">
		            <tr>
		            	<c:forEach var="user"  items="${users}">
			                <td>
			                	<c:out value="${user.username}"/>
			                </td>
			                <td>
			                	<c:out value="${user.surname}"/>
			                </td>
			                <td>
			                	<c:out value="${user.role}"/>
							</td>
			                <td>
			                	<c:out value="${user.email}"/>
			                </td>
			                <td>
			                	<c:out value="${user.date}"/>
							</td>
			                <td>
			                	<a href="<c:url value="/admin/users/edit" />" class="btn btn-sm btn-info"><span class="glyphicon glyphicon-pencil"></span></a>
			                	<c:if test="${not empty user.id}">
			                		<a href="<c:url value="/admin/delete/${user.id}" />" >
			                			<span class="btn btn-sm btn-info delete"><span class="glyphicon glyphicon-trash"></span></span>
			                		</a>
			                	</c:if>
			                </td>
			           </c:forEach>
		            	</tr>
		            </c:if>
		        </tbody>
		    </table>
        </div>
    </div>
    <jsp:include page="templates/footer.jsp"></jsp:include>