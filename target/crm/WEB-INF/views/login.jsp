<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<title>Elite Motors | Login</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.min.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/main.css"/>" />     
</head>
<body class="login-container" onload='document.loginForm.userName.focus();'>
	<div class="container-fluid">
		<div class="row">
	        <div class="jumbotron col-md-6 col-md-offset-3">
	            <h1> Welcome back! </h1>
	            <p> Please, log in </p>
	            <form class="form-signin" name="loginForm" action="<c:url value='/login' />" method="POST" accept-charset="utf-8">
		            <c:if test="${not empty error}">
						<div>${error}</div>
					</c:if>
					<c:if test="${not empty message}">
						<div>${message}</div>
					</c:if>
	                <input id="email" name="email" type="email" class="form-control" placeholder="Email address" required autofocus>
	                <input id="password" name="password" type="password" class="form-control" placeholder="Password" required>
	                <div id="remember" class="checkbox">
	                    <label>
	                        <input type="checkbox" value="remember-me"> Remember me
	                    </label>
	                </div>
	                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Sign in</button>
	            	
	            </form><!-- /form -->
	        </div><!-- /jumbotron -->
	    </div>
    </div><!-- /container --> 
</body>
</html>