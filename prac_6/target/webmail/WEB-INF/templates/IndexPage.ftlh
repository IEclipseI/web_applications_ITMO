<#-- @ftlvariable name="message" type="java.lang.String" -->


<#import "common.ftl" as c>

<@c.page>
    <#if message??>
        <p>${message}</p>
    </#if>
<div class="articlesBody">
    <p class="no-data"></p>
</div>

<template class="article_tmpl">
    <article>
        <div class="title"></div>
        <div class="information"></div>
        <div class="body"></div>
        <#--<ul class="attachment">-->
            <#--<li>Announcement of <a href="#">Codeforces Round #510 (Div. 1)</a></li>-->
            <#--<li>Announcement of <a href="#">Codeforces Round #510 (Div. 2)</a></li>-->
        <#--</ul>-->
        <div class="footer">
            <div class="left">
                <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
                <span class="positive-score">0</span>
                <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
            </div>
            <div class="right">
                <#--<img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>-->
                <#--<img src="img/comments_16x16.png" title="Comments" alt="Comments"/>-->
                <div class="creationTime"></div>
                <#--<a href="#">68</a>-->
            </div>
        </div>
    </article>
</template>
<script>
    $(function () {
        var $noData = $(".articlesBody .no-data");
        $noData.text("Loading...");
        $.post("", {action: "findArticles"}, function (articles) {
            var $articlesBody = $(".articlesBody");
            if (articles.length > 0) {
                $articlesBody.empty();
                articles.forEach(function (article) {
                    var $art = cloneTemplate($("template.article_tmpl"));
                    $art.find(".title").text(article.title);
                    $art.find(".body").text(article.text);
                    $art.find(".information").text("By " + article.user.login);
                    $art.find(".creationTime").text(article.creationTime);
                    $articlesBody.append($art);
                });
            } else {
                $noData.text("No data");
            }
        })
    })
</script>

</@c.page>