package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class StatisticsUtilsTest {

    // No completed test
    @Test
    fun getActiveAndCompletedStats_noCompleted_returnHundredZero() {
        val tasks = listOf(
            Task("title", "description", isCompleted = false)
        )

        val result = getActiveAndCompletedStats(tasks)

        // check the results
        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(100f))
    }

    // one complete test and no active task
    @Test
    fun getActiveAndCompleteStats_oneCompleteNoActive_returnHundredZero() {
        val tasks = listOf(
            Task("title", "description", isCompleted = true)
        )

        val result = getActiveAndCompletedStats(tasks)

        // check the status
        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(100f))
    }

    // two completed tasks and three active tasks
    @Test
    fun getActiveAndCompleteStats_twoCompletedThreeActive_returnFortySixtyPercentage() {
        val tasks = listOf(
            Task("title1", "description", isCompleted = true),
            Task("title2", "description", isCompleted = true),
            Task("title3", "description", isCompleted = false),
            Task("title4", "description", isCompleted = false),
            Task("title5", "description", isCompleted = false)
        )

        val result = getActiveAndCompletedStats(tasks)

        // check the status
        assertThat(result.completedTasksPercent, `is`(40f))
        assertThat(result.activeTasksPercent, `is`(60f))
    }

    // for empty list
    @Test
    fun getActiveAndCompleteStats_empty_returnZero() {
        val tasks = listOf<Task>()

        val result = getActiveAndCompletedStats(tasks)

        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }

    // for empty list
    @Test
    fun getActiveAndCompleteStats_error_returnZero() {
        val tasks = null

        val result = getActiveAndCompletedStats(tasks)

        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }


}