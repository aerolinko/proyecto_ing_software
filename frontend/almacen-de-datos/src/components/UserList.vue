<template>
    <div>
    <h2>Lista de Usuarios</h2>
    <ul>
        <li v-for="user in users" :key="user.id">
        id -> {{user.id}} - {{ user.firstName }} - {{ user.lastName }} - {{ user.email }}
        </li>
    </ul>
    </div>
</template>

<script>
export default {
    name: 'UserList',
    props: {
    users: {
        type: Array,
        required: true
    }
    }
};
</script>

