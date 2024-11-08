<template>
    <div>
    <h2>Búsqueda de Usuario por ID</h2>
    <input type="text" v-model="userId" placeholder="Ingresa el ID del usuario" />
    <button @click="fetchUserById">Buscar Usuario</button>
        <div v-if="user">
        <h3>Detalles del Usuario</h3>
        <p>ID: {{ user.id }}</p>
        <p>Nombre: {{ user.firstName }}</p>
        <p>Apellido: {{ user.lastName }}</p>
        <p>Email: {{ user.email }}</p>
    </div>
        <div v-if="errorMessage">{{ errorMessage }}</div>
    </div>
</template>
<script>
import UserService from '@/services/UserService';

export default {
    name: 'UserById',
    props: {
    users: {
        type: Array,
        required: true
    }
    },
    data() {
    return {
        userId: '',
        user: null,
        errorMessage: ''
    };
    },
    methods: {
    async fetchUserById() {
        try {
        const response = await UserService.getUserById(this.userId);
        this.user = response.data;
             this.errorMessage = ''; // Limpiar el mensaje de error si la búsqueda fue exitosa
        } catch (error) {
          this.user = null; // Limpiar el usuario si hay un error
        this.errorMessage = 'Usuario no encontrado. Verifica el ID.';
        console.error('Error fetching user by ID:', error);
        }
    }
    }
};
</script>

