<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="templates/admin_header.jsp"></jsp:include>
	 <div class="row">
        <div class="col-md-12">
            <h1>Cars</h1>
            <a href="<c:url value="/admin/cars/add/step1" />" ><button class="btn btn-info">Add car</button></a>
        	</br>
        	</br>
            <table id="table" class="table table-striped table-bordered" cellspacing="0" width="100%">
		        <thead>
		            <tr>
		                <th>Brand</th>
		                <th>Model</th>
		                <th>Year</th>
		                <th>Description</th>
		                <th>Equipment</th>
		                <th>Price, $</th>
		                <th width="75">Action</th>
		            </tr>
		        </thead>
		        <tfoot>
		            <tr>
		                <th>Brand</th>
		                <th>Model</th>
		                <th>Year</th>
		                <th>Description</th>
		                <th>Equipment</th>
		                <th>Price</th>
		                <th></th>
		            </tr>
		        </tfoot>
		        <tbody>
		        <c:if test="${empty cars}">
				      <tr>
				        <td>No cars to display</td>
				        <td></td>
				        <td></td>
				        <td></td>
				        <td></td>
				        <td></td>
				        <td></td>
				      </tr>
				    </c:if>
				    <c:if test="${not empty cars}">
		            <c:forEach var="car"  items="${cars}">
		            	<tr>
			                <td>
			                	<c:out value="${car.brand.brandName}"/>
			                </td>
			                <td>
			                	<c:out value="${car.model.modelName}"/>
			                </td>
			                <td>
			                	<c:out value="${car.year}"/>
			                </td>
			                <td>
			                	<c:out value="${car.description}"/>
			                </td>
			                <td>
			                	<c:out value="${car.equipment}"/>
			                </td>
			                <td>
			                	<c:out value="${car.price}"/>
			                </td>
			                <td>
			                	<a href="<c:url value="/admin/cars/edit/${car.id}" />" class="btn btn-sm btn-info">
			                		<span class="glyphicon glyphicon-pencil"></span>
			                	</a>
			                	<c:if test="${not empty car.id}">
			                	<a href="<c:url value="/admin/cars/${car.id}" />" onClick="return confirm('sure?')" > <!-- TODO: Implement nice dialog window -->
			                		<span class="btn btn-sm btn-info delete"><span class="glyphicon glyphicon-trash"></span></span>
			                	</a>
			                	</c:if>
			                </td>
		            	</tr>
		            </c:forEach>
		            </c:if>
		        </tbody>
		    </table>
        </div>
    </div>

<jsp:include page="templates/footer.jsp"></jsp:include>