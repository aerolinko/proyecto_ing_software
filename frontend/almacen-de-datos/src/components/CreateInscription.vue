<template>
    <form @submit.prevent="handleSubmit">
    <selectable-course-list
    :courses="courses"
    :selectedCourses="selectedCourses"
    @update:selectedCourses="selectedCourses = $event"/>
    <button type="submit">Crear Insripcion</button>
    </form>
</template>

<script>
import axios from 'axios';
import SelectableCourseList from './SelectableCourseList.vue';
import CourseService from '@/services/CourseService';

export default {
    name: 'App',
    components: {
     SelectableCourseList,
    },
    data() {
    return {
        courses: [],
        inscriptions: [],
        selectedCourses: [],
        inscription: {
                course_id:  '',
                course_name: 'hello',
                userId: JSON.parse(sessionStorage.getItem('user')).id,
              },
    };
    },
    methods: {
    resetForm() {
    this.selectedCourses = [];
    this.inscription.course_id = '';
    this.inscription.course_name = '';
    },
    async handleSubmit() {
        try {
        if (this.selectedCourses.length == 1)
        {
        this.inscription.course_id = this.selectedCourses[0];
        const courseNameHold = await CourseService.getCourseById(this.selectedCourses[0]);
        this.inscription.course_name = courseNameHold.data.course_name;
         } else
         { console.error('Solo se puede elegir un curso');
         return;
         }
        const response = await axios.post('http://localhost:4000/api/inscription', this.inscription);
        console.log(response.data);
          this.$emit('inscriptionCreated'); // Emitir un evento para informar que se ha creado un usuario
          this.resetForm(); // Limpiar el formulario
        } catch (error) {
        console.error('There was an error creating the inscription!', error);
        }
    },
    async fetchCourses() {
                try {
                  const response = await CourseService.getCourses();
                  this.courses = response.data;
                } catch (error) {
                  console.error('There was an error fetching the courses!', error);
                }
              }
    },
  created() {
    this.fetchCourses();
  },
};
</script>
