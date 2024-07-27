<template>
    <div>
        <vest :vest="vest"></vest>
    </div>
</template>

<script>
import Vest from "../components/Vest";

export default {
    name: "SingleVest",
    components: {Vest},
    data() {
        return {
            vest: {},
        }
    },
    created() {
        this.$axios.get(`/api/vesti/${this.$route.params.id}`).then((response) => {
            this.vest = response.data;
        });
    }
}
</script>

<style scoped>

</style>
