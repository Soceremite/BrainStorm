function DoCheck() {
    var ch = document.getElementsByName("ids");
    if (document.getElementById("allSelect").checked == true) {
        for (var i = 0; i < ch.length; i++) {
            ch[i].checked = true;
        }
    } else {
        for (var i = 0; i < ch.length; i++) {
            ch[i].checked = false;
        }
    }
}

function confirmDelete() {
    var msg = "您确定要删除吗？";
    if (confirm(msg)==true){
        return true;
    }else{
        return false;
    }
}

//获取相对路径
function  getPath(){
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}

/*
//显示和隐藏row-actions
$(".dashboard-comment-wrap").mouseenter(function () {
    $(this).find(".row-actions").show();
})
$(".dashboard-comment-wrap").mouseleave(function () {
    $(this).find(".row-actions").hide();
})
*/
//删除评论
function deleteComment(id) {
    if(confirmDelete()==true){
        $.ajax({
            async: false,
            type: "POST",
            url:'/admin/comment/delete/'+id,
            contentType : "application/x-www-form-urlencoded; charset=utf-8",
            dataType: "text",
            complete:function () {
                window.location.reload();
            }
        })
    }
}


//批准评论
function approveComment(id) {
    $.ajax({
        async: false,
        type: "POST",
        url:'/admin/comment/approve/'+id,
        contentType : "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "text",
        complete:function () {
            window.location.reload();
        }
    })
}


//屏蔽评论
function hideComment(id) {
    $.ajax({
        async: false,
        type: "POST",
        url:'/admin/comment/hide/'+id,
        contentType : "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "text",
        complete:function () {
            window.location.reload();
        }
    })
}

//添加草稿
function insertDraft() {
    if($("#thoughtContent").val!=""&&$("#thoughtTitle").val()!="") {
        $.ajax({
            async: false,
            type: "POST",
            url:'/admin/thought/insert',
            contentType : "application/x-www-form-urlencoded; charset=utf-8",
            dataType: "text",
            complete:function () {
                window.location.reload();
            }
    })
    }

}

//删除想法
function deleteThought(id) {
    if(confirmDelete()==true){
        $.ajax({
            async: false,
            type: "POST",
            url:'/admin/thought/delete/'+id,
            contentType : "application/x-www-form-urlencoded; charset=utf-8",
            dataType: "text",
            complete:function () {
                window.location.reload();
            }
        })
    }
}

//查询想法
function queryThought() {
    //提交form
    $("#thoughtForm").attr("action", "/admin/thought/search");
    $("#thoughtForm").submit();
}

//批量删除想法
function confirmDeleteThoughtBatch() {
    if(confirmDelete()==true){
        var text = $("input:checkbox[name='ids']:checked").map(function(index,elem) {
            return $(elem).val();
        }).get().join(',');
        $.ajax({
            async: false,
            type: "POST",
            url:'/admin/thought/deleteBatch',
            data:{ids:text},
            contentType : "application/x-www-form-urlencoded; charset=utf-8",
            dataType: "text",
            complete:function () {
                window.location.reload();
            }
        })
    }
}

//退出登录
function logout() {
    $.ajax({
        async: false,
        type: "POST",
        url:'/admin/logout',
        contentType : "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "text",
        complete:function () {
            window.location.reload();
        }
    })
}



//添加用户检查用户名是否存在
function checkUserName() {
    var result;
    $.ajax({
        async: false,//同步，待请求完毕后再执行后面的代码
        type: "POST",
        url: '/admin/user/checkUserName',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: {"username": $("#userName").val(), "id": $("#userId").val()},
        dataType: "json",
        success: function (data) {
            //用户存在
            if(data.code==1) {
                $("#userNameTips").html(data.msg);
                //alert("用户名已存在");
                result=1;
            }
            //用户名不存在
            if(data.code==0) {
                $("#userNameTips").html(data.msg);
                result=0;
            }
        },
        error: function () {
            // alert("数据获取失败")
        }
    })
    return result;
}

//添加用户检查电子邮箱是否存在
function checkUserEmail() {
    var result;
    $.ajax({
        async: false,//同步，待请求完毕后再执行后面的代码
        type: "POST",
        url: '/admin/user/checkUserEmail',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: {"email": $("#userEmail").val(), "id": $("#userId").val()},
        dataType: "json",
        success: function (data) {
            //用户存在
            if(data.code==1) {
                $("#userEmailTips").html(data.msg);
                //alert("该邮箱已经注册");
                result=1;
            }
            //用户名不存在
            if(data.code==0) {
                $("#userEmailTips").html(data.msg);
                result=0;
            }
        },
        error: function () {
            //alert("数据获取失败")
        }
    })
    return result;
}



