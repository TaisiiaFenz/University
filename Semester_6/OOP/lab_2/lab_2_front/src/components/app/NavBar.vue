<template>
        <nav class="navbar lighten-1">
            <div class="nav-wrapper">
                <div class="navbar-left">
                    <a href="#" @click.prevent="$emit('click')">
                        <i>-</i>
                    </a>
                </div>

                <div class="navbar-right">
                    <ul class="right hide-on-small-and-down">
                        <li>
                            <a
                                    class="dropdown-trigger black-text"
                                    href="#"
                                    data-target="dropdown"
                                    @click.prevent="logout"
                            >
                                <span class="Logout">Log out</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
</template>

<script>

    export default {
        name: 'NavBar',
        methods: {
            logout() {
                this.$router.push('/');
            }
        }
    };
</script>
