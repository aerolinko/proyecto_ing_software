<template>
    <form @submit.prevent="handleSubmit">
    <h2> Registrarse</h2>
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
        <label for="email">Contrasena</label>
        <input type="password" v-model="user.password" id="password" required>
    </div>
    <button type="submit">Crear Usuario</button>
    </form>
</template>

<script>
import axios from 'axios';

export default {
    name: 'CreateUser',
    data() {
    return {
        user: {
        firstName: '',
        lastName: '',
        email: '',
        username: '',
        password: '',
        }
    };
    },
    methods: {
    resetForm() {
        this.user = { firstName: '', lastName: '', email: '', username: '', password: '' };
    },
    async handleSubmit() {
        try {
        const response = await axios.post('http://localhost:4000/api/user', this.user);
        console.log(response.data);
          this.$emit('userCreated'); // Emitir un evento para informar que se ha creado un usuario
          this.resetForm(); // Limpiar el formulario
        } catch (error) {
        console.error('There was an error creating the user!', error);
        }
    }
    }
};
</script>

