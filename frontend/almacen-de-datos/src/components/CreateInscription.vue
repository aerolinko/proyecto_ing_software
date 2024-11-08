<template>
    <form @submit.prevent="handleSubmit">
    <h2> Crear Inscripcion</h2>
    <div>
        <label for="course-id">id del curso:</label>
        <input type="text" v-model="inscription.course_id" id="course-id" required>
    </div>
    <div>
        <label for="user-id">id del usuario:</label>
        <input type="text" v-model="inscription.user_id" id="user-id" required>
    </div>
    <button type="submit">Crear Insripcion</button>
    </form>
</template>

<script>
import axios from 'axios';

export default {
    name: 'CreateInscription',
    data() {
    return {
        inscription: {
        course_id: '',
        user_id: ''
        }
    };
    },
    methods: {
    resetForm() {
        this.inscription = { course_id: '', user_id: ''};
    },
    async handleSubmit() {
        try {
        const response = await axios.post('http://localhost:4000/api/inscription', this.inscription);
        console.log(response.data);
          this.$emit('inscriptionCreated'); // Emitir un evento para informar que se ha creado un usuario
          this.resetForm(); // Limpiar el formulario
        } catch (error) {
        console.error('There was an error creating the user!', error);
        }
    }
    }
};
</script>
