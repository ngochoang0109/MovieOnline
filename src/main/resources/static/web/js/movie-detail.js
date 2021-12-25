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

    $(".btn-add-comment").click(function (){
        let content= $(this).parent().find(".input-comment").val();
        let parentId= $(this).parent().attr("id");
        let movieId=$(this).attr("id");
        let jsonData={
            content:content,
            movieId:movieId,
            parentId:parentId
        }
        $.ajax({
            type:"POST",
            url:"http://localhost:8080/home/api/add-comment",
            data:JSON.stringify(jsonData),
            contentType:"application/json",
            success:function (data){
                location.reload(true);
            }
        })});
    $(".rate-movie").click(function (){
        let getValue= $(this).attr("value");
        let id=$(".btn-add-comment").attr("id");
        let rate;
        switch (getValue){
            case "5":
                rate=5;
                break;
            case "4 and a half":
                rate=4.5;
                break;
            case "4":
                rate=4;
                break;
            case "3 and a half":
                rate=3.5;
                break;
            case "3":
                rate=3;
                break;
            case "2 and a half":
                rate=2.5;
                break;
            case "2":
                rate=2;
                break;
            case "1 and a half":
                rate=1.5;
                break;
            case "1":
                rate=1;
                break;
        }
        jsonData={
            id:id,
            rate:rate
        }
        $.ajax({
            type:"POST",
            url:"http://localhost:8080/home/api/rating-movie",
            data:JSON.stringify(jsonData),
            contentType:"application/json",
            success:function (data){
                if (data.rate>=4){
                    alert("Bạn đã đánh giá cho phim này "+data.rate+" sao, " +
                        "do vậy hệ thống tự động thêm phim này vào danh sách yêu thích của bạn");
                }
                else{
                    alert("Cảm ơn bạn đã đánh giá phim, chúng tôi sẽ cố gắng đem đến phim hay hơn nữa");
                }

            }
        })
    })
})