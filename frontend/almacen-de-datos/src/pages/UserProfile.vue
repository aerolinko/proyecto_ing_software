<template>
  <div v-if="user">
    <h1 id="titulo"> BIENVENIDO {{ user.firstName.toUpperCase() }}!</h1>
    <div id="app">
      <b-card no-body>
        <b-tabs lazy pills card>
          <b-tab title="Perfil">
            <profile :user="user" />
          </b-tab>
          <b-tab title="Crear Curso">
            <create-course @courseCreated="fetchCourses"></create-course>
          </b-tab>
          <b-tab title="Cursos">
            <course-list :courses="courses"></course-list>
                <b-tabs pills card>
                        <b-tab title="Editar Curso">
                          <edit-course :courses="courses" @courseUpdated="fetchCourses"></edit-course>
                        </b-tab>
                        <b-tab title="Eliminar Curso">
                          <delete-course :courses="courses" @courseDeleted="fetchCourses"></delete-course>
                        </b-tab>
                </b-tabs>
          </b-tab>

          <b-tab title="Crear Inscripción">
            <create-inscription @inscriptionCreated="fetchInscriptions"></create-inscription>
          </b-tab>
          <b-tab title="Inscripciones">
            <inscription-list :inscriptions="inscriptions"></inscription-list>
          </b-tab>
          <b-tab title="Crear Productos">
            <create-product @productCreated="fetchProducts"></create-product>
          </b-tab>
          <b-tab title="Productos">
            <product-list :products="products"></product-list>
            <b-tabs pills card>
              <b-tab title="Editar Producto">
                <edit-product :products="products" @productUpdated="fetchProducts"></edit-product>
              </b-tab>
              <b-tab title="Eliminar Producto">
                <delete-product :products="products" @productDeleted="fetchProducts"></delete-product>
              </b-tab>
            </b-tabs>
          </b-tab>
          <b-tab title="Administrar Usuarios">
                <b-tabs pills card>
                  <b-tab title="Editar Usuario">
                    <edit-user :users="users" @userUpdated="fetchUsers"></edit-user>
                  </b-tab>
                  <b-tab title="Eliminar Usuario">
                    <delete-user :users="users" @userDeleted="fetchUsers"></delete-user>
                  </b-tab>
                </b-tabs>
          </b-tab>
        </b-tabs>
      </b-card>
    </div>
  </div>
</template>

<script>
import Profile from '@/components/Profile.vue';
import CreateCourse from '@/components/CreateCourse.vue';
import EditCourse from '@/components/EditCourse.vue'; // Importar el componente de Editar Curso
import DeleteCourse from '@/components/DeleteCourse.vue'; // Importar el componente de Eliminar Curso
import CreateInscription from '@/components/CreateInscription.vue';
import CourseList from '@/components/CourseList.vue';
import ProductList from '@/components/ProductList.vue';
import InscriptionList from '@/components/InscriptionList.vue';
import CreateProduct from '@/components/CreateProduct.vue';
import axios from 'axios';
import CourseService from '@/services/CourseService';
import InscriptionService from '@/services/InscriptionService';
import ProductService from '@/services/ProductService';
import {BCard, BTabs, BTab} from 'bootstrap-vue-3';
import EditProduct from "@/components/EditProduct.vue";
import DeleteProduct from "@/components/DeleteProduct.vue";
import UserService from "@/services/UserService.js";
import DeleteUser from "@/components/DeleteUser.vue";
import EditUser from "@/components/EditUser.vue";

export default {
  name: 'App',
  components: {
    EditUser,
    DeleteUser,
    DeleteProduct,
    EditProduct,
    BCard,
    BTabs,
    BTab,
    Profile,
    CreateCourse,
    EditCourse,
    DeleteCourse,
    CreateInscription,
    CourseList,
    ProductList,
    InscriptionList,
    CreateProduct,
  },
  data() {
    return {
      activeTab: 0,
      user: JSON.parse(sessionStorage.getItem('user')),
      courses: [],
      inscriptions: [],
      products: [],
      users:[],
    };
  },
  mounted() {
    this.user = JSON.parse(sessionStorage.getItem('user'));
  },
  methods: {
    async fetchUsers() {
      try {
        const response = await UserService.getUsers();
        this.users = response.data;
        this.errorMessage = ''; // Limpiar el mensaje de error si la búsqueda fue exitosa
      } catch (error) {
        this.user = null; // Limpiar el usuario si hay un error
        this.errorMessage = 'Usuarios no encontrado';
        console.error('Error fetching users:', error);
      }
    },
    async fetchCourses() {
      try {
        const response = await CourseService.getCourseByAuthorId(
            JSON.parse(sessionStorage.getItem('user')).id
        );
        this.courses = response.data;
      } catch (error) {
        console.error('There was an error fetching the courses!', error);
      }
    },
    async fetchInscriptions() {
      try {
        const response = await InscriptionService.getInscriptionByUserId(
            JSON.parse(sessionStorage.getItem('user')).id
        );
        this.inscriptions = response.data;
      } catch (error) {
        console.error('There was an error fetching the inscriptions!', error);
      }
    },
    async fetchProducts() {
      try {
        const response = await ProductService.getProductByAuthorId(
            JSON.parse(sessionStorage.getItem('user')).id
        );
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
    this.fetchUsers();
  },
};
</script>