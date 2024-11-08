<template>
    <form @submit.prevent="handleSubmit">
    <h2> Crear Curso</h2>
    <div>
        <label for="course-name">Nombre del curso:</label>
        <input type="text" v-model="course.course_name" id="course-name" required>
    </div>
    <div>
        <label for="description">Descripcion del curso:</label>
        <input type="text" v-model="course.course_description" id="description" required>
    </div>
    <button type="submit">Crear Curso</button>
    </form>
</template>

<script>
import axios from 'axios';

export default {
    name: 'CreateCourse',
    data() {
    return {
        course: {
        course_name: '',
        course_description: ''
        }
    };
    },
    methods: {
    resetForm() {
        this.course = { course_name: '', course_description: ''};
    },
    async handleSubmit() {
        try {
        const response = await axios.post('http://localhost:4000/api/course', this.course);
        console.log(response.data);
          this.$emit('courseCreated'); // Emitir un evento para informar que se ha creado un usuario
          this.resetForm(); // Limpiar el formulario
        } catch (error) {
        console.error('There was an error creating the user!', error);
        }
    }
    }
};
</script>


