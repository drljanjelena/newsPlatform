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
                        <th scope="col">Naslov</th>
                        <th scope="col">Autor</th>
                        <th scope="col">Datum</th>
                        <th scope="col">Akcije</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="vest in filteredVesti" :key="vest.id" @click="selectedVest = vest">
                        <th scope="row">{{ vest.id }}</th>
                        <td>
                            <router-link :to="{name: 'HomePage'}" tag="a" target="_blank">{{ vest.naslov }}</router-link>
                        </td>
                        <td>{{ vest.autor  }}</td>
                        <td>{{ vest.datumKreiranja | formatDate  }}</td>
                        <td>
                            <button >Edit</button>
                            <button>Delete</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <button>Dodaj novu vest</button>

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
    data() {
        return {
            selectedVest: null,
            vesti: [],
            currentPage: 1,
            itemsPerPage: 10,
        }
    },

    filters: {
        formatDate(timestamp) {
            const date = new Date(timestamp);
            const year = date.getFullYear();
            const month = (date.getMonth() + 1).toString().padStart(2, '0');
            const day = date.getDate().toString().padStart(2, '0');
            return `${year}-${month}-${day}`;
        }
    },
    computed: {
        sortedVesti() {
            return this.vesti.slice().sort((a, b) => {
                return b.datumKreiranja - a.datumKreiranja;
            });
        },
        filteredVesti() {
            const startIndex = (this.currentPage - 1) * this.itemsPerPage;
            const endIndex = startIndex + this.itemsPerPage;
            return this.sortedVesti.slice(startIndex, endIndex);
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
            this.$axios.get('/api/vesti').then((response) => {
                this.vesti = response.data;
            });
    },

}
</script>

<style scoped>
button {
    margin: 10px;
    padding: 5px 10px;
}
</style>