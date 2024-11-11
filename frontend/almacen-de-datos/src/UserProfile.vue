<template>
  <div v-if="user">
    <h1 id="titulo"> BIENVENIDO {{ user.firstName.toUpperCase() }}!</h1>
    <div id="app">
    <b-card no-body>
      <b-tabs pills card>
      <b-tab title="Crear Curso"><create-course @courseCreated="fetchCourses"></create-course></b-tab>
      <b-tab title="Cursos"><course-list :courses="courses"></course-list></b-tab>
      <b-tab title="Crear Inscripción"><create-inscription @inscriptionCreated="fetchInscriptions"></create-inscription></b-tab>
      <b-tab title="Inscripciones"><inscription-list :inscriptions="inscriptions"></inscription-list></b-tab>
      <b-tab title="Crear Productos"><create-product @productCreated="fetchProducts"></create-product></b-tab>
      <b-tab title="Productos"><product-list :products="products"></product-list></b-tab>
      </b-tabs>
     </b-card>
    </div>
  </div>
</template>



<script>

import CreateCourse from './components/CreateCourse.vue';
import CreateInscription from './components/CreateInscription.vue';
import CourseList from './components/CourseList.vue';
import ProductList from './components/ProductList.vue';
import InscriptionList from './components/InscriptionList.vue';
import CreateProduct from './components/CreateProduct.vue';
import axios from 'axios';
import CourseService from '@/services/CourseService';
import InscriptionService from '@/services/InscriptionService';
import ProductService from '@/services/ProductService';
import { BCard, BTabs, BTab } from 'bootstrap-vue-3';

export default {
    name: 'App',
    components: {
    BCard, BTabs, BTab,
     CreateCourse,
     CreateInscription,
     CourseList,
     ProductList,
     InscriptionList,
     CreateProduct,
    },
  data() {
    return {
      activeTab: 0,
      user: null,
      courses: [],
      inscriptions: [],
      products: []
    };
  },
  mounted() {
  this.user = JSON.parse(sessionStorage.getItem('user'));
  },
  methods: {
      async fetchCourses() {
            try {
              const response = await CourseService.getCourseByAuthorId(JSON.parse(sessionStorage.getItem('user')).id);
              this.courses = response.data;
            } catch (error) {
              console.error('There was an error fetching the courses!', error);
            }
          },
      async fetchInscriptions() {
            try {
              const response = await InscriptionService.getInscriptionByUserId(JSON.parse(sessionStorage.getItem('user')).id);
              this.inscriptions = response.data;
            } catch (error) {
              console.error('There was an error fetching the inscriptions!', error);
            }
          },
      async fetchProducts() {
                try {
                  const response = await ProductService.getProductByAuthorId(JSON.parse(sessionStorage.getItem('user')).id);
                  this.products = response.data;
                } catch (error) {
                  console.error('There was an error fetching the products!', error);
                }
              },
    },
    created() {
      this.fetchCourses();
      this.fetchInscriptions();
      this.fetchProducts();

    }
};
</script>
