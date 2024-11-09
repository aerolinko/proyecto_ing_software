<template>
  <div v-if="user">
    <h1>Bienvenido {{ user.firstName }}!</h1>
    <h2 style="text-align:left">Este es tu propio perfil personalizado</h2>
    <h3>Mira todas las opciones que tienes tu disposición</h3>

    <div id="app">
      <create-course @courseCreated="fetchCourses"></create-course>
      <course-list :courses="courses"></course-list>
      <create-inscription @inscriptionCreated="fetchInscriptions"></create-inscription>
      <inscription-list :inscriptions="inscriptions"></inscription-list>
      <create-product @productCreated="fetchProducts"></create-product>
      <product-list :products="products"></product-list>
    </div>
  </div>
</template>



<script>

import CreateCourse from './CreateCourse.vue';
import CreateInscription from './CreateInscription.vue';
import CourseList from './CourseList.vue';
import ProductList from './ProductList.vue';
import InscriptionList from './InscriptionList.vue';
import CreateProduct from './CreateProduct.vue';
import axios from 'axios';
import CourseService from '@/services/CourseService';
import InscriptionService from '@/services/InscriptionService';
import ProductService from '@/services/ProductService';

export default {
    name: 'App',
    components: {
     CreateCourse,
     CreateInscription,
     CourseList,
     ProductList,
     InscriptionList,
     CreateProduct,
    },
  data() {
    return {
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
              const response = await InscriptionService.getInscriptionByUserId(JSON.parse(sessionStorage.getItem('user')).id    );
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
              }
    },
    created() {
      this.fetchCourses();
      this.fetchInscriptions();
      this.fetchProducts();

    }
};
</script>
