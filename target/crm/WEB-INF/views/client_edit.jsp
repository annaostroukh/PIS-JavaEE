<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fc" uri="http://custom.function/EL" %> 

<jsp:include page="templates/supervisor_header.jsp"></jsp:include>
 <div class="row">
        <div class="col-md-12">
            <h1>Edit client</h1>
            <!-- when 'edit' mode : -->
            <!--<h2>Edit client</h2> -->
            <form:form class="form-add-client row" commandName="client" id="client_add" method="POST">
                <div class="col-md-4">
                    <label> Name: </label>
                    <form:input path="name" type="text" id="name" name="name" class="form-control" placeholder="Name" />
                    <label> Surname: </label>
                        <form:input path="surname" type="text" id="surname" name="surname" class="form-control" placeholder="Surname" />
                    <label> Birthday: </label>
                        <form:input path="birthday" type="text" name="birthdate" class="form-control" id="datepicker" />
                    <label> Manager: </label>
                        <form:select path="managers" class="form-control" multiple="false">
                            <form:option value="" label="- Select -"/>
                            <form:options selected="true" items="${managers}"/>
                        </form:select>
                    <br>
                    <form:hidden path="id"/>
                    <button class="btn btn-primary btn-block" type="submit">Save</button>
                </div>
                <div class="col-md-4">
                    <label> Email: </label>
                        <form:input path="email" type="email" name="email" id="Email" class="form-control" placeholder="Email" />
                    <label> Phone: </label>
                    <form:input path="phoneNumber" type="text" name="phone" class="form-control" />
                    <label> Status: </label>
                        <form:select path="status" class="form-control">
                            <form:option value="" label="- Select -"/>
                            <form:options selected="true" items="${status}" />
                        </form:select>
                </div>
                <div class="col-md-4">
                    <!-- if has a car selected -->
	                 <label> Select brand </label>
                        <form:select path="cars" class="form-control" multiple="false">
                            <form:option value="" label="- Select -"/>
                            <form:options selected="true" items="${cars}" itemValue="brand.id" itemLabel="brand.brandName" />
                        </form:select>
                    <label> Select model </label>
                        <form:select path="cars" class="form-control" multiple="false">
                            <form:option value="" label="- Select -"/>
                            <form:options selected="true" items="${cars}" itemValue="model.id" itemLabel="model.modelName" />
                        </form:select>
                    <label> Select year </label>
                        <form:select path="cars" class="form-control" multiple="false">
                            <form:option value="" label="- Select -"/>
                            <form:options selected="true" items="${cars}" itemValue="id" itemLabel="year" />
                        </form:select>    
                </div>
            </form:form><!-- /form -->
        </div>
    </div>

<jsp:include page="templates/footer.jsp"></jsp:include>