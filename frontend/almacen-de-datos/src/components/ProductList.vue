<template>
    <div>
    <h2>Lista de Productos</h2>
    <ul>
        <li v-for="product in products" :key="product.product_id">
        id -> {{product.product_id}} - {{ product.product_name }} - {{ product.product_description }}
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


