<template>
    <div>
        <h2>Cursos</h2>
        <ul>
            <li v-for="course in courses" :key="course.course_id">
                <div class="form-grid">
                    <div class="column-1">
                        <p><strong>Nombre: </strong>{{ course.course_name }}</p>
                        <p><strong>Descripción: </strong>{{ course.course_description }}</P>
                    </div>
                    <div class="column-2">
                        <ul>
                            <li v-for="(time_range) in course.time_ranges" id="nulo">
                                <p><strong>Inicio:</strong> {{ time_range.start_time }}</p>
                                <p><strong>Fin:</strong> {{ time_range.end_time }}</p>
                                <div id="all-days">
                            <li id="clasas" v-for="(day) in time_range.days"> {{ day }}</li>
                    </div>
            </li>
        </ul>
    </div>
    </div>
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
