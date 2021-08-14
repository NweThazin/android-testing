package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [29])
@RunWith(AndroidJUnit4::class)
class TasksViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var tasksViewModel: TasksViewModel

    @Before
    fun setupViewModel() {
        tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun addNewTask_setsNewTaskEvent() {
        // given a fresh TasksViewModel
        tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())

        // when adding a new task
        tasksViewModel.addNewTask()

        // then the new task event is triggered
        val value = tasksViewModel.newTaskEvent.getOrAwaitValue()
        assertThat(
            value.getContentIfNotHandled(), (not(nullValue()))
        )

    }

    @Test
    fun setFilterAllTasks_tasksAndViewVisible() {
        // given a fresh TasksViewModel
        tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())

        // when filtering type is ALL_TASKS
        tasksViewModel.setFiltering(TasksFilterType.ALL_TASKS)

        // then the "Add task" action is visible
        assertThat(tasksViewModel.tasksAddViewVisible.getOrAwaitValue(), `is`(true))
    }
}