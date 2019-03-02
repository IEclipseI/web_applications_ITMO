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
<div class="notice form-box">
    <div class="header">Notice creation</div>
    <div class="body">
        <form method="post" action="/notice">
            <div class="field">
                <div class="text">
                    <textarea id="text" name="text">${noticeForm.text!}</textarea>
                </div>
                <@error 'noticeForm.text'/>
            </div>
            <div class="button-field">
                <input type="submit" value="Add notice">
            </div>
            <div class="error"></div>
        </form>
    </div>
</div>
</@c.page>
