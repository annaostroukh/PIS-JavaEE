<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fc" uri="http://custom.function/EL" %> 

</div> <!--/.Main container -->
<!--  <script src="<c:url value="/resources/js/jquery.min.js"/>"></script> -->
<script src="<c:url value="/resources/js/moment.min.js"/>"></script>
<script src="<c:url value="/resources/js/fullcalendar.js"/>"></script>
<script>
  $(document).ready(function() {
    $('#table').DataTable({
    	"paging":   false,
        "ordering": false,
        "info":     false
    });
  });
  
  $(document).ready(function() {
	  
	  $('#calendar').fullCalendar({
		  windowResize: function(view) { },
		  height: 670,
		  header: {
              left: 'title',
              right: ''
          },
          footer: {
        	  left: 'prev,next today',
        	  right: 'month,agendaWeek,agendaDay'
          },
          defaultView: 'agendaWeek',
          timezone: "local",
          ignoreTimezone: false,
          editable: true,
          selectable: true,
          minTime: "07:00:00",
          maxTime: "18:00:00",
          allDay: false,
          eventSources: [ 
        	{
		          events: [
		        	  <c:forEach var='meeting' items='${meetings}'>	
		        	  {
		        		  title  : '${meeting.title}',
		                  start  : '${fc:formatDate(meeting.date, "yyyy-MM-dd")}T${meeting.time}' 
		              },
		        	  </c:forEach>
		          ],
		          color: '#FFA000',     
		          textColor: 'white' 
        	} 
        ]
		    
	 });
  
  });
  //example of ajax delete function
      function deleteUser(url, department_id, elem) {
          var data = {
              'id': department_id
          };
          $.post({
              url: url,
              data: data,
              success: function() {
                  $(elem).parents('tr').hide();
              },
              error: function() {
                  location.reload();
              }
          });
      } // end example
      $('#table .delete').click(function() {
            //var id = $(this).attr("data-id");
            //deleteDepartment(url, id, this);
            $(this).parents('tr').hide();
        });
</script>
</body>
</html>