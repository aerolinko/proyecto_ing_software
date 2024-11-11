<template>
    <div>
    <h2>Lista de Productos</h2>
    <ul>
        <li v-for="product in products" :key="product.product_id">
            <ul>Producto: {{ product.product_name }}</ul>
            <ul>Descripción: {{ product.product_description }}</ul>
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


