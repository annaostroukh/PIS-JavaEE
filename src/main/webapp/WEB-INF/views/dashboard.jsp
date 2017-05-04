<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fc" uri="http://custom.function/EL" %> 

<jsp:include page="templates/supervisor_header.jsp"></jsp:include>
 <div class="row">
        <div class="col-md-12">
            <h1>Dashboard</h1>
        	</br>
        	<div class="row">
        		<div class="col-md-3">
        			<h4> Clients waiting for a manager: </h4>
        			<div class="jumbotron text-center">
        				<h1>${lc}</h1><br>
        				lonely clients
        			</div>
        		</div>
        		<div class="col-md-3">
        			<h4> Most busy manager: </h4>
        			<div class="jumbotron text-center">
        				
        				meetings
        				<b>${maxManager}</b>
        			</div>
        		</div>
        		<div class="col-md-3">
        			<h4> Less busy manager: </h4>
        			<div class="jumbotron text-center">
        				<h1>2</h1>
        				meetings this week
        				<b>Name Surname</b>
        			</div>
        		</div>
        		<div class="col-md-3">
        			<h4> Today: </h4>
        			<div class="jumbotron text-center">
        				<!-- <h1>${ms}</h1>  -->
        				meetings<br>
        			</div>
        		</div>
        	</div>
        	<div class="row">
        		<div class="col-sm-12">
		        	<h4> Clients waiting for a manager: </h4>
		            <table class="table table-striped table-bordered" cellspacing="0" width="100%">
				        <thead>
				            <tr>
				                <th>Client</th>
                                <th>Status</th>
                                <th>Phone</th>
                                <th>Email</th>
                                <th>Edit</th>
				            </tr>
				        </thead>
				        <tbody>
				        	<c:if test="${empty clients}">
				        		<td>Congratulations! All your clients have a manager!</td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </c:if>
				            <c:if test="${not empty clients}">
				            <c:forEach var="client"  items="${clients}">
				            <tr>
				                <td>${client.name} ${client.surname}</td>
				                <td>${client.status}</td>
				                <td>${client.phoneNumber}</td>
				                <td>${client.email}</td>
				                <td>
				                	<a href="<c:url value="/supervisor/clients/edit/${client.id}" />" class="btn btn-sm btn-info"><span class="glyphicon glyphicon-pencil"></span></a>
				                </td>
				            </tr>
				            </c:forEach>
				            </c:if>
				        </tbody>
				    </table>
				</div>
			</div>				
        </div>
    </div>

 <jsp:include page="templates/footer.jsp"></jsp:include>