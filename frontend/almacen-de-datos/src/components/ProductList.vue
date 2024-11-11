<template>
    <div>
    <h2>Lista de Productos</h2>
    <ul>
        <li v-for="product in products" :key="product.product_id">
        Producto: {{ product.product_name }}, Descripción: {{ product.product_description }}
        </li>
    </ul>
    </div>
</template>

<script>
export default {
    name: 'ProductList',
    props: {
    products: {
        type: Array,
        required: true
    }
    }
};
</script>


