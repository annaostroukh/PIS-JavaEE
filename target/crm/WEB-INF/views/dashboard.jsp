<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fc" uri="http://custom.function/EL" %> 

<jsp:include page="templates/supervisor_header.jsp"></jsp:include>
 <div class="row">
        <div class="col-md-12">
            <h1>Dashboard</h1>
        	</br>
        	<div class="row">
        		<div class="col-md-3">
        			<h4> Clients waiting for a manager: </h4>
        			<div class="jumbotron text-center">
        				<h1>3</h1><br>
        				lonely clients
        			</div>
        		</div>
        		<div class="col-md-3">
        			<h4> Most busy manager: </h4>
        			<div class="jumbotron text-center">
        				<h1>8</h1>
        				meetings this week
        				<b>Name Surname</b>
        			</div>
        		</div>
        		<div class="col-md-3">
        			<h4> Less busy manager: </h4>
        			<div class="jumbotron text-center">
        				<h1>2</h1>
        				meetings this week
        				<b>Name Surname</b>
        			</div>
        		</div>
        		<div class="col-md-3">
        			<h4> Today: </h4>
        			<div class="jumbotron text-center">
        				<h1>24</h1>
        				meetings<br>
        				by <b>14</b> managers
        			</div>
        		</div>
        	</div>
        	<div class="row">
        		<div class="col-sm-12">
		        	<h4> Clients waiting for a manager: </h4>
		            <table class="table table-striped table-bordered" cellspacing="0" width="100%">
				        <thead>
				            <tr>
				                <th>Client</th>
                                <th>Status</th>
                                <th>Phone</th>
                                <th>Email</th>
                                <th>Manager</th>
                                <th>Save</th>
				            </tr>
				        </thead>
				        <tbody>
				            <tr>
				                <td>AAATiger</td>
				                <td>new</td>
				                <td>000000</td>
				                <td>nixon@elite-motors.com</td>
				                <td>
				                	<select>
				                		<option>User (8 clients)</option>
				                		<option>User (8 clients)</option>
				                		<option>User (8 clients)</option>
				                	</select>
				                </td>
				                <td>
				                	<a href="admin-users-edit.php" class="btn btn-sm btn-info"><span class="glyphicon glyphicon-floppy-disk"></span></a>
				                </td>
				            </tr>
				            <tr>
				                <td>AAATiger</td>
				                <td>new</td>
				                <td>000000</td>
				                <td>nixon@elite-motors.com</td>
				                <td>
				                	<!-- system suggests less busy managers firstly, and doesn't show the ones who has more then 10 active clients -->
				                	<select>
				                		<option>User (8 clients)</option>
				                		<option>User (8 clients)</option>
				                		<option>User (8 clients)</option>
				                	</select>
				                </td>
				                <td>
				                	<a href="admin-users-edit.php" class="btn btn-sm btn-info"><span class="glyphicon glyphicon-floppy-disk"></span></a>
				                </td>
				            </tr>
				            <tr>
				                <td>AAATiger</td>
				                <td>new</td>
				                <td>000000</td>
				                <td>nixon@elite-motors.com</td>
				                <td>
				                	<select>
				                		<option>User (8 clients)</option>
				                		<option>User (8 clients)</option>
				                		<option>User (8 clients)</option>
				                	</select>
				                </td>
				                <td>
				                	<a href="admin-users-edit.php" class="btn btn-sm btn-info"><span class="glyphicon glyphicon-floppy-disk"></span></a>
				                </td>
				            </tr>
				        </tbody>
				    </table>
				</div>
			</div>
				
			<div class="row">
				<div class="col-sm-12">
					<h4>Managers workload:</h4>
		            <table id="table" class="table table-striped table-bordered" cellspacing="0" width="100%">
				        <thead>
				            <tr>
				                <th>Name</th>
				                <th>Surname</th>
				                <th>Email</th>
				                <th>Meetings today</th>
				                <th>Meetings this week</th>
				                <th>Active clients</th>
				            </tr>
				        </thead>
				        <tbody>
				            <tr>
				                <td>AAATiger</td>
				                <td>Nixon</td>
				                <td>nixon@elite-motors.com</td>
				                <td>4</td>
				                <td>8</td>
				                <td>12</td>
				            </tr>
				            <tr>
				                <td>AAATiger</td>
				                <td>Nixon</td>
				                <td>nixon@elite-motors.com</td>
				                <td>4</td>
				                <td>9</td>
				                <td>10</td>
				            </tr>
				            <tr>
				                <td>AAATiger</td>
				                <td>Nixon</td>
				                <td>nixon@elite-motors.com</td>
				                <td>2</td>
				                <td>8</td>
				                <td>22</td>
				            </tr>
				            <tr>
				                <td>BBAATiger</td>
				                <td>KNixon</td>
				                <td>nixon@elite-motors.com</td>
				                <td>0</td>
				                <td>8</td>
				                <td>12</td>
				            </tr>
				        </tbody>
				    </table>
				</div>
			</div>
        </div>
    </div>

 <jsp:include page="templates/footer.jsp"></jsp:include>