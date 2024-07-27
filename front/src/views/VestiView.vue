<template>
    <div class="vesti">
        <Navbar/>
        <h1 class="mt-4">Vesti</h1>

        <div class="row">
            <div class="col">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">id</th>
                        <th scope="col">naslov</th>
                        <th scope="col">tekst</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="vest in filteredVesti" :key="vest.id" @click="selectedVest = vest">
                        <th scope="row">{{ vest.id }}</th>
                        <td>{{ vest.naslov | capitalize }}</td>
                        <td>{{ vest.tekst | shortText }}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="pagination">
            <button @click="prevPage" :disabled="currentPage === 1">Prethodna</button>
            <button @click="nextPage" :disabled="currentPage === totalPages">Sledeća</button>
        </div>
    </div>
</template>

<script>
import Vest from "../components/Vest.vue";
import Navbar from "@/components/Navbar.vue";

export default {
    // eslint-disable-next-line vue/no-unused-components
    components: {Vest,Navbar},
    props: ['categoryId'], //zbog kategorija
    filters: {
        shortText(value) {
            if (value.length < 30) {
                return value;
            }
            return value.slice(0, 30) + '...'
        }
    },
    data() {
        return {
            selectedVest: null,
            vesti: [],
            currentPage: 1, // Trenutna stranica
            itemsPerPage: 10, // Broj rezultata po stranici
        }
    },
    computed: {
        filteredVesti() {
            const startIndex = (this.currentPage - 1) * this.itemsPerPage;
            const endIndex = startIndex + this.itemsPerPage;
            return this.vesti.slice(startIndex, endIndex);
        },
        totalVesti() {
            return this.vesti.length;
        },
        totalPages() {
            return Math.ceil(this.totalVesti / this.itemsPerPage);
        },
    },
    methods: {
        prevPage() {
            if (this.currentPage > 1) {
                this.currentPage--;
            }
        },
        nextPage() {
            if (this.currentPage < this.totalPages) {
                this.currentPage++;
            }
        },
    },
    created() {
        if (this.$route.params.categoryId) {
            this.$axios.get(`/api/vesti/byKategorija/${this.$route.params.categoryId}`).then((response) => {
                this.vesti = response.data;
            });
        } else {
            this.$axios.get('/api/vesti').then((response) => {
                this.vesti = response.data;
            });
        }
    },

}
</script>
