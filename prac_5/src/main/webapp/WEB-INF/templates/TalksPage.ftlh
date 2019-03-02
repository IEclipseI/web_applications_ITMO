<#-- @ftlvariable name="talksView" type="ru.itmo.webmail.model.domain.Talk.TalkView[]" -->
<#import "common.ftl" as c>

<@c.page>
<div class="form-box">
    <div class="body">
        <form method="post" action="/talks">
            <input type="hidden" name="action" value="sendMessage">
            <div class="field">
                <div class="name">
                    <label for="to">To</label>
                </div>
                <div class="value">
                    <input id="to" name="to" value="${to!}"/>
                </div>
            </div>
            <div class="field">
                <div class="message">
                    <textarea id="text" name="text">${message!}</textarea>
                </div>
            </div>
            <div class="button-field">
                <input type="submit" value="Send">
            </div>
            <#if error??>
            <div class="error">
                ${error}
            </div>
            </#if>
        </form>
    </div>
</div>
<div class="datatable">
    <div class="caption">Users</div>
    <table>
        <thead>
        <tr>
            <th>From</th>
            <th>To</th>
            <th>Message</th>
            <th>Date</th>
        </tr>
        </thead>
        <tbody>
        <#list talksView as view>
        <tr>
            <td>
                ${view.loginFrom}
            </td>
            <td>
                ${view.loginTo}
            </td>
            <td>
                ${view.text}
            </td>
            <td>
                ${view.creationTime}
            </td>
        </tr>
        </#list>
    </table>
</div>
</@c.page>
