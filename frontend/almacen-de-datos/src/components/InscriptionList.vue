<template>
    <div id="inscripciones">
        <li id="clasas2" v-for="inscription in inscriptions" :key="inscription.inscription_id">
        Estás inscrito en el curso: {{ inscription.course_name }}
        </li>
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


