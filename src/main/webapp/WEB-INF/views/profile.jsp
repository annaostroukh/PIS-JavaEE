<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div sec:authorize="hasRole('ROLE_ADMIN')">
	<jsp:include page="templates/admin_header.jsp"></jsp:include>
</div>

	 <div class="row">
        <div class="col-md-12">
            <h1>Profile</h1>
            <div class="row">
            	<div class="col-md-6 col-md-offset-3">
            		<div class="row">
            			<form:form class="form-add-user row" commandName="userProfile">
			                <div class="col-md-4">
			                    <form:label path="username"> Name: </form:label>
			                    <form:input path="username" type="text" id="name" name="name" class="form-control" placeholder="Name" />
			                    <form:errors path="username" />
			                    <form:hidden path="id" />
			                    <form:label path="surname"> Surname: </form:label>
			                    <form:errors path="surname" />
			                        <form:input path="surname" type="text" id="surname" name="surname" class="form-control" placeholder="Surname" />
			                    <br>
			                    <button class="btn btn-primary btn-block" type="submit">Save</button>
			                </div>
			                <div class="col-md-4">
			                    <form:label path="email"> Email: </form:label>
			                        <form:input path="email" type="email" name="email" id="Email" class="form-control" placeholder="Email" />
			                        <form:errors path="email" />
			                    <form:label path="role"> Position: </form:label>
			                        <form:select path="role" class="form-control" name="role">
			                        	<form:options items="${role}" />
			                        </form:select>
			                </div>
			                <div class="col-md-4">
			                    <form:label path="date"> Start date: </form:label>
			                        <form:input path="date" type="text" name="start-date" class="form-control" id="datepicker" />
			                    </br>
			                     <form:input path="password" type="password" id="password" class="form-control" placeholder="Password" />
			                    <form:errors path="password" />
			                    <form:input path="confirmPassword" type="password" id="confirmPassword" class="form-control" placeholder="Repeat password" />
			                    <form:errors path="confirmPassword" />
			                </div>
			                <input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" class="btn btn-theme" />
			            </form:form>
            		</div>
            	</div>
            </div>
        </div>
    </div>

<jsp:include page="templates/footer.jsp"></jsp:include>