package com.ekiz.sixtcodingtask.data.api.datasource

import com.ekiz.sixtcodingtask.data.NetworkHandler
import com.ekiz.sixtcodingtask.data.api.responsemodels.CarAPIModel
import com.ekiz.sixtcodingtask.data.api.services.CarsService
import com.ekiz.sixtcodingtask.helper.MockJSONHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import java.lang.reflect.Type

class CarRemoteDataSourceTest {

    @Test
    fun `getCars_with_success`() = runBlockingTest{
        Mockito.reset(carsService)
        Mockito.`when`(carsService.getCars())
            .thenReturn(carAPIModelList)
        val response = remoteDataSource.getCars()
        Assert.assertEquals(response.size, carAPIModelList.size)
        Mockito.verify(carsService, Mockito.times(1)).getCars()
    }

    @Test(expected = java.lang.Exception::class)
    fun `getCars_with_exception`() = runBlockingTest{
        Mockito.reset(carsService)
        Mockito.`when`(carsService.getCars())
            .thenThrow(exception)
        remoteDataSource.getCars()
        Mockito.verify(carsService, Mockito.times(1)).getCars()
    }

    companion object {
        private var carsService: CarsService = mock(CarsService::class.java)
        private lateinit var remoteDataSource: CarsRemoteDataSource
        private lateinit var carAPIModelList: List<CarAPIModel>
        private var mockJSONFileName = "getCars.json"
        private val exception = Exception("Empty message")

        @BeforeClass
        @JvmStatic
        fun initialize() {
            MockJSONHelper.getJSONString(mockJSONFileName)?.let {
                val listType: Type = object: TypeToken<List<CarAPIModel>>(){}.type
                carAPIModelList = Gson().fromJson(it, listType)
            }

            remoteDataSource = CarsRemoteDataSource(carsService)
        }
    }

}