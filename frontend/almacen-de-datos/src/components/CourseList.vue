<template>
    <div>
    <h2>Tus Cursos</h2>
    <ul>
        <li v-for="course in courses" :key="course.course_id">
        id -> {{course.course_id}} - {{ course.course_name }} - {{ course.course_description }}
        </li>
    </ul>
    </div>
</template>

<script>
export default {
    name: 'CourseList',
    props: {
    courses: {
        type: Array,
        required: true
    }
    }
};
</script>


