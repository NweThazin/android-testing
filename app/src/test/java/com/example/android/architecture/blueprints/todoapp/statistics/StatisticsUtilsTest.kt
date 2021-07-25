package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import junit.framework.TestCase
import org.junit.Assert.assertEquals
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
        assertEquals(0f, result.completedTasksPercent)
        assertEquals(100f, result.activeTasksPercent)
    }

    // one complete test and no active task
    @Test
    fun getActiveAndCompleteStats_oneCompleteNoActive_returnHundredZero() {
        val tasks = listOf(
            Task("title", "description", isCompleted = true)
        )

        val result = getActiveAndCompletedStats(tasks)

        // check the status
        assertEquals(0f, result.activeTasksPercent)
        assertEquals(100f, result.completedTasksPercent)
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
        assertEquals(40f, result.completedTasksPercent)
        assertEquals(60f, result.activeTasksPercent)
    }
}