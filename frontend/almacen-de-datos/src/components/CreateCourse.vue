<template>  
    <div class="form-container">
  <form @submit.prevent="handleSubmit" class="form-grid">
    <div class="column-1">
      <div>
        <label for="course-name">Nombre del curso:</label>
        <input type="text" v-model="course.course_name" id="course-name" maxlength="25" required>
      </div>
      <div>
        <label for="description">Descripción del curso:</label>
        <textarea v-model="course.course_description" rows="6" cols="42" maxlength="255" id="description" required></textarea>
      </div>
    </div>

    <div class="column-2">
      <div v-for="(time_range) in course.time_ranges">
        <h3 class="schedule">Horario</h3>
        <div class="schedule">
          <label for="start-time">Hora de inicio:</label>
          <input type="time" v-model="time_range.start_time" required>
        </div>
        <div class="schedule">
          <label for="end-time">Hora de fin:</label>
          <input type="time" v-model="time_range.end_time" required>
        </div>
        <label id="days-label">Días de la semana:</label>
        <div id="all-days">
          <div v-for="day in daysOfWeek" :key="day" id="weekdays">
            <input type="checkbox" :value="day" v-model="time_range.days">
            <label>{{ day }}</label>
          </div>
        </div>
      </div>
    </div>

    <button type="submit" :disabled="!isFormValid">Crear Curso</button>
  </form>
</div>
</template>

<script>
import axios from 'axios';

export default {
    name: 'CreateCourse',
    data() {
    return {
        course: {
        course_name: '',
        course_description: '',
        authorId: JSON.parse(sessionStorage.getItem('user')).id,
        time_ranges: [ { start_time: '', end_time: '', days: [] }
          ]
        },
        daysOfWeek: ['Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado','Domingo']
    };
    },
    computed: {
    isFormValid() {
     return this.course.time_ranges.some(time_range => time_range.days.length > 0);
     }
   },
    methods: {
      resetForm() {
        this.course = { 
            course_name: '', 
            course_description: '', 
            authorId: JSON.parse(sessionStorage.getItem('user')).id, // Mantener authorId si es necesario
            time_ranges: [{ start_time: '', end_time: '', days: [] }] // Mantener la estructura de horarios
        };   
    },
    async handleSubmit() {
        try {
        console.log(this.course);
        const response = await axios.post('http://localhost:4000/api/course', this.course);
        console.log(response.data);
        console.log(this.course.time_ranges); console.log(this.course.time_ranges[0].days); console.log(this.course.time_ranges[0].days[0].data);
          this.$emit('courseCreated'); // Emitir un evento para informar que se ha creado un usuario
          this.resetForm(); // Limpiar el formulario
        } catch (error) {
        console.error('There was an error creating the course!', error);
        }
      }
    }
};
</script>


