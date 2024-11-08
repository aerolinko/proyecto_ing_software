<template>
  <div id="app">
    <create-user @userCreated="fetchUsers"></create-user>
    <user-list :users="users"></user-list>
    <delete-user @userDeleted="fetchUsers"></delete-user>
    <user-by-id :users="users"></user-by-id>
  </div>
</template>

<script>
import axios from 'axios';
import UserList from './components/UserList.vue';
import CreateUser from './components/CreateUser.vue';
import DeleteUser from './components/DeleteUser.vue';
import UserById from './components/UseryById.vue';

export default {
  name: 'App',
  components: {
    CreateUser,
    UserList,
    DeleteUser,
    UserById
  },
  data() {
    return {
      users: []
    };
  },
  methods: {
    async fetchUsers() {
      try {
        const response = await axios.get('http://localhost:4000/api/user');
        this.users = response.data;
      } catch (error) {
        console.error('There was an error fetching the users!', error);
      }
    }
  },
  created() {
    this.fetchUsers();
  },
};
</script>
