/*
* 提交回复
*/
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

/*
* 二级评论
*/
function collapseComments(e)
{
    let id = e.getAttribute("data-id");
    let comments = $("#comment-"+id);
    //获取一下二级评论的展开状态
    let collapse = e.getAttribute("data-collapse");
    if(collapse){
        //折叠二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    }else{
        //展开二级评论
        comments.addClass("in");//拼接html的class   collapse为collapse in
        //标记二级评论展开状态
        e.setAttribute("data-collapse","in");
        e.classList.add("active");
    }

}