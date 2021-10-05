$(document).ready(function (){
    $(".play-button").click(function (event){
        var id= $(this).attr("id");
        window.location.replace("http://localhost:8080/movie?id="+id)
    })
});