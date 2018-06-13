/**
 * 文章赞赏功能
 * @param id  文章ID
 */
function doPraise(id) {
    $.ajax({
        type: "post",
        url: "/api/doPraise/" + id,
        success: function (json) {
            $.tool.ajaxSuccess(json);
            if (json.status == 200) {
                //赞赏成功，将数量加1
                var countLabel = $(".count");
                countLabel.text(Number(countLabel.text()) + 1);
            }
        },
        error: function (data) {
            $.tool.ajaxError();
        }
    });
}