<template>
    <div>
    <form @submit.prevent="handleSubmit">
        <h2> Log in</h2>
        <div>
            <label for="user-username">Nombre de usuario:</label>
            <input type="text"  v-model="username" id="user-username" required>
        </div>
        <div>
            <label for="user-password">contrasena:</label>
            <input type="password" v-model="password" id="user-password" required>
        </div>

        <button type="submit">Log in</button>
        <div>
    </div>
        <div v-if="errorMessage">{{ errorMessage }}</div>
        </form>
    </div>
</template>
<script>
import UserService from '@/services/UserService';
import axios from 'axios';

export default {
    data(){
    return{
          user: '',
          username: '',
          password: '',
          errorMessage: '',
        };
    },
    methods: {
    async handleSubmit() {
        try {
        const response = await UserService.getUserByUsername(this.username,this.password);
        this.user = response.data;
        window.location.href = "/index.html";
        } catch (error) {
          this.user = null; // Limpiar el usuario si hay un error
          this.password = null;
        this.errorMessage = 'Usuario o contrasena equivocados';
        console.error('Error fetching user by password', error);
        }
    }
    }
};
</script>


