<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="templates/admin_header.jsp"></jsp:include>
<div class="row">
        <div class="col-md-12">
            <h1>Edit a car</h1>
            <form:form class="form-edit-car" commandName="car">
                <div class="row">
                    <div class="col-md-4">
                        <label> Select brand </label>
                            <form:select path="brand" class="form-control">
                                <form:options items="${brands}" />
                            </form:select>
                        <label> Or </label><br>
                        <a class="btn btn-info" data-toggle="modal" data-target="#brand">Add new brand</a>
                    </div>
                    <div class="col-md-4">
                        <label> Select model </label>
                            <form:select path="model" class="form-control">
                                <form:options items="${models}" />
                            </form:select>
                        <label> Or </label><br>
                        <a class="btn btn-info" data-toggle="modal" data-target="#model">Add new model</a>
                    </div>
                    <div class="col-md-4">
                        <label> Year </label>
                            <form:input path="year" type="text" name="year" id="Year" class="form-control" />
                        <label>Price</label>
                        <form:input path="price" class="form-control" type="number" />
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

    <div class="modal fade" id="model" tabindex="-1" role="dialog" aria-labelledby="model">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form>
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Add new model</h4>
                    </div>
                    <div class="modal-body">
                        <input type="text" id="new-model" class="form-control" placeholder="Model name">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" type="submit" class="btn btn-primary">Save</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="modal fade" id="brand" tabindex="-1" role="dialog" aria-labelledby="brand">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form>
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Add new brand</h4>
                    </div>
                    <div class="modal-body">
                        <input type="text" id="new-brand" class="form-control" placeholder="Brand name">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" type="submit" class="btn btn-primary">Save</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

<jsp:include page="templates/footer.jsp"></jsp:include>