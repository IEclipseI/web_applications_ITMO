<template>
    <div class="enter form-box">
        <div class="header">Register</div>
        <div class="body">
            <form @submit.prevent="onRegister">
                <div class="field">
                    <div class="name">
                        <label for="login">Login</label>
                    </div>
                    <div class="value">
                        <input id="login" name="login" v-model="login"/>
                    </div>
                </div>

                <div class="field">
                    <div class="name">
                        <label for="user_name">Name</label>
                    </div>
                    <div class="value">
                        <input id="user_name" name="user_name" v-model="user_name"/>
                    </div>
                </div>

                <div class="error">{{error}}</div>

                <div class="button-field">
                    <input type="submit" value="Register">
                </div>
            </form>
        </div>
    </div>
</template>

<script>
    export default {
        data: function() {
            return {
                login: "",
                user_name: "",
                error: ""
            }
        },
        beforeCreate() {
            this.$root.$on("onRegisterSuccess", () => {
                this.changePage('Index');
            });
        },
        name: "Register",
        beforeMount() {
            this.login = "";
            this.user_name = "";
            this.error = "";
            this.$root.$on("onRegisterValidationError", (error) => {
                this.error = error;
            });
        }, methods: {
            onRegister: function () {
                this.$root.$emit("onRegister", this.login, this.user_name);
            }
        }
    }
</script>

<style scoped>

</style>
