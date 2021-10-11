$(document).ready(function () {

    // add genre
    //url: /api/admin/genre-manager
    // input: { name:"name"}
    //output: genre object
    $("#btnAddGenre").click(function (event) {
        var name = $("#txtNameGenre").val();
        jsonData = {name: name};
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/admin/api/genre/add",
            data: JSON.stringify(jsonData),
            contentType: "application/json",
            success: function (data) {
                $("#txtNameGenre").val("");
                $("#datatablesSimple > tbody:last-child")
                    .append(`<tr class="text-center">
                                <td>${data.id} </td>
                                <td>${data.name}</td>
                                <td>New York</td>
                                <td>
                                    <button class="btn btn-info mx-3 btnEditGenre" id="${data.id}" type="button">Sửa</button>
                                    <button class="btn btn-danger btnDeleteGenre" id="${data.id}" type="button">Xóa</button>
                                </td>
                             </tr>`);
            }
        })
    });

    var element=null;
    $(".btnEditGenre").click(function (event){
        var id= $(this).attr("id");
        element=$(this).parent().parent();
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/admin/api/genre/edit?id="+id,
            success:function(data){
                $("#txtIdGenre").val(data.id);
                $("#txtNameGenre").val(data.name);
            }
        })
    })

    $("#btnEdit").click(function() {
        var id = $("#txtIdGenre").val();
        var name = $("#txtNameGenre").val();
        jsonData = {
            id: id,
            name: name
        };
        $.ajax({
            type: "PUT",
            url: "http://localhost:8080/admin/api/genre/edit",
            data: JSON.stringify(jsonData),
            contentType: "application/json",
            success: function (data) {
                element.children("td:nth-child(2)").text(data.name);
            }
        })
    });

    $(".btnDeleteGenre").click(function(){
        console.log("delete");
        var parent=$(this).parent().parent();
        var id=parent.children("td:nth-child(1)").text();
        $.ajax({
            type:"DELETE",
            url:"http://localhost:8080/admin/api/genre/delete/"+id,
            success:function(){
                parent.fadeOut('slow', function() {
                    $(this).remove();
                });
            }
        })
    })
})