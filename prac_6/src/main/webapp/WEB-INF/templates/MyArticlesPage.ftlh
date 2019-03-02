<#-- @ftlvariable name="users" type="ru.itmo.webmail.model.domain.User[]" -->
<#-- @ftlvariable name="articles" type="ru.itmo.webmail.model.domain.Article[]" -->

<#import "common.ftl" as c>

<@c.page>
<div class="datatable articles">
    <div class="caption">Articles</div>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Hide/Show</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td colspan="3" class="no-data">No data</td>
        </tr>
        </tbody>
    </table>
</div>

<template class="articles-tr">
    <tr>
        <td class="id"></td>
        <td class="title"></td>
        <td class="button">
            <div class="button-field">
                <input type="submit" class="hide_show">
            </div>
        </td>
    </tr>
</template>
<script>
    $(function () {
        var $noData = $(".articles .no-data");
        $noData.text("Loading...");
        $.post("", {action: "findArticles"}, function (articles) {
            var $tbody = $(".articles tbody");
            if (articles.length > 0) {
                $tbody.empty();
                articles.forEach(function (article) {
                    var $tr = cloneTemplate($("template.articles-tr"));
                    $tr.find(".id").text(article.id);
                    $tr.find(".title").text(article.title);
                    if (article.hidden) {
                        $tr.find(".hide_show").attr("value", "Show");
                    } else {
                        $tr.find(".hide_show").attr("value", "Hide");
                    }
                    $tbody.append($tr);
                });
            } else {
                $noData.text("No data");
            }
        })
    });
    // $(document).ready(function () {
    $(function () {
        $(".datatable.articles").on("click", ".hide_show", function () {
            var button = $(this);
            button.attr("disabled", "");
            var articleId = $(this).parent().parent().parent().find(".id").html();
            var old = this.value;
            $.post("", {action: "switchHidden", articleId: articleId}, function (response) {
                if (response["success"]) {
                    if (old === "Hide") {
                        button.attr("value", "Show");
                    } else {
                        button.attr("value", "Hide");
                    }
                }
                button.removeAttr("disabled");
            });
        });
    });

</script>

</@c.page>
