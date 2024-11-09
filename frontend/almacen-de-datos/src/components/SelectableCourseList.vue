<template>
    <div>
    <h2>Cursos Disponibles</h2>
    <ul>
        <li v-for="course in courses" :key="course.course_id">
        <input type="checkbox" :value="course.course_id" :checked="isSelected(course.course_id)" @change="toggleSelection(course.course_id)"/>
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
