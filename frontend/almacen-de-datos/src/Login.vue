<template>
    <div class="login-container">
        <div class="login">
            <img src="/src/Imagenes/dibujo-maquina-coser-concepto-taller-costura-creado-tecnologia-generativa-ai_919772-503.avif" alt="Maquina de coser">
                <form @submit.prevent="handleSubmit">
                <h2>Inicio de sesión</h2>
                <div>
                    <input type="text" v-model="user.username" id="username" placeholder="Usuario" required >
                </div>
                <div>
                    <input type="password" v-model="user.password" id="password" placeholder="Contraseña"required>
                </div>
                <button type="submit">Iniciar sesión</button>
                <div>
                </div>
                <div v-if="errorMessage">{{ errorMessage }}</div>
                <a href="/register.html">Registrarse</a>
            </form>
        </div>
    </div>
</template>
<script>
import axios from 'axios';
import UserService from '@/services/UserService';

export default {
    data() {
        return {
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
                this.user = response.data;
                delete this.user.password;
                sessionStorage.setItem('user', JSON.stringify(this.user));
                window.location.href = '/profile.html';
            } catch (error) {
                this.errorMessage = 'Usuario o contrasena equivocados';
                console.error('Error fetching user by password', error);
            }
        }
    }
};
</script>
