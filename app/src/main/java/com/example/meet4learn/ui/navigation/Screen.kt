package com.example.meet4learn.ui.navigation

sealed class Screen( val route: String) {
    object Login : Screen("login")
    object Register: Screen("register")
    object Splash: Screen("splash")
    object Main: Screen ("main")
    object Dashboard : Screen("dashboard")
    object MyCourses : Screen("my_courses")
    object  Calendar: Screen("calendar")
    object Profile: Screen("profile")
    object CourseDetails: Screen("course_details/{courseId}") {
        fun courseSelected(courseId: Int): String {
            return "course_details/$courseId"
        }
    }
    object MyCourseDetails: Screen("my_course_details/{courseId}") {
        fun courseSelected(courseId: Int): String {
            return "my_course_details/$courseId"
        }
    }
    object VideoCall: Screen("videocall/{moduleId}") {
        fun moduleSelected(moduleId: Int): String {
            return "videocall/$moduleId"
        }
    }
}