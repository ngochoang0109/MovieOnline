$(document).ready(function () {

    // add YearRelease
    //url: /api/admin/YearRelease-manager
    // input: { name:"name"}
    //output: YearRelease object
    $("select.yearSelect").change(function(){
        var selectedYear = $(this).children("option:selected").val();
        console.log(selectedYear);
        // alert("You have selected the country - " + selectedYear);
        $("#txtNameYearRelease").val(selectedYear);
        $("#btnAddYearRelease").click(function (event) {
            var year = $("#txtNameYearRelease").val();
            jsonData = {year: year};
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/admin/api/YearRelease/add",
                data: JSON.stringify(jsonData),
                contentType: "application/json",
                success: function (data) {
                    $("#txtNameYearRelease").val("");
                    $("#datatablesSimple > tbody:last-child")
                        .append(`<tr class="text-center">
                                <td>${data.id} </td>
                                <td>${data.year}</td>
                                <td>
                                    <button class="btn btn-info mx-3" type="button" ${data.id}>Sửa</button>
                                    <button class="btn btn-danger" type="button" ${data.id}>Xóa</button>
                                </td>
                             </tr>`);
                }
            })
        });

        var element=null;
        $(".btnEditYearRelease").click(function (event){
            var id= $(this).attr("id");
            element=$(this).parent().parent();
            $.ajax({
                type: "GET",
                url: "http://localhost:8080/admin/api/YearRelease/edit?id="+id,
                success:function(data){
                    $("#txtIdYearRelease").val(data.id);
                    $("#txtNameYearRelease").val(data.year);
                }
            })
        })

        $("#btnAddYearRelease").click(function() {
            var id = $("#txtIdYearRelease").val();
            var year = $("#txtNameYearRelease").val();
            jsonData = {
                id: id,
                year: year
            };
            $.ajax({
                type: "PUT",
                url: "http://localhost:8080/admin/api/YearRelease/edit",
                data: JSON.stringify(jsonData),
                contentType: "application/json",
                success: function (data) {
                    element.children("td:nth-child(2)").text(data.year);
                }
            })
        });

        $(".btnDeleteYearRelease").click(function(){
            var parent=$(this).parent().parent();
            var id=parent.children("td:nth-child(1)").text();
            $.ajax({
                type:"DELETE",
                url:"http://localhost:8080/admin/api/YearRelease/delete/"+id,
                success:function(){
                    parent.fadeOut('slow', function() {
                        $(this).remove();
                    });
                }
            })
        })
    });

})