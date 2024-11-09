<template>
    <div>
    <form @submit.prevent="handleSubmit">
        <h2> Log in</h2>
        <div>
            <label for="username">Nombre de usuario:</label>
            <input type="text"  v-model="user.username" id="username" required>
        </div>
        <div>
            <label for="password">contrasena:</label>
            <input type="password" v-model="user.password" id="password" required>
        </div>

        <button type="submit">Log in</button>
        <div>
    </div>
        <div v-if="errorMessage">{{ errorMessage }}</div>
        <a href="/index.html">Registrarse</a>
        </form>
    </div>
</template>
<script>
import axios from 'axios';
import UserService from '@/services/UserService';

export default {
    data(){
    return{
          user: {
          firstName: '',
          lastName: '',
          email: '',
          username: '',
          password: '',
          },
          errorMessage: '',
        };
    },
    methods: {
    async handleSubmit() {
        try {
        const response = await UserService.getUserByUsername(this.user);
        this.user=response.data;
        delete this.user.password;
        sessionStorage.setItem('user',JSON.stringify(this.user));
        window.location.href='/profile.html';
        } catch (error) {
        this.errorMessage = 'Usuario o contrasena equivocados';
        console.error('Error fetching user by password', error);
        }
    }
    }
};
</script>


