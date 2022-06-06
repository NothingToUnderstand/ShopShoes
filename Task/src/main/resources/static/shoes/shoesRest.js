/*
let procRows = document.querySelectorAll("tbody tr");
for (let i = 0; i < procRows.length; i++) {
    procRows[i].innerHTML += '<td><button class="btnEdit" title="Edit"> E </button></td>';
    //procRows[i].innerHTML += '<td><button class="btnRemove" title="Remove"> R </td>';
}

document.querySelectorAll(".btnEdit").forEach((el) => {
    el.addEventListener("click", function (e) {
        //
    });
});

function getHandler(shoesId) {
    return function (e) {
        //if (e.target.nodeName == "BUTTON") {
        const http = new XMLHttpRequest();
        const url = `/shoes/${shoesId}`;
        const params = null;
        http.open('DELETE', url, true);

        //Send the proper header information along with the request
        http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

        http.onreadystatechange = function () { //Call a function when the state changes.
            if (http.readyState === 4 && http.status === 200) {
                // Remove row on UI
                const cell = e.target.parentNode;
                cell.parentNode.classList.add("hidden");
                e.target.remove();
            } else {
                //alert(http.responseText);
                console.log(http.response);
            }
        }
        http.send(params);
        //}
    }
}

document.querySelectorAll("button.btnRemove").forEach((el) => {
    //el.removeEventListener("click", handler);
    el.addEventListener("click", getHandler(el.getAttribute("data-id")));
});
*/
/**
 * This file contains common methods to work with shoes.
 */
$(document).ready(function() {

    /**
     * Return form data in JSON representation
     * @param $form jQuery selector for the form
     * @returns {{}} - json data of form fields
     */
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
     * Get list of shoes from server
     */
    function getShoes() {
        // Get list of shoes
        $.get( "/rest/shoes", function( data ) {
            //$( ".result" ).html( data );
            console.log('Data received: ', data);
            $.each(data, function(i, item) {
               addRow(item);
                //console.log($tr.wrap('<p>').html());
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
                url: `/rest/shoes/${rowId}`,
                contentType: 'application/x-www-form-urlencoded',//"application/json",
                // dataType: "json",
                // data: JSON.stringify(payload),
                 success: function(data) {
                    console.log('Received data after delete: ', data);
                   $(`table#shoes tr[data-id="${rowId}"]`).remove();
                   // $.remove(`table#shoes tr[data-id="${rowId}"]`);
                },
                error: function(err){
                    console.log('Error during creation of shoes: ', err);
                }
            });
        });
    }
    function addRow(item){
        var $tr = $('<tr>').attr("data-id",item.id).append(
            $('<td>').text(item.id),
            $('<td>').text(item.name),
            $('<td>').text(item.weightKg),
            $('<td>').text(item.color),
            $('<td>').text(item.quantity),
            $('<td>').append($("<button class='btnRemove' data-id='"+item.id+"'>X</button>"))
        ); //.appendTo('#records_table');
        $tr.appendTo('#shoes tbody');
        subscribeOnRemove();


    }

    // Get list of shoes
    getShoes();

    $('button[name="saveButton"]').on('click', function(e) {
        e.preventDefault();
        const payload = getFormData($('#createShoesForm'));
        console.log('Data = ', payload);
        $.ajax({
            type: 'POST',
            url: '/rest/shoes',
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(payload),
            success: function(data) {
                console.log('Received data: ', data);
                addRow(data );
            },
            error: function(err){
                console.log('Error during creation of shoes: ', err);
            }
        });
    });

    //search
    $('button[name="searchButton"]').on('click', function(e) {
        e.preventDefault();
        const payload = getFormData($('#searchShoesForm'));
        console.log('Data = ', payload);
        $.ajax({
            type: 'GET',
            url: `/api/shoes?name=${payload.name}`,
            contentType: "application/json",
            dataType: "json",
            // data: JSON.stringify(payload),
            success: function(data) {
                console.log('Received data: ', data);
               $('#shoes tbody tr').remove();
               for(const el of data){
                   addRow(el);
                }
            },
            error: function(err){
                console.log('Error during creation of shoes: ', err);
            }
        });
    });


    // Remove elements
    //
    // document.querySelectorAll("button.btnRemove").forEach((el) => {
    //     //el.removeEventListener("click", handler);
    //     el.addEventListener("click", getHandler(el.getAttribute("data-id")));
    // });
    // function getHandler(shoesId) {
    //     return function (e) {
    //         //if (e.target.nodeName == "BUTTON") {
    //         const http = new XMLHttpRequest();
    //         const url = `${shoesId}`;
    //         const params = null;
    //         http.open('DELETE', url, true);
    //
    //         //Send the proper header information along with the request
    //         http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    //
    //         http.onreadystatechange = function () { //Call a function when the state changes.
    //             if (http.readyState === 4 && http.status === 200) {
    //                 // Remove row on UI
    //                 const cell = e.target.parentNode;
    //                 cell.parentNode.classList.add("hidden");
    //                 e.target.remove();
    //             } else {
    //                 //alert(http.responseText);
    //                 console.log(http.response);
    //             }
    //         }
    //         http.send(params);
    //         //}
    //     }
    // }
});
