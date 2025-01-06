<template>
  <div class="form-container">
    <form @submit.prevent="handleSubmit" class="form-grid">
      <div>
        <label for="select-product">Seleccionar producto:</label>
        <select v-model="selectedProductId" id="select-product" @change="loadProducts" >
          <option v-for="product in products" :key="product.product_id" :value="product.product_id">
            {{ product.product_name }}
          </option>
        </select>
      </div>

      <div v-if="product">
        <div>
          <label for="product-name">Nombre del producto:</label>
          <input type="text" v-model="product.product_name" id="product-name" maxlength="25" required>
        </div>
        <div>
          <label for="description">Descripción del curso:</label>
          <textarea v-model="product.product_description" rows="6" cols="42" maxlength="255" id="description"
                    required></textarea>
        </div>
        <button type="submit">Guardar Cambios</button>
      </div>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'EditProduct',
  props: {
    products: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      selectedProductId: null,
      product: null,
    };
  },
  methods: {
    reset(){
      this.selectedProductId=null;
      this.product = null;
    },
    loadProducts() {
      this.product = this.products.find(product => product.product_id === this.selectedProductId);
    },
    async handleSubmit() {
      try {
        await axios.put(`http://localhost:4000/api/product/${this.product.product_id}`, this.product);
        this.$emit('productUpdated'); // Emitir un evento para actualizar la lista
        alert('Producto actualizado correctamente');
        this.reset();
      } catch (error) {
        console.error('Error actualizando el product:', error);
      }
    },
  },
};
</script>