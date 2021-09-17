$(document).ready(function (){

    // add genre
    //url: /api/admin/genre-manager
    $("#btnAddGenre").click(function (event){
        event.preventDefault();
        var name=$("#txtNameGenre").val();
        jsonData={name:name};
        $.ajax({
            type:"POST",
            url: "http://localhost:8080/admin/api/genre/add",
            data:JSON.stringify(jsonData),
            contentType:"application/json",
            success: function (data){
                // alert("Thêm thành công: "+data.id);
            },
            error:function (){
                alert("That bai");
            }
        })
    });
})