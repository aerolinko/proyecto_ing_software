<template>
    <form @submit.prevent="handleSubmit">
        <div>
        <h2>Eliminar Usuario</h2>
        <input type="text" v-model="user.id" id="id" placeholder="Ingresa el ID del usuario" required/>
        <button type="submit">Buscar</button>
        </div>
    </form>
</template>

<script>
import axios from 'axios';
export default{
    name: 'DeleteUser',
    data(){
        return{
            user:{
                id: ''
            }
        }
    },
    methods:{
        async handleSubmit(){
            try{
                const response = await axios.delete(`http://localhost:4000/api/user/${this.user.id}`);
                console.log(response.data);
                this.$emit('userDeleted');
                this.resetForm();
            }catch(error){
                console.error('There was an error deleting the user!', error);
            }
        },
        resetForm(){
            this.user = {id: ''};
        }
    }
}
</script>