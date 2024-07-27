<template>
    <div>
        <NavbarFront/>
        <h2>
            Vest: {{ vest.naslov }}
        </h2>

        <h5>Tekst:</h5>
        <p>
            {{ vest.tekst }} <br><br>
        </p>
    </div>
</template>

<script>

import NavbarFront from "@/components/NavbarFront.vue";

export default {
    // eslint-disable-next-line vue/multi-word-component-names
    name: "Vest",
    components: {NavbarFront},
    props: {
        vest: {
            type: Object,
            required: true,
        }
    },
}
</script>

<style scoped>

</style>
