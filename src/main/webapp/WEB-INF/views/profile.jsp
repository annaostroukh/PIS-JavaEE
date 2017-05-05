<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<sec:authorize access="hasRole('ROLE_ADMIN')">
	<jsp:include page="templates/admin_header.jsp"></jsp:include> 
</sec:authorize>

<sec:authorize access="hasRole('ROLE_MANAGER')">
	<jsp:include page="templates/manager_header.jsp"></jsp:include>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_SUPERVISOR')">
	<jsp:include page="templates/supervisor_header.jsp"></jsp:include>
</sec:authorize>

	 <div class="row">
        <div class="col-md-12">
            <h1>Profile</h1>
            <div class="row">
            	<div class="col-md-6 col-md-offset-3">
            		
            			<form:form class="form-add-user row" commandName="userProfile">
            				<c:if test="${not empty error}">
				             	<div class="error-title">Following errors occurred:</div>
								<div class="error">${error}</div>
							</c:if>
							<c:if test="${not empty message}">
								<div class="error">${message}</div>
							</c:if>
							<div class="row">
				                <div class="col-md-4">
				                    <form:label path="username"> Name*: </form:label>
				                    <form:input path="username" type="text" id="name" name="name" class="form-control" placeholder="Name" />
				                    <form:errors class="error" path="username" />
				                    <form:hidden path="id" />
				                    <form:label path="surname"> Surname*: </form:label>
				                        <form:input path="surname" type="text" id="surname" name="surname" class="form-control" placeholder="Surname" />
				                    	<form:errors class="error" path="surname" />
				                    <br>
				                    <button class="btn btn-primary btn-block" type="submit">Save</button>
				                </div>
				                <div class="col-md-4">
				                    <form:label path="email"> Email*: </form:label>
				                        <form:input path="email" type="email" name="email" id="Email" class="form-control" placeholder="Email" />
				                        <form:errors class="error" path="email" />
				                    <form:label path="role"> Position*: </form:label>
				                        <sec:authorize access="hasRole('ROLE_MANAGER')">
					                        <form:select disabled="${true}" path="role" class="form-control" name="role">
					                        	<form:option value="ROLE_MANAGER">Manager</form:option>
					                        </form:select>
					                        <form:hidden path="role"/>
				                        </sec:authorize>
				                         <sec:authorize access="hasRole('ROLE_ADMIN')">
					                        <form:select path="role" class="form-control" name="role">
					                        	<form:options items="${role}" />
					                        </form:select>
				                        </sec:authorize>
				                         <sec:authorize access="hasRole('ROLE_SUPERVISOR')">
					                        <form:select disabled="${true}" path="role" class="form-control" name="role">
					                        	<form:option value="ROLE_SUPERVISOR">Supervisor</form:option>
					                        </form:select>
					                        <form:hidden path="role"/>
				                        </sec:authorize>
				                </div>
				                <div class="col-md-4">
				                    <form:label path="date"> Start date*: </form:label>
				                        <form:input path="date" type="text" name="start-date" class="form-control" id="datepicker" />
				                        <form:errors class="error" path="date" />
				                    </br>
				                     <form:input path="password" type="password" id="password" class="form-control" placeholder="Password" />
				                    <form:errors class="error" path="password" />
				                    <form:input path="confirmPassword" type="password" id="confirmPassword" class="form-control" placeholder="Repeat password" />
				                    <form:errors class="error" path="confirmPassword" />
				                </div>
				            </div>
			                <form:hidden path="phoneNumber"/>
			               
			                <input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" class="btn btn-theme" />
			            </form:form>
            		</div>
            	</div>
            </div>
        </div>
    </div>

<jsp:include page="templates/footer.jsp"></jsp:include>