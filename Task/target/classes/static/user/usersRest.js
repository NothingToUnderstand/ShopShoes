$(document).ready(function() {

    function getFormData($form){
        const fieldDataArray = $form.serializeArray();
        const result = {};
        for (const fieldData of fieldDataArray) {
            console.log(':: field data = ', fieldData);
            result[fieldData['name']] = fieldData['value'];
        }
        return result;
    }

    /**
     * Get list of users from server
     */
    function getUsers() {
        // Get list of users
        $.get( "/rest/users", function( data ) {
            $.each(data, function(i, item) {
                
               addRow(item);
            });
        });
    }
    


    function subscribeOnRemove(){
     var btn$ = $('button.btnRemove');
       btn$.unbind();
        btn$.on('click', function(e) {
            e.preventDefault();
            var rowId = $(this).attr("data-id");
           
            $.ajax({
                type: 'DELETE',
                url: `/rest/users/${rowId}`,
                contentType: 'application/x-www-form-urlencoded',
                 success: function(data) {
                    console.log('Received data after delete: ', data);
                   $(`table#users tr[data-id="${rowId}"]`).remove();
                },
                error: function(err){
                    console.log('Error during creation of users: ', err);
                }
            });
        });
    }
   
    function addRow(item){
        var $tr = $('<tr>').attr("data-id",item.id).append(
            $('<td>').text(item.id),
            $('<td>').text(item.username),
            $('<td>').text(item.email),
            $('<td>').text(item.roles[0]['authority']),
            $('<td>').append($("<button class='btnRemove' data-id='"+item.id+"'>X</button>")),
            $('<td>').append($("<button class='btnEdit' data-id='"+item.id+"'>E</button>"))
        ); //.appendTo('#records_table');
        $tr.appendTo('#users tbody');
        subscribeOnRemove();
        subscribeOnEdit();


    }

    // Get list of users
    getUsers();

$('#createUser').click(function() {
        $('#createUserForm').slideToggle(1000);
    });
    $('button[name="saveButton"]').on('click', function(e) {
        e.preventDefault();
        const payload = getFormData($('#createUserForm'));
        console.log('Data = ', payload);
        $.ajax({
            type: 'POST',
            url: '/rest/users',
            contentType: "application/json",
            dataType: "json",       
         data: JSON.stringify(payload),
            success: function(data) {
                console.log('Received data2: ', data);
                addRow(data );
            },
            error: function(err){
                console.log('Error during creation of users: ', err);
            }
        });
    });

    //search
    $('#searchUser').click(function() {
        $('#searchUserForm').slideToggle(1000);
    });

    $('button[name="searchButton"]').on('click', function(e) {
        e.preventDefault();
        const payload = getFormData($('#searchUserForm'));
        
         console.log('Data = ', payload.username);
        $.ajax({
            type: 'GET',
            url: `/rest/users/?username=${payload.username}`, 
            contentType: "application/json",
            dataType: "json",
            // data: JSON.stringify(payload),
            success: function(data) {
                console.log('Received data: ', data);
                $('#users tbody tr').remove();
               if (Array.isArray(data)){
              for(const el of data){
                    addRow(el);
             }
             }else{
                addRow(data);
            }
        },
            error: function(err){
                console.log('Error during searching of users: ', err);
            }
        });
    });


    //edit
  function subscribeOnEdit(){
    var btn$ = $('button.btnEdit');
        btn$.unbind();
    btn$.on('click', function(e) {
        e.preventDefault();   
        var rowId = $(this).attr("data-id");
         var data= $("table#users").find(`tr[data-id="${rowId}"]`);
         data.each(function(){
            $('#usernameEdit').text($(this).find("td").eq(1).text());
            $('#emailEdit').text($(this).find("td").eq(2).text());
           
        })
            $('#editUserForm').slideToggle(1000);
           
            $('button[name="editButton"]').on('click', function(e) {
                e.preventDefault();  
            const payload = getFormData($('#editUserForm'));
            console.log("Payload",payload);
           
                   $.ajax({
                       type: 'POST',
                       url: `/rest/users/${rowId}`,
                       contentType: "application/json",
                       dataType: "json", 
                       data: JSON.stringify(payload),
                        success: function(data) {
                            $("table#users").find(`tr[data-id="${rowId}"]`).remove();
                            addRow(data );
                           console.log('Received data after edit: ', data);
                       },
                       error: function(err){
                           console.log('Error during editing of users: ', err);
                       }
                   });
                });
                });
             
         };

    //account
    $('#accbtn').on('click', function(e) {
        $('#acc').slideToggle(1000);
       
        $.ajax({
            type: 'GET',
            url: `/rest/users/acc`, 
            contentType: "application/json",
            dataType: "json",
            success: function(data) {
                console.log("Acc data :",data);
                // $.each(data, function(i, item) {
                $('#idAcc').text(data.id);
                $('#usernameAcc').text(data.username);
                $('#emailAcc').text(data.email);
                $('#passwordAcc').text(data.password);
                $('#roleAcc').text(data.roles[0]['authority']);
                
           
        },
            error: function(err){
                console.log('Error during auth: ', err);
            }
        });
    });
            });