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

<style>
h2 {
    color: #000000;
    text-align: center;
}
li{
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 50px;
    width: 50%;
    margin: 10px auto;
    text-align: center;
}
</style>
