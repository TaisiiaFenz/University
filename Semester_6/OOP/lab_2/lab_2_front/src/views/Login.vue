<template>
    <form class="card auth-card" @submit.prevent="submitHandler">
        <div class="card-content">
            <span class="card-title center">Login</span>
            <div class="input-field">
                <input
                        id="email"
                        type="text"
                        class="validate"
                        v-model="email"
                        :class="{invalid: ($v.email.$dirty && !$v.email.required) ||
                            ($v.email.$dirty && !$v.email.email)}"
                >
                <label for="email">Email</label>
                <small
                        class="helper-text invalid"
                        v-if="($v.email.$dirty && !$v.email.required)"
                >Email should not be empty</small>
                <small
                        class="helper-text invalid"
                        v-else-if="($v.email.$dirty && !$v.email.email)"
                >Enter correct email</small>
            </div>
            <div class="input-field">
                <input
                        id="password"
                        type="password"
                        class="validate"
                        v-model="password"
                        :class="{invalid: ($v.password.$dirty && !$v.password.required) ||
                            ($v.password.$dirty && !$v.password.minLength)}"
                >
                <label for="password">Password</label>
                <small class="helper-text invalid"
                       v-if="$v.password.$dirty && !$v.password.required"
                >
                    Enter password
                </small>
                <small class="helper-text invalid"
                       v-else-if="$v.password.$dirty && !$v.password.minLength"
                >
                    Password must be {{$v.password.$params.minLength.min}} symbols. Now it is {{password.length}}
                </small>
            </div>
        </div>
        <div class="card-action">
            <div>
                <button
                        class="btn waves-effect waves-light auth-submit"
                        type="submit"
                >
                    Sign in
                </button>
            </div>
        </div>
    </form>
</template>

<script>
    import {email, required, minLength} from 'vuelidate/lib/validators'

    export default {
        name: "login",
        data: () => ({
            email: '',
            password: ''
        }),
        validations: {
            email: {email, required},
            password: {required, minLength: minLength(3)}
        },
        methods: {
            async submitHandler() {
                if (this.$v.$invalid) {
                    this.$v.$touch();
                    return;
                }
                const formData = {
                    username: this.email,
                    password: this.password
                }

                let resultData = await this.$store.dispatch('login', formData);

                if (!resultData) {
                    alert("Try one more time");
                } else if (resultData.role == 'AGENT') {
                    console.log(this.$store.getters.userToken);
                    this.$router.push('/add-client');
                } else if (resultData.role == 'CLIENT') {
                    this.$router.push('/client-tours');
                }
            }
        }
    }
</script>
