<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fc" uri="http://custom.function/EL" %>

<jsp:include page="templates/manager_header.jsp"></jsp:include>
 <div class="row">
        <div class="col-md-12">
            <h1>Add a meeting</h1>
            <div class="row">
                <div class=" col-md-8 col-md-offset-2">
                    <form:form class="form-add-user row" commandName="meeting" name="addMeetingForm" id="meeting_add" method="POST">
                    	<c:if test="${not empty error}">
							<div>${error}</div>
						</c:if>
						<c:if test="${not empty message}">
							<div>${message}</div>
						</c:if>
                    	<form:hidden path="id"/>
		                <div class="col-md-6">
		                	<label> Title: </label>
		                   		<form:input path="title" type="text" id="title" name="title" class="form-control" placeholder="Title" />
		                   		<form:errors path="title" />
		                   	<label> Date: </label>
		                        <form:input path="date" type="text" name="start-date" class="form-control" id="datepicker" />
		                        <form:errors path="date" />
		                	<label> Time: </label>
		                   		<form:input path="time" type="text" id="time" name="time" class="form-control" value="10:00" placeholder="Time" />
		                   		<form:errors path="time" />
		                   	<label> Place: </label>
		                   		<form:input path="place" type="text" id="place" name="place" class="form-control" placeholder="Place" />
		                   		<form:errors path="place" />
		                   	<button class="btn btn-primary btn-block" type="submit">Save</button>
		                </div>
		                <div class="col-md-6">
		                	<label> Client: </label>
		                        <form:select path="client.id" class="form-control" name="clients">
		                        	<form:option value="" label="- Select -"/>
		           					<form:options items="${clients}" />
		                        </form:select>
		                    <label> Description: </label>
		                   		<form:input path="description" type="text" id="description" name="description" class="form-control" placeholder="Description" />

		                	<label> Result: </label>
		                        <form:input path="results" type="text" id="results" name="results" class="form-control" placeholder="Results" />
		                </div>

		            </form:form><!-- /form -->
                </div>
            </div>
        </div>
    </div>

<jsp:include page="templates/footer.jsp"></jsp:include>