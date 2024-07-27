<template>
    <div>
        <h2>
            IME: {{ kategorija.ime }}
        </h2>

        <h5>Opis:</h5>
        <p>
            {{ kategorija.opis }} <br><br>
        </p>
    </div>
</template>

<script>

export default {
    // eslint-disable-next-line vue/multi-word-component-names
    name: "Kategorija",
    props: {
        kategorija: {
            type: Object,
            required: true,
        }
    },
}
</script>

<style scoped>

</style>
