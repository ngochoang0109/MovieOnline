$(document).ready(function(){

    $("#addDirector").click(function (event){
        var name=$("#txtNameDirector").val();
        var jsonData={
            name:name
        };
        $.ajax({
            type: "POST",
            url:"http://localhost:8080/admin/api/directors/add",
            data:JSON.stringify(jsonData),
            contentType:"application/json",
            success:function (data){
                $("#txtNameDirector").val("");
                $(".directorTable > tbody:last-child")
                    .append(`<tr>
                                <td>${data.id}</td>
                                <td>${data.name}</td>
                                <td>
                                    <button class="btn btn-info me-4" id="${data.id}" type="button">Sửa</button>
                                    <button class="btn btn-danger" id="${data.id}" type="button">Xóa</button>
                                </td>
                             </tr>`);
            }
        });
    })

    // var element=null;
    // $(".btnEditCast").click(function (event){
    //     var id= $(this).attr("id");
    //     element=$(this).parent().parent();
    //     $.ajax({
    //         type: "GET",
    //         url: "http://localhost:8080/admin/api/genre/edit?id="+id,
    //         success:function(data){
    //             $("#txtNameCast").val(data.name);
    //         }
    //     })
    // })


})