<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

</div> <!--/.Main container -->
<script>
  $(document).ready(function() {
    $('#table').DataTable();
  } );
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