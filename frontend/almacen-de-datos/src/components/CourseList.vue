<template>
    <div>
    <h2>Cursos</h2>
    <ul>
        <li v-for="course in courses" :key="course.course_id">
            <ul>Nombre: {{ course.course_name }}</ul>
            <ul>Descripción: {{ course.course_description }}</ul>
            <ul>
                <li v-for="(time_range) in course.time_ranges" id="nulo">
                    Inicio: {{ time_range.start_time }}
                    Fin: {{ time_range.end_time }}
                    <ul id="dias-container">
                        <li v-for="(day) in time_range.days"> {{ day }}</li>
                    </ul>
                </li>
            </ul>
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


