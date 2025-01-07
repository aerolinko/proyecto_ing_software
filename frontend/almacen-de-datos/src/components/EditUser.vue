<template>
  <div class="form-container">
    <form @submit.prevent="handleSubmit" class="form-grid">
      <div>
        <label for="select-user">Seleccionar Usuario:</label>
        <select v-model="selectedUserId" id="select-user" @change="loadUsers">
          <option v-for="user in users" :key="user.id" :value="user.id">
            {{ user.username + '-- id: ' + user.id }}
          </option>
        </select>
      </div>

      <div v-if="user">
        <div>
          <label for="firstname">Nombre</label>
          <input type="text" v-model="user.firstName" id="firstName" required>
        </div>
        <div>
          <label for="lastname">Apellido</label>
          <input type="text" v-model="user.lastName" id="lastName" required>
        </div>
        <div>
          <label for="email">Email</label>
          <input type="email" v-model="user.email" id="email" required>
        </div>
        <div>
          <label for="email">Usuario</label>
          <input type="text" v-model="user.username" id="username" required>
        </div>
        <div>
          <label for="email">Contraseña</label>
          <input type="password" v-model="user.password" id="password" required>
        </div>
        <button type="submit">Guardar Cambios</button>
      </div>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'EditUser',
  props: {
    users: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      selectedUserId: null,
      user: null,
    };
  },
  methods: {
    reset() {
      this.selectedUserId = null;
      this.user = null;
    },
    loadUsers() {
      this.user = this.users.find(user => user.id === this.selectedUserId);
    },
    async handleSubmit() {
      try {
        await axios.put(`http://localhost:4000/api/user/${this.user.id}`, this.user);
        this.$emit('userUpdated'); // Emitir un evento para actualizar la lista
        alert('Usuario actualizado correctamente');
        this.reset();
      } catch (error) {
        console.error('Error actualizando el usuario:', error);
      }
    },
  },
};
</script>