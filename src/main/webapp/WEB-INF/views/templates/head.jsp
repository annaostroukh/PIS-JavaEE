<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<title>Elite Motors | CRM</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<link rel="stylesheet" type="text/css"
	href="<c:url value ="/resources/css/bootstrap.min.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value ="/resources/css/main.css"/>" /> 
<link rel="stylesheet" 
	href="<c:url value ="/resources/css/jquery-ui.min.css"/>" />
<link href="<c:url value="/resources/css/fullcalendar.css"/>"
	rel="stylesheet" />
<script src = "<c:url value = "https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" />" /></script>
<script type = "text/javascript" charset="utf8" src="<c:url value = "http://cdn.datatables.net/1.10.13/js/jquery.dataTables.js" />" /></script>
<script src = "<c:url value = "https://code.jquery.com/ui/1.12.1/jquery-ui.js" />" /></script>
<script src = "<c:url value = "/resources/js/bootstrap.min.js" />"/></script>
<script>
   $( function() {
     $( "#datepicker" ).datepicker({
    	 dateFormat: 'dd/mm/yy'
     });
     $( "#datepickerT" ).datepicker({
    	 dateFormat: 'dd/mm/yy'
     });
     $("#datepickerT").datepicker('setDate', new Date());
   });
   
	function loadModalBrand() {
		window.open("new/brand", 'window');
	}
</script>
	
</head>
<body onload="loadModalBrand()">
    <nav class="navbar bg-main navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand">Elite Motors</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
