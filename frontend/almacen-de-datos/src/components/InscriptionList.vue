<template>
    <div>
    <h2>Tus Inscripciones</h2>
    <ul>
        <li v-for="inscription in inscriptions" :key="inscription.inscription_id">
        id -> {{inscription.inscription_id}} - {{ inscription.course_id }} - {{ inscription.userId }} - {{ inscription.course_name }}
        </li>
    </ul>
    </div>
</template>

<script>
export default {
    name: 'InscriptionList',
    props: {
    inscriptions: {
        type: Array,
        required: true
    }
    }
};
</script>


