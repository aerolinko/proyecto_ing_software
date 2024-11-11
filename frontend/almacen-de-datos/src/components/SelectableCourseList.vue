<template>
    <div>
    <h2>Cursos Disponibles</h2>
    <ul>
        <li v-for="course in courses" :key="course.course_id">
        <input type="checkbox" :value="course.course_id" :checked="isSelected(course.course_id)" @change="toggleSelection(course.course_id)"/>
        Curso: {{ course.course_name }}, Descripción: {{ course.course_description }}
        <ul class="dias-disp">
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
    }
  }
};
</script>
