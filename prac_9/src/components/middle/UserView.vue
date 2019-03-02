<template>
    <tr>
        <td>{{user.id}}</td>
        <td>{{user.login}}</td>
        <td>{{user.name}}</td>
    </tr>
</template>

<script>
    export default {
        props: ['user'],
        name: "UserView"
    }
</script>

<style scoped>

</style>
