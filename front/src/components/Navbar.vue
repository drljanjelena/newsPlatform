<template>
    <div>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand">CMS</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <router-link :to="{name: 'Kategorije'}" tag="a" class="nav-link" :class="{active: this.$router.currentRoute.name === 'Kategorije'}">Kategorije</router-link>
                        </li>
                        <li class="nav-item">
                            <router-link :to="{name: 'SveVesti'}" tag="a" class="nav-link" :class="{active: this.$router.currentRoute.name === 'Vesti'}">Vesti</router-link>
                        </li>
                        <li class="nav-item">
                            <router-link :to="{name: 'Korisnici'}" tag="a" class="nav-link" :class="{active: this.$router.currentRoute.name === 'Korisnici'}">Korisnici</router-link>
                        </li>
                    </ul>

                    <form class="d-flex">
                        <input class="form-control me-2" type="search" placeholder="Pretraga" aria-label="Pretraga">
                        <button class="btn btn-outline-success" type="button" >Pretraži</button>
                    </form>

                    <form v-if="canLogout" class="d-flex" @submit.prevent="logout">
                        <button class="btn btn-outline-secondary" type="submit">Logout</button>
                    </form>


                </div>
            </div>
        </nav>
    </div>
</template>

<script>
export default {
    // eslint-disable-next-line vue/multi-word-component-names
    name: "Navbar",
    computed: {
        canLogout() {
            return this.$route.name !== 'Login';
        }
    },
    methods: {
        logout() {
            localStorage.removeItem('jwt');
            this.$router.push({name: 'Login'});
        }
    }
}
</script>

<style scoped>

</style>
