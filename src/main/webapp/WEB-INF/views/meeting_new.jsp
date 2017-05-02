<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fc" uri="http://custom.function/EL" %>

<jsp:include page="templates/manager_header.jsp"></jsp:include>
 <div class="row">
        <div class="col-md-12">
            <h1>Edit meeting</h1>
            <div class="row">
                <div class=" col-md-8 col-md-offset-2">
                    <form:form class="form-add-user row" commandName="meeting" name="addMeetingForm" id="meeting_add" method="POST">
                    	<c:if test="${not empty error}">
             		<div class="error-title">Following errors occurred:</div>
					<div class="error">${error}</div>
					</c:if>
					<c:if test="${not empty message}">
						<div class="error">${message}</div>
					</c:if>
		                <div class="col-md-6">
		                	<label> Title*: </label>
		                   		<form:input path="title" type="text" id="title" name="title" class="form-control" placeholder="Title" />
		                   		<form:errors class="error" path="title" />
		                   	<label> Date*: </label>
		                        <form:input path="date" type="text" name="start-date" class="form-control" id="datepickerT" />
		                        <form:errors class="error" path="date" />
		                	<label> Time*: </label>
		                   		<form:input path="time" type="text" id="time" name="time" class="form-control" placeholder="10:00" />
		                   		<form:errors class="error" path="time" />
		                   	<label> Place: </label>
		                   		<form:input path="place" type="text" id="place" name="place" class="form-control" placeholder="Place" />
		                   	<button class="btn btn-primary btn-block" type="submit">Save</button>
		                </div>
		                <div class="col-md-6">
		                	<label> Client: </label>
		                        <form:select path="client.id" class="form-control" name="clients">
		           					<form:options items="${clients}" />
		                        </form:select>
		                    <label> Description: </label>
		                   		<form:input path="description" type="text" id="description" name="description" class="form-control" placeholder="Description" />

		                	<label> Result: </label>
		                        <form:input path="results" type="text" id="results" name="results" class="form-control" placeholder="Results" />
		                </div>
		                <form:hidden path="id"/>
		                <form:hidden path="client.phoneNumber"/>
		                <form:hidden path="client.name"/>
		                <form:hidden path="client.surname"/>
		                <form:hidden path="client.email"/>
		                <form:hidden path="manager"/>

		            </form:form><!-- /form -->
                </div>
            </div>
        </div>
    </div>

<jsp:include page="templates/footer.jsp"></jsp:include>