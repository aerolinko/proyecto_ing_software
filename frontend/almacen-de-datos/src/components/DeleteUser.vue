<template>
  <div class="delete-container">
    <h3>Eliminar Usuario</h3>
    <select v-model="selectedUserId" :id="'optioner'">
      <option v-for="user in users" :key="user.id" :value="user.id" >
        {{ user.username }}
      </option>
    </select>
    <button @click="deleteUser" :disabled="!selectedUserId">Eliminar Usuario</button>
  </div>
</template>

<script>
import axios from 'axios';
import UserService from "@/services/UserService.js";
import productService from "@/services/ProductService.js";
import inscriptionService from "@/services/InscriptionService.js";
import courseService from "@/services/CourseService.js";

export default {
  name: 'DeleteUser',
  props: {
    users: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      selectedUserId: null,
    };
  },
  methods: {
    reset() {
      document.getElementById('optioner').selectedIndex = -1;
    },
    async deleteUser() {
      try {
        if (confirm('¿Estás seguro de que deseas eliminar este usuario?')) {
          await this.deleteUserCourses(this.selectedUserId);
          await this.deleteUserProducts(this.selectedUserId);
          await this.deleteUserInscriptions(this.selectedUserId);
          await axios.delete(`http://localhost:4000/api/user/${this.selectedUserId}`);
          this.$emit('userDeleted'); // Emitir un evento para actualizar la lista
          alert('Usuario eliminado correctamente');
          this.reset();
        }
      } catch (error) {
        console.error('Error eliminando el usuario:', error);
      }
    },
    async deleteUserProducts() {
      try {
        await productService.deleteAllProducts(this.selectedUserId);
      } catch (error) {
        console.error('Error al eliminar los productos del usuario:', error);
      }
    },
    async deleteUserInscriptions() {
      try {
        await inscriptionService.deleteAllInscriptions(this.selectedUserId);
      } catch (error) {
        console.error('Error al eliminar las inscripciones del usuario:', error);
      }
    },
    async deleteUserCourses() {
      try {
        await courseService.deleteAllCourses(this.selectedUserId);
      } catch (error) {
        console.error('Error al eliminar los cursos del usuario:', error);
      }
    }

  }
};
</script>