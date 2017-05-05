<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="templates/admin_header.jsp"></jsp:include>
<div class="row">
        <div class="col-md-12">
            <h1>Edit a car</h1>
            <form:form class="form-edit-car" commandName="car">
            	<c:if test="${not empty error}">
             		<div class="error-title">Following errors occurred:</div>
					<div class="error">${error}</div>
				</c:if>
				<c:if test="${not empty message}">
					<div class="error">${message}</div>
				</c:if>
				
                <div class="row">
                    <div class="col-md-4">
                    <form:hidden path="id"/>
                    <form:hidden path="brand.brandName"/>
                        <label> Select brand* </label>
                            <form:select path="brand.id" class="form-control" id="brand" name="brand" onchange="brandChange(this)">
                                <form:options items="${brands}" />
                            </form:select>
                            <form:errors class="error" path="brand.id" />
                        <label> Or </label><br>
                       	<a class="btn btn-info" id="brand-new" data-toggle="modal" data-target="#brand-modal">Add new brand</a>
                    </div>
                    <div class="col-md-4">
                        <label> Select model* </label>
                            <form:select path="model.id" class="form-control" id="model" name="model" onchange="modelChange(this)">
                                <form:options items="${models}" />
                            </form:select>
                            <form:errors class="error" path="model.id" />
                        <label> Or </label><br>
                        <a class="btn btn-info" data-toggle="modal" data-target="#model-modal">Add new model</a>
                    </div>
                    <div class="col-md-4">
                        <label> Year* </label>
                            <form:input path="year" type="text" name="year" id="year" class="form-control" />
                            <form:errors class="error" path="year" />
                        <label>Price*</label>
                        <form:input path="price" class="form-control" type="number" id="price" />
                        <form:errors class="error" path="price" />
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <label>Description</label>
                        <form:textarea path="description" rows="10" name="description" class="form-control"></form:textarea>
                    </div>
                    <div class="col-md-6">
                        <label> Equipment </label><br>
                        <form:textarea path="equipment" rows="10" name="equipment" class="form-control"></form:textarea>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                         <button class="btn btn-primary pull-right" type="submit">Save</button>
                    </div>
                </div>
            </form:form><!-- /form -->
        </div>
    </div>
				
     <div class="modal fade" id="brand-modal" tabindex="-1" role="dialog" aria-labelledby="brand">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form action="?form_brand" id="form-brand">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Add new brand</h4>
                    </div>
                    <div class="modal-body">
                     <form:hidden path="newBrand.id"/>
                        <form:input type="text" path="newBrand.brandName" id="brand" class="form-control" placeholder="Brand name" />
                        <form:errors class="error" path="newBrand.brandName" />
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <div class="modal fade" id="model-modal" tabindex="-1" role="dialog" aria-labelledby="model">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form action="?form_model" id="form-model">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Add new model</h4>
                    </div>
                    <div class="modal-body">
                    	<form:hidden path="newModel.id"/>
                    	<form:select path="newModel.brand.id" class="form-control" id="brand">
                                <form:options items="${brands}" />
                            </form:select>
                        <form:input path="newModel.modelName" type="text" id="new-model" class="form-control" placeholder="Model name" />
                    	<form:errors class="error" path="newModel.modelName" />
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

<jsp:include page="templates/footer.jsp"></jsp:include>