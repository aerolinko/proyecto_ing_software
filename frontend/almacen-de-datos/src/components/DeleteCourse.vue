<template>
    <div class="delete-container">
        <h3>Eliminar Curso</h3>
        <select v-model="selectedCourseId">
            <option v-for="course in courses" :key="course.course_id" :value="course.course_id">
                {{ course.course_name }}
            </option>
        </select>
        <button @click="deleteCourse" :disabled="!selectedCourseId">Eliminar Curso</button>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    name: 'DeleteCourse',
    props: {
        courses: {
            type: Array,
            required: true,
        },
    },
    data() {
        return {
            selectedCourseId: null,
        };
    },
    methods: {
        async deleteCourse() {
            try {
                if (confirm('¿Estás seguro de que deseas eliminar este curso?')) {
                    await axios.delete(`http://localhost:4000/api/course/${this.selectedCourseId}`);
                    this.$emit('courseDeleted'); // Emitir un evento para actualizar la lista
                    alert('Curso eliminado correctamente');
                }
            } catch (error) {
                console.error('Error eliminando el curso:', error);
            }
        },
    },
};
</script>