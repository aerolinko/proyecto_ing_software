<template>
    <form @submit.prevent="handleSubmit">
    <selectable-course-list
    :courses="courses"
    :selectedCourses="selectedCourses"
    @update:selectedCourses="selectedCourses = $event"/>
    <button type="submit" :disabled="!isFormValid ">Crear Insripcion</button>
    </form>
</template>

<script>
import axios from 'axios';
import SelectableCourseList from './SelectableCourseList.vue';
import CourseService from '@/services/CourseService';
import InscriptionService from "@/services/InscriptionService.js";
import {toRaw} from "@vue/runtime-core";

export default {
    name: 'CreateInscription',
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
                course_name: '',
                userId: JSON.parse(sessionStorage.getItem('user')).id,
              },
    };
    },
    computed: {
      isFormValid() {
        return this.selectedCourses.length === 1;
      }
    },
    methods: {
    resetForm() {
    this.selectedCourses = [];
    this.inscription.course_id = '';
    this.inscription.course_name = '';
    },
    async handleSubmit() {
        try {
        if (this.selectedCourses.length === 1)
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
        await this.fetchCourses();
    },
    async fetchCourses() {
                try {
                  const response = await CourseService.getCoursesByNotAuthorId(this.inscription.userId);
                  this.courses = response.data;
                  this.inscriptions = await this.fetchInscriptions();
                  this.courses = await this.compareCurrentInscriptons();
                  this.resetForm();
                  console.log('Cursos incribibles: ',this.courses);
                } catch (error) {
                  console.error('There was an error fetching the courses!', error);
                }
              },
      async fetchInscriptions() {
        try {
          const response = await InscriptionService.getInscriptionByUserId(JSON.parse(sessionStorage.getItem('user')).id);
          return response.data;
        } catch (error) {
          console.error('There was an error fetching the courses!', error);
        }
      },
      async compareCurrentInscriptons(){
      let courseNoRepeat = toRaw(this.courses);
        for (let i = 0; i < this.inscriptions.length ; i++) {
          courseNoRepeat = courseNoRepeat.filter(index => index.course_id !== this.inscriptions[i].course_id);
        }
        return courseNoRepeat;
    }
    },
  created() {
    this.fetchCourses();
  },
};
</script>
