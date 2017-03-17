<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<jsp:include page="templates/admin_header.jsp"></jsp:include>


<div class="row">
        <div class="col-md-12">
            <h1>Users</h1>
            <a href="<c:url value = "/admin-users-edit" />" /><button class="btn btn-info">Add user</button></a>
        	</br>
        	</br>
            <table id="table" class="table table-striped table-bordered" cellspacing="0" width="100%">
		        <thead>
		            <tr>
		                <th>Name</th>
		                <th>Surname</th>
		                <th>Position</th>
		                <th>Email</th>
		                <th>Start date</th>
		                <th width="75">Action</th>
		            </tr>
		        </thead>
		        <tfoot>
		            <tr>
		                <th>Name</th>
		                <th>Surname</th>
		                <th>Position</th>
		                <th>Email</th>
		                <th>Start date</th>
		                <th></th>
		            </tr>
		        </tfoot>
		        <tbody>
		            <tr>
		                <td>AAATiger</td>
		                <td>Nixon</td>
		                <td>Supervisor</td>
		                <td>nixon@elite-motors.com</td>
		                <td>2011/04/25</td>
		                <td>
		                	<a href="admin-users-edit.php" class="btn btn-sm btn-info"><span class="glyphicon glyphicon-pencil"></span></a>
		                	<span class="btn btn-sm btn-info delete"><span class="glyphicon glyphicon-trash"></span></span>
		                </td>
		            </tr>
		            <tr>
		                <td>BTiger</td>
		                <td>Nixon</td>
		                <td>
		                	Administrator
		                </td>
		                <td>nixon@elite-motors.com</td>
		                <td>2011/04/25</td>
		                <td>
		                	<a href="admin-users-edit.php" class="btn btn-sm btn-info"><span class="glyphicon glyphicon-pencil"></span></a>
		                	<span class="btn btn-sm btn-info delete"><span class="glyphicon glyphicon-trash"></span></span>
		                </td>
		            </tr>
		            <tr>
		                <td>Tiger</td>
		                <td>Nixon</td>
		                <td>
		                	Administrator
		                </td>
		                <td>nixon@elite-motors.com</td>
		                <td>2011/04/25</td>
		                <td>
		                	<a href="admin-users-edit.php" class="btn btn-sm btn-info"><span class="glyphicon glyphicon-pencil"></span></a>
		                	<span class="btn btn-sm btn-info delete"><span class="glyphicon glyphicon-trash"></span></span>
		                </td>
		            </tr>
		            <tr>
		                <td>Tiger</td>
		                <td>Nixon</td>
		                <td>
		                	Administrator
		                </td>
		                <td>nixon@elite-motors.com</td>
		                <td>2011/04/25</td>
		                <td>
		                	<a href="admin-users-edit.php" class="btn btn-sm btn-info"><span class="glyphicon glyphicon-pencil"></span></a>
		                	<span class="btn btn-sm btn-info delete"><span class="glyphicon glyphicon-trash"></span></span>
		                </td>
		            </tr><tr>
		                <td>Tiger</td>
		                <td>Nixon</td>
		                <td>
		                	Administrator
		                </td>
		                <td>nixon@elite-motors.com</td>
		                <td>2011/04/25</td>
		                <td>
		                	<a href="admin-users-edit.php" class="btn btn-sm btn-info"><span class="glyphicon glyphicon-pencil"></span></a>
		                	<span class="btn btn-sm btn-info delete"><span class="glyphicon glyphicon-trash"></span></span>
		                </td>
		            </tr><tr>
		                <td>Tiger</td>
		                <td>Nixon</td>
		                <td>
		                	Administrator
		                </td>
		                <td>nixon@elite-motors.com</td>
		                <td>2011/04/25</td>
		                <td>
		                	<a href="admin-users-edit.php" class="btn btn-sm btn-info"><span class="glyphicon glyphicon-pencil"></span></a>
		                	<span class="btn btn-sm btn-info delete"><span class="glyphicon glyphicon-trash"></span></span>
		                </td>
		            </tr><tr>
		                <td>Tiger</td>
		                <td>Nixon</td>
		                <td>
		                	Administrator
		                </td>
		                <td>nixon@elite-motors.com</td>
		                <td>2011/04/25</td>
		                <td>
		                	<a href="admin-users-edit.php" class="btn btn-sm btn-info"><span class="glyphicon glyphicon-pencil"></span></a>
		                	<span class="btn btn-sm btn-info delete"><span class="glyphicon glyphicon-trash"></span></span>
		                </td>
		            </tr><tr>
		                <td>Tiger</td>
		                <td>Nixon</td>
		                <td>
		                	Administrator
		                </td>
		                <td>nixon@elite-motors.com</td>
		                <td>2011/04/25</td>
		                <td>
		                	<a href="admin-users-edit.php" class="btn btn-sm btn-info"><span class="glyphicon glyphicon-pencil"></span></a>
		                	<span class="btn btn-sm btn-info delete"><span class="glyphicon glyphicon-trash"></span></span>
		                </td>
		            </tr><tr>
		                <td>Tiger</td>
		                <td>Nixon</td>
		                <td>
		                	Administrator
		                </td>
		                <td>nixon@elite-motors.com</td>
		                <td>2011/04/25</td>
		                <td>
		                	<a href="admin-users-edit.php" class="btn btn-sm btn-info"><span class="glyphicon glyphicon-pencil"></span></a>
		                	<span class="btn btn-sm btn-info delete"><span class="glyphicon glyphicon-trash"></span></span>
		                </td>
		            </tr><tr>
		                <td>Tiger</td>
		                <td>Nixon</td>
		                <td>
		                	Administrator
		                </td>
		                <td>nixon@elite-motors.com</td>
		                <td>2011/04/25</td>
		                <td>
		                	<a href="admin-users-edit.php" class="btn btn-sm btn-info"><span class="glyphicon glyphicon-pencil"></span></a>
		                	<span class="btn btn-sm btn-info delete"><span class="glyphicon glyphicon-trash"></span></span>
		                </td>
		            </tr>
		            <tr>
		                <td>Mikjhiger</td>
		                <td>Nixoner</td>
		                <td>
		                	Owner
		                </td>
		                <td>nixon@elite-motors.com</td>
		                <td>2011/04/25</td>
		                <td>
		                	<a href="<c:url value = "/admin-users-edit" />" class="btn btn-sm btn-info"><span class="glyphicon glyphicon-pencil"></span></a>
		                	<span class="btn btn-sm btn-info delete"><span class="glyphicon glyphicon-trash"></span></span>
		                </td>
		            </tr>
		            <tr>
		                <td>Tiger</td>
		                <td>Nixon</td>
		                <td>
		                	Administrator
		                </td>
		                <td>nixon@elite-motors.com</td>
		                <td>2011/04/25</td>
		                <td>
		                	<a href="admin-users-edit.php" class="btn btn-sm btn-info"><span class="glyphicon glyphicon-pencil"></span></a>
		                	<span class="btn btn-sm btn-info delete"><span class="glyphicon glyphicon-trash"></span></span>
		                </td>
		            </tr>
		        </tbody>
		    </table>
        </div>
    </div>
    <jsp:include page="templates/footer.jsp"></jsp:include>