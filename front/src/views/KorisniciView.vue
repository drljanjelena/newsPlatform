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
                        <th scope="col">Ime</th>
                        <th scope="col">Prezime</th>
                        <th scope="col">Email</th>
                        <th scope="col">Tip korisnika</th>
                        <th scope="col">Akcije</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="korisnik in filteredKorisnici" :key="korisnik.id" @click="selectedVest = korisnik">
                        <th scope="row">{{ korisnik.id }}</th>
                        <td>{{ korisnik.ime  }}</td>
                        <td>{{ korisnik.prezime  }}</td>
                        <td>{{ korisnik.email  }}</td>
                        <td>{{ korisnik.tipKorisnika  }}</td>
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
import Navbar from "@/components/Navbar";

export default {
    // eslint-disable-next-line vue/no-unused-components
    components: {Navbar},

    data() {
        return {
            selectedVest: null,
            korisnici: [],
            currentPage: 1,
            itemsPerPage: 10,
        }
    },

    computed: {
        filteredKorisnici() {
            const startIndex = (this.currentPage - 1) * this.itemsPerPage;
            const endIndex = startIndex + this.itemsPerPage;
            return this.korisnici.slice(startIndex, endIndex);
        },
        totalVesti() {
            return this.korisnici.length;
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
            this.$axios.get('/api/korisnici').then((response) => {
                this.korisnici = response.data;
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