<template>
    <form class="card auth-card add-client-card" @submit.prevent="submitClient">
        <div class="card-content">
            <span class="card-title">Add client</span>
            <div class="input-field">
                <input
                        id="firstName"
                        type="text"
                        class="validate"
                        v-model="firstName"
                >
                <label for="firstName">First name</label>
            </div>
            <div class="input-field">
                <input
                        id="middleName"
                        type="text"
                        class="validate"
                        v-model="middleName"
                >
                <label for="middleName">Middle name</label>
            </div>
            <div class="input-field">
                <input
                        id="lastName"
                        type="text"
                        class="validate"
                        v-model="lastName"
                >
                <label for="lastName">Last name</label>
            </div>
            <div class="input-field">
                <input
                        id="passport"
                        type="text"
                        class="validate"
                        v-model="passport"
                >
                <label for="passport">Passport</label>
            </div>
            <div class="input-field">
                <input
                        id="birthday"
                        type="date"
                        class="validate"
                        v-model="birthday"
                >
                <label for="birthday">Birthday</label>
            </div>
            <div class="input-checkBox">
                <input
                        id="checkBox"
                        type="checkbox"
                        class="custom-checkbox"
                        v-model="regularClient"
                >
                <label for="checkBox">Regular client</label>
            </div>
            <div class="input-field">
                <input
                        id="clientEmail"
                        type="text"
                        class="validate"
                        v-model="clientEmail"
                >
                <label for="clientEmail">Client email</label>
            </div>
            <div class="input-field">
                <input
                        id="clientPassword"
                        type="password"
                        class="validate"
                        v-model="clientPassword"
                >
                <label for="clientPassword">Client password</label>
            </div>
        </div>
        <div class="card-action">
            <div>
                <button
                        class="btn waves-effect waves-light auth-submit"
                        type="submit"
                >
                    Add client
                </button>
            </div>
        </div>
    </form>
</template>

<script>
    export default {
        name: "AdminAddClient",
        data: () => ({
            firstName: '',
            middleName: '',
            lastName: '',
            passport: '',
            birthday: '',
            regularClient: '',
            clientEmail: '',
            clientPassword: ''
        }),
        methods: {
            async submitClient() {
                const formData = {
                    firstName: this.firstName,
                    middleName: this.middleName,
                    lastName: this.lastName,
                    passport: this.passport,
                    birthday: this.birthday,
                    regularClient: this.regularClient,
                    clientEmail: this.clientEmail,
                    clientPassword: this.clientPassword
                }

                try {
                    await this.$store.dispatch('addClient', formData);
                } catch (e) {}
            }
        }
    }
</script>

<style>
    .add-client-card {
        margin-left: auto;
        margin-right: auto;
    }
</style>


