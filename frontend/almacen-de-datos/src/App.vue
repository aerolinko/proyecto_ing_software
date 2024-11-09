<template>
  <div id="app">
    <create-user @userCreated="fetchUsers"></create-user>
    <a href="/testing.html" style="margin: 0 auto; display:block; text-align: center; padding: 20px 20px">Ya tengo una cuenta</a>
    <user-list :users="users"></user-list>
  </div>
</template>

<script>
import axios from 'axios';
import UserList from './components/UserList.vue';
import CreateUser from './components/CreateUser.vue';

export default {
  name: 'App',
  components: {
    CreateUser,
    UserList,
  },
  data() {
    return {
      users: [],
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
    },
  },
  created() {
    this.fetchUsers();
  },
};
</script>
