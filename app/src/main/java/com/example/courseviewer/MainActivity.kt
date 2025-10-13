package com.example.courseviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.compose.ui.unit.sp




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: CourseViewModel by viewModels()
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "courseList") {

                composable("courseList") {
                    val courses by viewModel.uiState.collectAsState()
                    CourseListScreen(
                        courses = courses,
                        onCourseClick = { courseNum ->
                            navController.navigate("courseDetails/$courseNum")
                        },
                        onCourseAdd = {
                            navController.navigate("addCourse")
                        }
                    )
                }

                composable(
                    route = "courseDetails/{courseNum}",
                    arguments = listOf(navArgument("courseNum") { type = NavType.StringType })
                ) { backStackEntry ->
                    val courses by viewModel.uiState.collectAsState()
                    val courseNum = backStackEntry.arguments?.getString("courseNum") ?: ""
                    CourseDetailsScreen(
                        courses = courses,
                        courseNum = courseNum,
                        onCourseDelete = { cNum ->
                            viewModel.deleteCourse(cNum)
                            navController.popBackStack()
                        },
                        back = {navController.popBackStack()}
                    )
                }

                composable("addCourse") {
                    AddCourseScreen(
                        onSaveCourse = { dep, num, loc ->
                            viewModel.addCourse(dep, num, loc)
                            navController.popBackStack()
                        }
                    )
                }


            }
        }
    }
}

    @Composable
    fun CourseListScreen(
        courses: List<Course>,
        onCourseClick: (String) -> Unit = {},
        onCourseAdd: () -> Unit = {}
    ) {


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(courses) { course ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(16.dp)
                        .clickable { onCourseClick(course.courseNum) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${course.department} ${course.courseNum}",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally) {

        Button(
            onClick = { onCourseAdd() }
        ) {
            Text("Add new course")
        }
    }

    }

    @Composable
    fun CourseDetailsScreen(
        courses: List<Course>,
        courseNum: String,
        onCourseDelete: (cNum: String) -> Unit = {},
        back: () -> Unit = {}
    ) {
        val current = courses.find { it.courseNum == courseNum }

        if (current == null) {
            Text("Course does not exist")
            return
        }

        Column (modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)){
            Text("Department:" + current.department,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
                )
            Text("Course Number::" + current.courseNum,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold)
            Text("Location:" + current.location,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold)
        }


        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Button(onClick = { back() }) {
                Text("Back to course list")
            }

            Button(
                onClick = { onCourseDelete(current.courseNum) }
            ) {
                Text("Delete course")
            }
        }
    }

    @Composable
    fun AddCourseScreen(
        onSaveCourse: (department: String, courseNum: String, location: String) -> Unit
    ) {

        var dep by remember { mutableStateOf("") }
        var cNum by remember { mutableStateOf("") }
        var loc by remember { mutableStateOf("") }

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(
                value = dep,
                onValueChange = { dep = it },
                label = { Text("Department:") },
                placeholder = { Text("Department:") }
            )
            TextField(
                value = cNum,
                onValueChange = { cNum = it },
                label = { Text("Course Number:") },
                placeholder = { Text("Course Number:") }
            )
            TextField(
                value = loc,
                onValueChange = { loc = it },
                label = { Text("Location:") },
                placeholder = { Text("Location:") }
            )
        }

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Button(
                onClick = { onSaveCourse(dep, cNum, loc) }
            ) {
                Text("Save course")
            }
        }
    }
