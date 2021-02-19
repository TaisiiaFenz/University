<template>
        <nav class="navbar lighten-1">
            <div class="nav-wrapper">
                <div class="navbar-left">
                    <a href="#">
                        <i class="material-icons black-text">dehaze</i>
                    </a>
                    <span class="black-text">12.12.12</span>
                </div>

                <div class="navbar-right">
                    <ul class="right hide-on-small-and-down">
                        <li>
                            <a
                                    class="dropdown-trigger black-text"
                                    href="#"
                                    data-target="dropdown"
                            >
                                USER NAME
                                <i class="material-icons right">arrow_drop_down</i>
                            </a>

                            <ul id='dropdown' class='dropdown-content'>
                                <li>
                                    <a href="#" class="black-text">
                                        <i class="material-icons">account_circle</i>Profile
                                    </a>
                                </li>
                                <li class="divider" tabindex="-1"></li>
                                <li>
                                    <a href="#" class="black-text">
                                        <i class="material-icons">assignment_return</i>Sign out
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
</template>

<script>
    export default {
        name: 'NavBar'
    };
</script>
