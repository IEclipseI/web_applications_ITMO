<#import "common.ftl" as c>

<@c.page>
<div class="form-box">
    <div class="header">Enter</div>
    <div class="body">
        <form method="post" action="/addNews">
            <input type="hidden" name="action" value="addNews">
            <div class="field">
                <div class="name">
                    <label for="textarea">Write your news</label>
                </div>
                <div class="value">
                    <textarea id="textarea" name="textarea"></textarea>
                </div>
            </div>
            <div class="button-field">
                <input type="submit" value="Enter">
            </div>
            <#if error??>
            <div class="error">
                ${error}
            </div>
            </#if>
        </form>
    </div>
</div>
</@c.page>
