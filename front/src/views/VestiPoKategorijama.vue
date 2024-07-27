<template>
    <div>
        <NavbarFront/>
       <!-- <h1>{{ kategorija.ime }}</h1> -->
        <ul>
            <li v-for="vest in vesti" :key="vest.id">
                <router-link :to="{ name: 'SingleVest', params: { id: vest.id } }">
                    <h2>{{ vest.naslov }}</h2>
                </router-link>
                <p>{{ vest.tekst | limitirajTekst }}</p>
                <p>{{ vest.datumKreiranja | formatirajDatum }}</p>
            </li>
        </ul>
    </div>
</template>

<script>
import NavbarFront from "@/components/NavbarFront.vue";

export default {
    components :{NavbarFront},
    props: ['categoryId'],
    data(){
        return{
            vesti : []
        }
    },
    filters: {
        limitirajTekst(value) {
            if (value.length < 150) {
                return value;
            }
            return value.slice(0, 150) + '...'
        },
        formatirajDatum(timestamp) {
            const date = new Date(timestamp);
            const year = date.getFullYear();
            const month = (date.getMonth() + 1).toString().padStart(2, '0');
            const day = date.getDate().toString().padStart(2, '0');
            return `${year}-${month}-${day}`;
        },
    },
    watch: {
        '$route.params.categoryId': {
            immediate: true,
            // eslint-disable-next-line no-unused-vars
            handler(newCategoryId, oldCategoryId) {
                this.loadData(newCategoryId);
            },
        },
    },
    methods: {
        loadData(categoryId) {
            this.$axios.get(`/api/vesti/byKategorija/${categoryId}`).then((response) => {
                this.vesti = response.data;
            });
        },
    },
    created() {
        this.loadData(this.$route.params.categoryId);
    },
};
</script>
