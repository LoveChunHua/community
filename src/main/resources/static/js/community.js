function post() {
    let questionId = $("#question_id").val();
    let content = $("#comment_content").val();

    if(!content){
        alert("不能回复空内容 ");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": questionId,
            "content": content,
            "type": 1
        }),
        success: function (response) {
            if (response.code == 200) {
                window.location.reload();
            } else {
                if (response.code == 2003) {
                    let isAccepted = confirm(response.message);
                    if(isAccepted){
                        window.open("https://github.com/login/oauth/authorize?client_id=3de3c8f78dd44d09f533&redirect_uri=http://localhost:8080/callback&scope=user&state=1")
                        window.localStorage.setItem("closable","true");
                    }
                } else {
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}