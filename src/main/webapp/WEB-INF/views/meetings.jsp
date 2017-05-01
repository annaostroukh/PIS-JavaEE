<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fc" uri="http://custom.function/EL" %> 

<jsp:include page="templates/manager_header.jsp"></jsp:include>

	<div class="row">
        <div class="col-md-12">
            <h1>Schedule</h1>
            <a href="meetings/new"><button class="btn btn-info">Plan a meeting</button></a>
            </br>
            </br>
            <div class="row">
                <div class="col-md-8">
                    <table id="table" class="table table-striped table-bordered" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th>Date</th>
                                <th>Client</th>
                                <th>Status</th>
                                <th>Phone</th>
                                <th>Email</th>
                                <th>Place</th>
                                <th width="75">Action</th>
                            </tr>
                        </thead>
                        <tbody>
	                        <c:if test="${empty meetings}">
					     		 <tr>
							        <td>No meetings to display</td>
							        <td></td>
							        <td></td>
							        <td></td>
							        <td></td>
							        <td></td>
							        <td></td>
					      		</tr>
					    	</c:if>
					    	<c:if test="${not empty meetings}">
		            		<c:forEach var="meeting"  items="${meetings}">
                            <tr>
                                <td>
                                	<c:out value="${fc:formatDate(meeting.date,'dd/MM/yyyy')}" />
									<p>at</p>
                                	<c:out value="${meeting.time}"/>
                                </td>
                                <td>
                                	<c:out value="${meeting.client.name}"/>
                                </td>
                                <td>
                                	<c:out value="${meeting.client.status}"/>
                                </td>
                                <td>
                                	<c:out value="${meeting.client.phoneNumber}"/>
                                </td>
                                <td>
                                	<c:out value="${meeting.client.email}"/>
                                </td>
                                <td>
                                	<c:out value="${meeting.place}"/>
                                </td>
                                <td>
                                    <a href="<c:url value="/manager/meetings/edit/${meeting.id}" />" class="btn btn-sm btn-info"><span class="glyphicon glyphicon-pencil"></span></a>
                                    <a href="<c:url value="/manager/meetings/${meeting.id}" />" onClick="return confirm('sure?')" > <!-- TODO: Implement nice dialog window -->
			                			<span class="btn btn-sm btn-info delete"><span class="glyphicon glyphicon-trash"></span></span>
			                		</a>
                                </td>
                            </tr>
                            </c:forEach>
		            	</c:if>
                        </tbody>
                    </table>
                </div>
                <div id="calendar" class="col-md-4">   
    			</div>
            </div>
        </div>
    </div>

<jsp:include page="templates/footer.jsp"></jsp:include>