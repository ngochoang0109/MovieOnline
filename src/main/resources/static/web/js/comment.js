$(document).ready(function (){
   $(".btn-add-comment").click(function (){
      let content= $(this).parent().find(".input-comment").val();
      let parentId= $(this).parent().attr("id");
      let movieId=$(this).attr("id");
      let jsonData={
         content:content,
         movieId:movieId,
         parentId:parentId
      }
      console.log(parentId);
      $.ajax({
         type:"POST",
         url:"http://localhost:8080/home/api/add-comment",
         data:JSON.stringify(jsonData),
         contentType:"application/json",
         success:function (data){
            let url="http://localhost:8080/home/movie?id="+data.movieId;
            window.location.replace(url)
         }
      })
   })

   
});