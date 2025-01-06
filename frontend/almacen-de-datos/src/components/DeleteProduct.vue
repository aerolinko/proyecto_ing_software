<template>
  <div class="delete-container">
    <h3>Eliminar Producto</h3>
    <select v-model="selectedProductId" :id="'optioner'">
      <option v-for="product in products" :key="product.product_id" :value="product.product_id" >
        {{ product.product_name }}
      </option>
    </select>
    <button @click="deleteProduct" :disabled="!selectedProductId">Eliminar Producto</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'DeleteProduct',
  props: {
    products: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      selectedProductId: null,
    };
  },
  methods: {
    reset(){
      document.getElementById('optioner').selectedIndex = -1;
    },
    async deleteProduct() {
      try {
        if (confirm('¿Estás seguro de que deseas eliminar este producto?')) {
          await axios.delete(`http://localhost:4000/api/product/${this.selectedProductId}`);
          this.$emit('productDeleted'); // Emitir un evento para actualizar la lista
          alert('Producto eliminado correctamente');
          this.reset();
        }
      } catch (error) {
        console.error('Error eliminando el producto:', error);
      }
    },
  },
};
</script>