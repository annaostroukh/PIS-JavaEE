<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="templates/admin_header.jsp"></jsp:include>
 <div class="row">
        <div class="col-md-12">
            <h1>Add a car</h1>
            <div class="row">
                <div class=" col-md-8 col-md-offset-2">
                    <form:form class="form-add-car add-card" commandName="brand">
                        <div class="row">
                           <!--   <div class="col-md-4">
                                <form:label path="brand"> Select brand </form:label>
                                    <form:select path="brand" class="form-control" name="brand">
                                    	<form:option value="" label="- Select -"/>
                                        <form:options items="${brands}" />
                                    </form:select>
                                <br>
                            </div> -->
                            <div class="col-md-4">
                                <label> Or </label>
                                <br>
                                <!-- <a class="btn btn-info" data-toggle="modal" data-target="#brand">Add new brand</a>  -->
                                 <form:input path="name" type="text" id="new-brand" class="form-control" placeholder="Brand name" />
                        		<form:hidden path="id" />
                        		<button class="btn btn-primary btn-block" type="submit">Add new brand</button>
                            </div>
                        </div>
                        <div class="row form-nav">
                            <div class="col-md-3">
                                <button class="btn btn-defaultt" disabled>Previous</button>
                            </div>
                            <div class="col-md-6">
                                <div class="progress">
                                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="30"
                                        aria-valuemin="0" aria-valuemax="100" style="width:30%">
                                        Step 1
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <a href="<c:url value="/admin/cars/add/step2" />" class="btn btn-primary pull-right">Next</a>
                            </div>
                        </div> <!-- /form-nav-->
                    </form:form><!-- /form -->
                </div>
            </div>
        </div>
    </div>
   <!--  <div class="modal fade" id="brand" tabindex="-1" role="dialog" aria-labelledby="brand">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form:form commandName="brand">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Add new brand</h4>
                    </div>
                    <div class="modal-body">
                        <form:input path="name" type="text" id="new-brand" class="form-control" placeholder="Brand name" />
                        <form:hidden path="id" />
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" type="submit" class="btn btn-primary">Save</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>  -->

<jsp:include page="templates/footer.jsp"></jsp:include>