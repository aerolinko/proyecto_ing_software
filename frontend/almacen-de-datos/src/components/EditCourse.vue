<template>
    <div class="form-container">
        <form @submit.prevent="handleSubmit" class="form-grid">
            <div>
                <label for="select-course">Seleccionar curso:</label>
                <select v-model="selectedCourseId" id="select-course" @change="loadCourse">
                    <option v-for="course in courses" :key="course.course_id" :value="course.course_id">
                        {{ course.course_name }}
                    </option>
                </select>
            </div>

            <div v-if="course">
                <div>
                    <label for="course-name">Nombre del curso:</label>
                    <input type="text" v-model="course.course_name" id="º-name" maxlength="25" required>
                </div>
                <div>
                    <label for="description">Descripción del curso:</label>
                    <textarea v-model="course.course_description" rows="6" cols="42" maxlength="255" id="description"
                        required></textarea>
                </div>

                <div v-for="(time_range, index) in course.time_ranges" :key="index">
                    <h3 class="schedule">Horario</h3>
                    <div class="schedule">
                        <label for="start-time">Hora de inicio:</label>
                        <input type="time" v-model="time_range.start_time" required>
                    </div>
                    <div class="schedule">
                        <label for="end-time">Hora de fin:</label>
                        <input type="time" v-model="time_range.end_time" required>
                    </div>
                    <label>Días de la semana:</label>
                    <div>
                        <div v-for="day in daysOfWeek" :key="day">
                            <input type="checkbox" :value="day" v-model="time_range.days">
                            <label>{{ day }}</label>
                        </div>
                    </div>
                </div>

                <button type="submit">Guardar Cambios</button>
            </div>
        </form>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    name: 'EditCourse',
    props: {
        courses: {
            type: Array,
            required: true,
        },
    },
    data() {
        return {
            selectedCourseId: null,
            course: null,
            daysOfWeek: ['Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado', 'Domingo'],
        };
    },
    methods: {
      reset(){
        this.selectedCourseId=null;
        this.course = null;
      },
        loadCourse() {
            this.course = this.courses.find(course => course.course_id === this.selectedCourseId);
        },
        async handleSubmit() {
            try {
                await axios.put(`http://localhost:4000/api/course/${this.course.course_id}`, this.course);
                this.reset();
                this.$emit('courseUpdated'); // Emitir un evento para actualizar la lista
                alert('Curso actualizado correctamente');
            } catch (error) {
                console.error('Error actualizando el curso:', error);
            }
        },
    },
};
</script>