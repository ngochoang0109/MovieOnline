$(document).ready(function (){
   $(".btn-add-comment").click(function (){
      let content= $(this).parent().find(".input-comment").val();
      let movieId=$(this).attr("id");
      let jsonData={
         content:content,
         movieId:movieId,
         parentId:1
      }
      $.ajax({
         type:"POST",
         url:"http://localhost:8080/home/api/add-comment",
         data:JSON.stringify(jsonData),
         contentType:"application/json",
         success:function (data){
            console.log(data);
         }
      })
   })
});