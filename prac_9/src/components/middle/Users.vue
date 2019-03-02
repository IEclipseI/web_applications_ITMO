<template>
    <div class="datatable">
        <div class="caption">Users</div>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Login</th>
                <th>Name</th>
            </tr>
            </thead>
            <tbody>
            <UserView v-for="user_ in viewUsers" :user="user_" :key="user_.id"/>
            </tbody>
        </table>
    </div>
</template>

<script>
    import UserView from './UserView';

    export default {
        name: "Users",
        props: ['users'],
        computed: {
            viewUsers: function () {
                return Object.values(this.users).sort((a, b) => -b.id + a.id);
            }
        },
        components: {
            UserView
        }
    }
</script>

<style scoped>

</style>
