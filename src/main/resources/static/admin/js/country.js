$(document).ready(function () {

    // add Country
    //url: /api/admin/Country-manager
    // input: { name:"name"}
    //output: Country object
    $("#btnAddCountry").click(function (event) {
        var name = $("#txtNameCountry").val();
        jsonData = {name: name};
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/admin/api/Country/add",
            data: JSON.stringify(jsonData),
            contentType: "application/json",
            success: function (data) {
                $("#txtNameCountry").val("");
                $("#datatablesSimple > tbody:last-child")
                    .append(`<tr class="text-center">
                                <td>${data.id} </td>
                                <td>${data.name}</td>
                                
                                <td>
                                    <button class="btn btn-info mx-3" type="button" ${data.id}>Sửa</button>
                                    <button class="btn btn-danger" type="button" ${data.id}>Xóa</button>
                                </td>
                             </tr>`);
            }
        })
    });

    var element=null;
    $(".btnEditCountry").click(function (event){
        var id= $(this).attr("id");
        element=$(this).parent().parent();
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/admin/api/Country/edit?id="+id,
            success:function(data){
                $("#txtIdCountry").val(data.id);
                $("#txtNameCountry").val(data.name);
            }
        })
    })

    $("#btnAddCountry").click(function() {
        var id = $("#txtIdCountry").val();
        var name = $("#txtNameCountry").val();
        jsonData = {
            id: id,
            name: name
        };
        $.ajax({
            type: "PUT",
            url: "http://localhost:8080/admin/api/Country/edit",
            data: JSON.stringify(jsonData),
            contentType: "application/json",
            success: function (data) {
                element.children("td:nth-child(2)").text(data.name);
            }
        })
    });

    $(".btnDeleteCountry").click(function(){
        var parent=$(this).parent().parent();
        var id=parent.children("td:nth-child(1)").text();
        $.ajax({
            type:"DELETE",
            url:"http://localhost:8080/admin/api/Country/delete/"+id,
            success:function(){
                parent.fadeOut('slow', function() {
                    $(this).remove();
                });
            }
        })
    })
})