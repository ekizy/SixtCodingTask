package com.ekiz.sixtcodingtask.data.repository

import com.ekiz.sixtcodingtask.data.NetworkHandler
import com.ekiz.sixtcodingtask.data.api.datasource.CarsRemoteDataSource
import com.ekiz.sixtcodingtask.data.api.responsemodels.CarAPIModel
import com.ekiz.sixtcodingtask.helper.MockJSONHelper
import com.ekiz.sixtcodingtask.util.ErrorException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import java.lang.reflect.Type

@RunWith(JUnit4::class)
class CarsRepositoryTest {

    @Test
    fun `getCars_withInternet_success`() = runBlockingTest{
        reset(remoteDataSource)
        `when`(networkHandler.hasInternetConnection()).thenReturn(true)
        `when`(remoteDataSource.getCars()).thenReturn(carAPIModelList)
        val response = carsRepository.getCars()
        Assert.assertTrue(response.isSuccess)
        response.decide({Assert.fail()}, {uiModels -> uiModels.size == carAPIModelList.size})
        verify(remoteDataSource,times(1)).getCars()
    }

    @Test
    fun `getCars_withInternet_failure`() = runBlockingTest{
        reset(remoteDataSource)
        `when`(networkHandler.hasInternetConnection()).thenReturn(true)
        `when`(remoteDataSource.getCars()).thenThrow(exception)
        val response = carsRepository.getCars()
        Assert.assertTrue(response.isFailure)
        response.decide({error -> exception.message == error.message}, {Assert.fail()})
        verify(remoteDataSource,times(1)).getCars()
    }

    @Test
    fun `getCars_withoutInternet`() = runBlockingTest{
        reset(remoteDataSource)
        `when`(networkHandler.hasInternetConnection()).thenReturn(false)
        `when`(remoteDataSource.getCars()).thenReturn(carAPIModelList)
        val response = carsRepository.getCars()
        Assert.assertTrue(response.isFailure)
        response.decide({error -> error is ErrorException.NoConnectionError}, {Assert.fail()})
        verify(remoteDataSource,times(0)).getCars()
    }

    companion object {
        private var networkHandler: NetworkHandler = mock(NetworkHandler::class.java)
        private lateinit var carsRepository: CarsRepository
        private var remoteDataSource: CarsRemoteDataSource = mock(CarsRemoteDataSource::class.java)
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
            carsRepository = CarsRepository(
                remoteDataSource,
                networkHandler
            )
        }
    }

}