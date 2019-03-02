<#-- @ftlvariable name="users" type="ru.itmo.webmail.model.domain.User[]" -->
<#-- @ftlvariable name="user" type="ru.itmo.webmail.model.domain.User" -->
<#import "common.ftl" as c>

<@c.page>
<div class="datatable admins">
    <div class="caption">Admins</div>
    <table>
        <thead>
        <tr>
            <th>#</th>
            <th>Login</th>
            <th>Admin</th>
            <#if user.admin>
            <th>Enable/Disable</th>
            </#if>
        </tr>
        </thead>
        <tbody>
        <tr>
    <#if user.admin>
        <td colspan="4" class="no-data">No data</td>
    <#else>
        <td colspan="3" class="no-data">No data</td>
    </#if>
        </tr>
        </tbody>
    </table>
</div>

<template class="users-tr">
    <tr>
        <td class="id"></td>
        <td class="login"></td>
        <td class="admin"></td>
        <#if user.admin>
            <td class="button">
                <div class="button-field">
                    <input type="submit" class="enable_disable">
                </div>
            </td>
        </#if>
    </tr>
</template>
<script>
    $(function () {
        var $noData = $(".admins .no-data");
        $noData.text("Loading...");
        $.post("", {action: "find"}, function (users) {
            var $tbody = $(".admins tbody");
            if (users.length > 0) {
                $tbody.empty();
                users.forEach(function (user) {
                    var $tr = cloneTemplate($("template.users-tr"));
                    $tr.find(".id").text(user.id);
                    $tr.find(".login").text(user.login);
                    $tr.find(".admin").text(user.admin);
                    $tr.find("tr").attr("id", "user_" + user.login.toLowerCase());
                    if (user.admin) {
                        $tr.find(".enable_disable").attr("value", "Disable");
                    } else {
                        $tr.find(".enable_disable").attr("value", "Enable");
                    }
                    $tbody.append($tr);
                });
            } else {
                $noData.text("No data");
            }
        })
    })
</script>
<#if user.admin>
    <script>
        $(function () {
            $(".datatable.admins").on("click", ".enable_disable", function () {
                var $button = $(this);
                $button.attr("disabled", "");
                var userToChangeId = $button.parent().parent().parent().find(".id").html();
                var old = this.value;
                $.post("", {action: "switchAdmin", userToChangeId: userToChangeId}, function (response) {
                    if (response["success"]) {
                        if (old === "Disable") {
                            $button.attr("value", "Enable");
                            $button.parent().parent().parent().find(".admin").text("false");
                        } else {
                            $button.attr("value", "Disable");
                            $button.parent().parent().parent().find(".admin").text("true");
                        }
                    }
                    $button.removeAttr("disabled");
                });
            });
        });
    </script>
</#if>
</@c.page>
