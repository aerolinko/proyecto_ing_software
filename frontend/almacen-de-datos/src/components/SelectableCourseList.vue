<template>
    <div>
    <h2>Selecciona un curso</h2>
    <ul>
        <li v-for="course in courses" :key="course.course_id">
        <input type="checkbox" :value="course.course_id" :checked="isSelected(course.course_id)" @change="toggleSelection(course.course_id)"/>
        <ul>Nombre: {{ course.course_name }}</ul>
        <ul>Descripción: {{ course.course_description }}</ul>
        <ul class="dias-disp">
            <li v-for="(time_range) in course.time_ranges" id="nulo">
                Inicio: {{ time_range.start_time }}
                Fin: {{ time_range.end_time }}
                        <div id="all-days">
                            <li id="clasas" v-for="(day) in time_range.days"> {{ day }}</li>
                        </div>
                    </li>
                </ul>
        </li>
    </ul>
    </div>
</template>

<script>
import CourseService from "@/services/CourseService.js";

export default {
    name: 'CourseList',
    props: {
    courses: {
        type: Array,
        required: true
    },
    selectedCourses: {
        type: Array,
        default: () => []
    },
   },
  methods: {
    isSelected(courseId) {
      return this.selectedCourses.includes(courseId);
    },
    toggleSelection(courseId)
    { const newSelectedCourses = this.isSelected(courseId)
    ? this.selectedCourses.filter(id => id !== courseId)
    : [...this.selectedCourses, courseId];

    this.$emit('update:selected-courses', newSelectedCourses); // Emit the updated array
    },
  }
};
</script>
