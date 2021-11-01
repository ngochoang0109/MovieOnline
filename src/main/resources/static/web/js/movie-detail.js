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

    $(".btn-watch-movie").click(function (){
        let id= $(this).attr("id");
        let url="http://localhost:8080/home/movie/watch?id="+id;
        window.location.replace(url)
    });

    // $(".plyr__control--overlaid").click(function (){
    //     $.ajax({
    //         type:"GET",
    //         url:"http://localhost:8080/api/stream/video",
    //         success:function (data){
    //             console.log(typeof(data) )
    //             $("#player source").attr("src", data);
    //         }
    //     })
    // });
})