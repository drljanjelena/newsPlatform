<template>
    <div class="vesti">
        <Navbar/>
        <h1 class="mt-4">Kategorije</h1>
        <div class="row">
            <div class="col">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">id</th>
                        <th scope="col">Ime</th>
                        <th scope="col">Opis</th>
                        <th scope="col">Akcije</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="kategorija in filteredKategorije" :key="kategorija.id">
                        <th scope="row">{{ kategorija.id }}</th>
                        <td>
                            <router-link :to="{ name: 'VestiByCategory', params: { categoryId: kategorija.id } }">
                                {{ kategorija.ime | capitalize }}
                            </router-link>
                        </td>
                        <td>{{ kategorija.opis | shortText }}</td>
                        <td>
                            <button @click="showEditCategoryForm(kategorija)">Edit</button>
                            <button @click="deleteCategory(kategorija.id)">Delete</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <button @click="showAddCategoryForm">Dodaj novu kategoriju</button>

        <div class="pagination">
            <button @click="prevPage" :disabled="currentPage === 1">Prethodna</button>
            <button @click="nextPage" :disabled="currentPage === totalPages">Sledeća</button>
        </div>

        <div v-if="isAddCategoryFormVisible">
            <h2>Dodaj novu kategoriju</h2>
            <form @submit.prevent="addNewCategory">
                <div class="form-group">
                    <label for="newCategoryName">Ime:</label>
                    <input type="text" id="newCategoryName" v-model="newCategory.ime" required>
                </div>
                <div class="form-group">
                    <label for="newCategoryDescription">Opis:</label>
                    <textarea id="newCategoryDescription" v-model="newCategory.opis" required></textarea>
                </div>
                <button type="submit">Dodaj</button>
                <button type="button" @click="cancelAddCategory">Cancel</button>
            </form>
        </div>

        <div v-if="isEditModalVisible">
            <h2>Izmeni kategoriju</h2>
            <form @submit.prevent="editKategoriju">
                <div class="form-group">
                    <label for="editCategoryName">Ime:</label>
                    <input type="text" id="editCategoryName" v-model="editCategory.ime" required>
                </div>
                <div class="form-group">
                    <label for="editCategoryDescription">Opis:</label>
                    <textarea id="editCategoryDescription" v-model="editCategory.opis" required></textarea>
                </div>
                <button type="submit">Izmeni</button>
                <button type="button" @click="cancelEditCategory">Cancel</button>
            </form>
        </div>



    </div>
</template>

<script>
import Kategorija from "../components/Kategorija.vue";
import Navbar from "@/components/Navbar.vue";

export default {
    // eslint-disable-next-line vue/no-unused-components
    components: {Kategorija,Navbar},
    filters: {
        shortText(value) {
            if (value.length < 100) {
                return value;
            }
            return value.slice(0, 100) + '...'
        }
    },
    data() {
        return {
            selectedKategorija: null,
            kategorije: [],
            currentPage: 1,
            itemsPerPage: 10,
            isAddCategoryFormVisible: false,
            isEditModalVisible: false,
            newCategory: {
                ime: '',
                opis: '',
            },
            editCategory: {
                ime: '',
                opis: ''
            }
        }
    },
    computed: {
        filteredKategorije() {
            const startIndex = (this.currentPage - 1) * this.itemsPerPage;
            const endIndex = startIndex + this.itemsPerPage;
            return this.kategorije.slice(startIndex, endIndex);
        },
        totalKategorije() {
            return this.kategorije.length;
        },
        totalPages() {
            return Math.ceil(this.totalKategorije / this.itemsPerPage);
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
        showAddCategoryForm() {
            this.isAddCategoryFormVisible = true;
        },
        showEditCategoryForm(kategorija) {
            this.selectedKategorija = kategorija;
            this.editCategory.ime = kategorija.ime;
            this.editCategory.opis = kategorija.opis;
            this.isEditModalVisible = true;
        },
        addNewCategory() {
            this.$axios.post('/api/kategorije', this.newCategory)
                .then((response) => {
                    this.kategorije.push(response.data);
                    this.newCategory = { ime: '', opis: '' };
                    this.isAddCategoryFormVisible = false;
                })
                .catch((error) => {
                    console.error('Greška prilikom dodavanja kategorije:', error);
                });
        },
        editKategoriju() {
            const updatedCategory = {
                ime: this.editCategory.ime,
                opis: this.editCategory.opis
            };

            this.$axios.put(`/api/kategorije/${this.selectedKategorija.id}`, this.editCategory)
                .then(response => {
                    console.log(response);
                    const index = this.kategorije.findIndex(
                        kategorija => kategorija.id === this.selectedKategorija.id
                    );
                    if (index !== -1) {
                        this.kategorije[index] = updatedCategory;
                    }
                    this.editCategory = { ime: '', opis: '' };
                    this.isEditModalVisible = false;
                })
                .catch(error => {
                    console.error('Greška prilikom editovanja kategorije:', error);
                });
        },
        cancelAddCategory() {
            this.isAddCategoryFormVisible = false;
        },
        cancelEditCategory() {
            this.isEditModalVisible = false;
        },
        deleteCategory(categoryId) {
                this.$axios.get(`/api/vesti/byKategorija/${categoryId}`)
                    .then((response) => {
                        const vestiZaKategoriju = response.data;
                        if (vestiZaKategoriju.length === 0) {
                            this.$axios.delete(`/api/kategorije/${categoryId}`)
                                .then(() => {
                                    const index = this.kategorije.findIndex(kategorija => kategorija.id === categoryId);
                                    if (index !== -1) {
                                        this.kategorije.splice(index, 1); // Uklonite kategoriju iz tabele
                                    }
                                })
                                .catch((error) => {
                                    console.error('Greška prilikom brisanja kategorije sa servera:', error);
                                });
                        } else {
                            alert('Kategorija ne može biti obrisana jer sadrži vesti.');
                        }
                    })
                    .catch((error) => {
                        console.error('Greška prilikom dobijanja liste vesti za kategoriju sa servera:', error);
                    });
        },
    },
    created() {
        this.$axios.get('/api/kategorije').then((response) => {
            this.kategorije = response.data;
        });
    },

}
</script>

<style scoped>

button {
    margin: 10px;
    padding: 5px 10px;
}
 .add-category-form {
     background-color: #f4f4f4;
     border: 1px solid #ccc;
     padding: 20px;
     margin-top: 20px;
 }

.add-category-form h2 {
    font-size: 1.2rem;
    margin-bottom: 10px;
}

.form-group {
    margin-bottom: 15px;
}

.form-group label {
    font-weight: bold;
    display: block;
}

.form-group input[type="text"],
.form-group textarea {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

button[type="submit"] {
    background-color: #007bff;
    color: #fff;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-weight: bold;
}

button[type="submit"]:hover {
    background-color: #0056b3;
}

</style>