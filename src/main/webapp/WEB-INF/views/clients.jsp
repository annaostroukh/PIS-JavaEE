<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fc" uri="http://custom.function/EL" %> 

<jsp:include page="templates/supervisor_header.jsp"></jsp:include>

 <div class="row">
        <div class="col-md-12">
            <h1>Clients</h1>
            <a href="<c:url value = "/supervisor/clients/new" />" /><button class="btn btn-info">New client</button></a>
        	</br>
        	</br>
            <table id="table" class="table table-striped table-bordered" cellspacing="0" width="100%">
		        <thead>
		            <tr>
		                <th>Client</th>
		                <th>Email</th>
		                <th>Phone</th>
		                <th>Birthday</th>
		                <th>Status</th>
		                <th>Car</th>
		                <th>Manager</th>
		                <th width="75">Action</th>
		            </tr>
		        </thead>
		        <tfoot>
		            <tr>
		                <th>Client</th>
		                <th>Email</th>
		                <th>Phone</th>
		                <th>Birthday</th>
		                <th>Status</th>
		                <th>Car</th>
		                <th>Manager</th>
		                <th></th>
		            </tr>
		        </tfoot>
		        <tbody>
		        	<c:if test="${empty clients}">
				      <tr>
				        <td>No clients to display</td>
				        <td></td>
				        <td></td>
				        <td></td>
				        <td></td>
				        <td></td>
				        <td></td>
				        <td></td>
				      </tr>
				    </c:if>
				    <c:if test="${not empty clients}">
		            <c:forEach var="client"  items="${clients}">
		            <tr>
		                <td>
		                	<c:out value="${client.name}"/>
		                </td>
		                <td>
		                	<c:out value="${client.email}"/>
		                </td>
		                <td>
		                	<c:out value="${client.phoneNumber}"/>
		                </td>
		                <c:if test="${empty client.birthday}">
		                	<td>-</td>
		                </c:if>
		                <c:if test="${not empty client.birthday}">
		                	<td>
		                		<c:out value="${fc:formatDate(client.birthday,'dd/MM/yyyy')}" />
		                	</td>
		                </c:if>
		                <td>
		                	<c:out value="${client.status}"/>
		                </td>
		                <c:if test="${empty client.cars}">
		                	<td>-</td>
		                </c:if>
		                <c:if test="${not empty client.cars}">
				            <td>
				            	<c:forEach var="car"  items="${client.cars}" varStatus="status">
				                	<c:out value="${car.brand.brandName} ${car.model.modelName} ${car.year}"/>
				            	</c:forEach>
				             </td>
		                </c:if>
		                 <c:if test="${empty client.managers}">
		                	<td>-</td>
		                </c:if>
		                 <c:if test="${not empty client.managers}">
				             <td>
				             	<c:forEach var="manager"  items="${client.managers}">
				                	<c:out value="${manager.username} "/>
				                </c:forEach>
				             </td>
		                </c:if>
		                <td>
		                	<a href="<c:url value="/supervisor/clients/edit/${client.id}" />" class="btn btn-sm btn-info"><span class="glyphicon glyphicon-pencil"></span></a>
		                	<a href="<c:url value="/supervisor/clients/${client.id}" />" onClick="return confirm('sure?')" >
		                		<span class="btn btn-sm btn-info delete"><span class="glyphicon glyphicon-trash"></span></span>
		                	</a>
		                </td>
		            </tr>
		           </c:forEach>
		          </c:if>
		        </tbody>
		    </table>
        </div>
    </div>
  <jsp:include page="templates/footer.jsp"></jsp:include>