<template>
  <div class="delete-container">
    <h3>Eliminar Inscripción</h3>
    <select v-model="selectedInscriptionId" :id="'optioner'">
      <option v-for="inscription in inscriptions" :key="inscription.inscription_id" :value="inscription.inscription_id" >
        {{ inscription.course_name }}
      </option>
    </select>
    <button id="delete-course-products-inscriptions-users" class="btn btn-danger" @click="deleteInscription" :disabled="!selectedInscriptionId">Eliminar Inscripción</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'DeleteInscription',
  props: {
    inscriptions: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      selectedInscriptionId: null,
    };
  },
  methods: {
    reset(){
      document.getElementById('optioner').selectedIndex = -1;
      this.selectedInscriptionId = null;
    },
    async deleteInscription() {
      try {
        if (confirm('¿Estás seguro de que deseas eliminar este producto?')) {
          await axios.delete(`http://localhost:4000/api/inscription/${this.selectedInscriptionId}`);
          this.$emit('inscriptionDeleted'); // Emitir un evento para actualizar la lista
          alert('Inscripción eliminada correctamente');
          this.reset();
        }
      } catch (error) {
        console.error('Error eliminando la inscripción:', error);
      }
    },
  },
};
</script>