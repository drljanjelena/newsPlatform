<template>
    <div class="pt-5">
        <Navbar/>
        <form @submit.prevent="login">
            <div class="form-group">
                <label for="email">Email</label>
                <input v-model="email" type="text" class="form-control" id="email" aria-describedby="email" placeholder="Enter email">
                <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Password</label>
                <input v-model="password" type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
            </div>
            <button type="submit" class="btn btn-primary mt-2">Submit</button>
        </form>
    </div>
</template>



<script>
import Navbar from "@/components/Navbar.vue";

export default {
    // eslint-disable-next-line vue/multi-word-component-names
    name: "Login",
    components:{Navbar},
    data() {
        return {
            email: '',
            password: '',
        }
    },
    methods: {
        login() {
            this.$axios.post('/api/korisnici/login', {
                email: this.email,
                password: this.password,
            }).then(response => {
                localStorage.setItem('jwt', response.data.jwt)
                this.$router.push({name: 'Kategorije'});
            })
        }
    },
}
</script>

<style scoped>

</style>
