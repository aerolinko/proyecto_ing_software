<template>
  <div class="form-container">
    <form @submit.prevent="handleSubmit" class="form-grid">
      <div>
        <label for="select-inscription">Seleccionar Incripción:</label>
        <select v-model="selectedInscriptionId" id="select-inscription" @change="loadInscription">
          <option v-for="inscription4selection in inscriptions" :key="inscription4selection.inscription_id" :value="inscription4selection.inscription_id">
            {{ inscription4selection.course_name }}
          </option>
        </select>
      </div>

        <div v-if="inscription4selection">
          <selectable-course-list
              :courses="courses"
              :selectedCourses="selectedCourses"
              @update:selectedCourses="selectedCourses = $event"/>
        <button type="submit" :disabled="!isFormValid" >Guardar Cambios</button>
      </div>

    </form>
  </div>
</template>

<script>
import axios from 'axios';
import CreateInscription from "@/components/CreateInscription.vue";
import SelectableCourseList from "@/components/SelectableCourseList.vue";
import CourseService from "@/services/CourseService.js";
import InscriptionService from "@/services/InscriptionService.js";
import {toRaw} from "@vue/runtime-core";

export default {
  name: 'EditCourse',
  components: {SelectableCourseList, CreateInscription},
  props: {
    inscriptions: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      selectedInscriptionId: null,
      inscription4selection: null,
      courses: [],
      selectedCourses: [],
      allinscriptions4Selection: [],
      inscription: {
        course_id:  '',
        course_name: 'hello',
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
    reset(){
      this.selectedInscriptionId=null;
      this.inscription4selection = null;
      this.selectedCourses = [];
    },
    loadInscription() {
      this.inscription4selection = this.inscriptions.find(inscription => inscription.inscription_id === this.selectedInscriptionId);
    },
    async handleSubmit() {
      try {
        if (this.selectedCourses.length === 1)
        {
          this.inscription4selection.course_id = this.selectedCourses[0];
          const courseNameHold = await CourseService.getCourseById(this.selectedCourses[0]);
          this.inscription4selection.course_name = courseNameHold.data.course_name;
        } else
        {
          console.error('Solo se puede elegir un curso');
          return;
        }
        const response = await axios.put(`http://localhost:4000/api/inscription/${this.inscription4selection.inscription_id}`, this.inscription4selection);
        console.log(response.data);
        this.$emit('inscriptionEdited'); // Emitir un evento para informar que se ha creado un usuario
        alert('Inscripción actualizada correctamente');
      } catch (error) {
        console.error('There was an error creating the inscription!', error);
      }
      await this.fetchCourses();
    },
    async fetchCourses() {
      try {
        const response = await CourseService.getCoursesByNotAuthorId(this.inscription.userId);
        this.courses = response.data;
        this.allinscriptions4Selection = await this.fetchInscriptions();
        this.courses = await this.compareCurrentInscriptons();
        this.reset();
        console.log('Cursos inscribibles: ',this.courses);
      } catch (error) {
        console.error('There was an error fetching the courses!', error);
      }
    },
    async fetchInscriptions() {
      try {
        const response = await InscriptionService.getInscriptionByUserId(JSON.parse(sessionStorage.getItem('user')).id);
        return response.data;
      } catch (error) {
        console.error('There was an error fetching the inscriptions!', error);
      }
    },
    async compareCurrentInscriptons(){
      let courseNoRepeat = toRaw(this.courses);
      for (let i = 0; i < this.allinscriptions4Selection.length ; i++) {
        courseNoRepeat = courseNoRepeat.filter(index => index.course_id !== this.allinscriptions4Selection[i].course_id);
      }
      return courseNoRepeat;
    }



  },
  created() {
    this.fetchCourses();
  }
};
</script>