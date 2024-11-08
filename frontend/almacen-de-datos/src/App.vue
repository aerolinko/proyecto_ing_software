<template>
  <div id="app">
    <create-user @userCreated="fetchUsers"></create-user>
    <user-list :users="users"></user-list>
    <create-course @courseCreated="fetchCourses"></create-course>
    <course-list :courses="courses"></course-list>
    <create-inscription @inscriptionCreated="fetchInscriptions"></create-inscription>
    <inscription-list :inscriptions="inscriptions"></inscription-list>
    <create-product @productCreated="fetchProducts"></create-product>
    <product-list :products="products"></product-list>
  </div>
</template>

<script>
import axios from 'axios';
import UserList from './components/UserList.vue';
import CreateUser from './components/CreateUser.vue';
import CreateCourse from './components/CreateCourse.vue';
import CreateInscription from './components/CreateInscription.vue';
import CourseList from './components/CourseList.vue';
import ProductList from './components/ProductList.vue';
import InscriptionList from './components/InscriptionList.vue';
import CreateProduct from './components/CreateProduct.vue';


export default {
  name: 'App',
  components: {
    CreateUser,
    UserList,
    CreateCourse,
    CreateInscription,
    CourseList,
    InscriptionList,
    CreateProduct,
    ProductList,
  },
  data() {
    return {
      users: [],
      courses: [],
      inscriptions: [],
      products:[],
    };
  },
  methods: {
    async fetchUsers() {
      try {
        const response = await axios.get('http://localhost:4000/api/user');
        this.users = response.data;
      } catch (error) {
        console.error('There was an error fetching the users!', error);
      }
    },
    async fetchCourses() {
          try {
            const response = await axios.get('http://localhost:4000/api/course');
            this.courses = response.data;
          } catch (error) {
            console.error('There was an error fetching the courses!', error);
          }
        },
    async fetchInscriptions() {
          try {
            const response = await axios.get('http://localhost:4000/api/inscription');
            this.inscriptions = response.data;
          } catch (error) {
            console.error('There was an error fetching the inscriptions!', error);
          }
        },
    async fetchProducts() {
              try {
                const response = await axios.get('http://localhost:4000/api/product');
                this.products = response.data;
              } catch (error) {
                console.error('There was an error fetching the products!', error);
              }
            }
  },
  created() {
    this.fetchUsers();
    this.fetchCourses();
    this.fetchInscriptions();
    this.fetchProducts();

  },
};
</script>
