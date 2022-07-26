package com.geekbrains.tests.automator

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class DetailsScreenTest {

    //UiDevice предоставляет доступ к устройству
    private val uiDevice: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    //Контекст для запуска нужных экранов и получения packageName
    private val context = ApplicationProvider.getApplicationContext<Context>()

    //Путь к классам нашего приложения, которые будем тестировать
    private val packageName = context.packageName

    @Before
    fun setup() {
        //Cворачиваем все приложения, если что-то запущено
        uiDevice.pressHome()

        //Запускаем приложение
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)

        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)//Чистим бэкстек от запущенных ранее Активити
        context.startActivity(intent)

        //Ждем, когда приложение откроется на смартфоне чтобы начать тестировать его элементы
        uiDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), DetailsScreenTest.TIMEOUT)
    }
    companion object {
        private const val TIMEOUT = 5000L
    }

    //Убеждаемся, что приложение открыто. Для этого достаточно найти на экране любой элемент
    //и проверить его на null
    @Test
    fun test_TotalCountTextViewIsNotNull() {
        val toSearchButton: UiObject2 = uiDevice.findObject(
            By.res(
                packageName,
                "toDetailsActivityButton"
            )
        )
        //Кликаем по ней
        toSearchButton.click()

        Assert.assertNotNull("totalCountTextView")
    }

    @Test
    fun test_IncrementButtonIsActive() {
        val toSearchButton: UiObject2 = uiDevice.findObject(
            By.res(
                packageName,
                "toDetailsActivityButton"
            )
        )
        //Кликаем по ней
        toSearchButton.click()

        val incrementButton: UiObject = uiDevice.findObject(
            UiSelector().textContains("+")
        )
        incrementButton.click()
        Assert.assertNotNull("totalCountTextView")
    }
    @Test
    fun test_DecrementButtonIsActive() {
        val toSearchButton: UiObject2 = uiDevice.findObject(
            By.res(
                packageName,
                "toDetailsActivityButton"
            )
        )
        //Кликаем по ней
        toSearchButton.click()

        val incrementButton: UiObject = uiDevice.findObject(
            UiSelector().textContains("-")
        )
        incrementButton.click()
        Assert.assertNotNull("totalCountTextView")
    }
}
