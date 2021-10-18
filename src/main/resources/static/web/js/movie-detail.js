$(document).ready(function (){
    $("#add-to-favorite-list").click(function (){
        let id= $(this).parent().attr("id");
        let jsonData={
            id:id
        }
        $.ajax({
            type:"POST",
            url:"http://localhost:8080/home/api/add-favorite-list",
            data:JSON.stringify(jsonData),
            contentType:"application/json",
            success:function (data){
                alert(`${data.username} đã thêm phim ${data.title} vào danh sách yêu thích`);
            }
        })
    });
})