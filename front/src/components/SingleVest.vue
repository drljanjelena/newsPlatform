<template>
    <div>
        <NavbarFront/>
        <h1>{{ vest.naslov }}</h1>
        <p>{{ vest.datumKreiranja | formatirajDatum }}</p>
        <p>{{ vest.tekst }}</p>
        <router-link :to="{ name: 'VestiPoKategoriji', params: { kategorijaId: vest.kategorijaId } }">
            Nazad na vesti u kategoriji
        </router-link>
    </div>
</template>

<script>
import NavbarFront from "@/components/NavbarFront.vue";

export default {
    components: {NavbarFront},
    data() {
        return {
            vest: null,
        };
    },
    filters: {
        formatirajDatum(timestamp) {
            const date = new Date(timestamp);
            const year = date.getFullYear();
            const month = (date.getMonth() + 1).toString().padStart(2, '0');
            const day = date.getDate().toString().padStart(2, '0');
            return `${year}-${month}-${day}`;
        },
    },
    created() {
        const vestId = this.$route.params.id;
        this.$axios.get(`/api/vesti/${vestId}`).then((response) => {
            this.vest = response.data;
        });
    },
};
</script>

<style scoped>

</style>
