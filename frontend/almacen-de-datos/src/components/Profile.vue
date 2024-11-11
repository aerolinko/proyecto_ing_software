<template>
    <div class="profile-container">
    <h2>Perfil de Usuario</h2>
    <div v-if="user">
        <p><strong>Nombre:</strong> {{ user.firstName }} {{ user.lastName }}</p>
        <p><strong>Email:</strong> {{ user.email }}</p>
        <p><strong>Usuario:</strong> {{user.username }}</p>
    </div>
    </div>
</template>

<script>
export default {
    name: 'Profile',
    props: {
    user: Object,
    },
    methods: {
    editProfile() {
        console.log('Edit profile clicked');
    }
    }
};
</script>