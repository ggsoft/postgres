$(function() {

    $("#add").click(function() {
        $.ajax( {
            type: "POST",
            url: "/add",
            data: {date: $("#date").val(),author: $("#author").val(),title: $("#title").val(),text: $("#text").val()},
            dataType: "text",
            success: function(data) {
                $("#list").html(data);
            }
        })
    })

    $("#upd").click(function() {
        $.ajax( {
            type: "POST",
            url: "/upd",
            data: {id: $("#id").val(),date: $("#date").val(),author: $("#author").val(),title: $("#title").val(),text: $("#text").val()},
            dataType: "text",
            success: function(data) {
                $("#list").html(data);
            }
        })
    })

    $("#del").click(function() {
        $.ajax( {
            type: "GET",
            url: "/del",
            data: {id: $("#id").val()},
            dataType: "text",
            success: function(data) {
                $("#list").html(data);
            }
        })
    })

})

