<#import "./common.ftlh" as c>

<@c.page>
<div class="datatable">
    <div class="caption">Users</div>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Login</th>
            <th>Created At</th>
            <th>Disable/disable</th>
        </tr>
        </thead>
        <tbody>
    <#list users as user>
    <tr>
        <td><a href="/user/${user.id}">${user.id}</a></td>
        <td>${user.login}</td>
        <td>${user.creationTime}</td>
        <td>
            <div class="body">
                <form method="post" action="/users/change_status_${user.id}">
                    <div class="button-field">
                        <input type="submit" value="<#if user.disabled>Enable<#else>Disable</#if>">
                    </div>
                    <div class="error"></div>
                </form>
            </div>
        </td>
    </tr>
    </#list>
        </tbody>
    </table>
</div>
</@c.page>
