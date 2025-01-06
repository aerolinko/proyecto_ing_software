<template>
    <div>
    <ul>
        <li v-for="inscription in inscriptions" :key="inscription.inscription_id">
        Estás inscrito en el curso: {{ inscription.course_name }}
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


