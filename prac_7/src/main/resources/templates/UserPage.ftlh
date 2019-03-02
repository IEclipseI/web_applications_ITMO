<#import "./common.ftlh" as c>

<@c.page>
<div class="profile">
    <#if userProfile??>
        <div class="field">
            ID: ${userProfile.id}
        </div>
        <div class="field">
            Login: ${userProfile.login}
        </div>
        <div class="field">
            Registration date: ${userProfile.creationTime}
        </div>
    <#else>
        <div class="field">
            User does not exists
        </div>
    </#if>
</div>
</@c.page>
