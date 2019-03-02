<#import "/spring.ftl" as spring>
<#import "./common.ftlh" as c>

<#macro error field>
    <@spring.bind field/>
    <#list spring.status.errorMessages as error>
        <div class="error">${error}</div>
        <#break/>
    </#list>
</#macro>

<@c.page>
    <div class="noticeInfoPage">
        <div class="noticeInfo">
            <div class="header">
                ${noticeInfo.creationTime}
            </div>
            <div class="body">
                ${noticeInfo.text?html}
                <br>
                <br>
                By ${noticeInfo.user.login}
            </div>
            <div class="tags">
                    <#list noticeInfo.tags as tag>
                        <div class="tag">
                            ${tag.name}
                        </div>
                    </#list>
            </div>
        </div>

    <#if user??>
    <div class="form-box">
        <div class="header">New comment</div>
        <div class="body">
            <form method="post" action="/notice/${noticeInfo.id}">
                <div class="field">
                    <div class="value">
                        <textarea id="text" rows="5" name="text"></textarea>
                    </div>
                <#--<@error 'notice.text'/>-->
                </div>

                <div class="button-field">
                    <input type="submit" value="Add">
                </div>
            </form>
        </div>
    </div>
    <div class="comments">
    <#list noticeInfo.comments as comment>
        <div class="comment">
            <div class="header">
                ${comment.user.login}
            </div>
            <div class="body">
                ${comment.text?html}
                <br>
                <br>
                ${comment.creationTime}
            </div>
        </div>
    </#list>
    </div>
    </#if>
    </div>
</@c.page>
