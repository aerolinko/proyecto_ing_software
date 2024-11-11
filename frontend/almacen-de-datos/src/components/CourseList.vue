<template>
    <div>
    <h2>Tus Cursos</h2>
    <ul>
        <li v-for="course in courses" :key="course.course_id">
        Curso: {{ course.course_name }}, Descripción: {{ course.course_description }}
        <ul>
            <li v-for="(time_range) in course.time_ranges">
             <p>Hora Inicio: <br>{{ time_range.start_time }}</p>
             <p>Hora Fin: <br>{{ time_range.end_time }}</p>
                <ul>
                    <li v-for="(day) in time_range.days"> {{ day }}
                    </li>
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


