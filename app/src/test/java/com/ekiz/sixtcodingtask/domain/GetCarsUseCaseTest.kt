package com.ekiz.sixtcodingtask.domain

import com.ekiz.sixtcodingtask.data.api.responsemodels.CarAPIModel
import com.ekiz.sixtcodingtask.data.repository.CarsRepository
import com.ekiz.sixtcodingtask.helper.MockJSONHelper
import com.ekiz.sixtcodingtask.util.ErrorException
import com.ekiz.sixtcodingtask.util.Response
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mockito
import java.lang.reflect.Type

class GetCarsUseCaseTest {

    @Test
    fun `getCars_success`() = runBlockingTest{
        Mockito.reset(carsRepository)
        Mockito.`when`(carsRepository.getCars())
            .thenReturn(Response.Success(carAPIModelList.map { it.toUIModel() }))
        val response = getCarsUseCase.run()
        Assert.assertTrue(response.isSuccess)
        response.decide({ Assert.fail()}, { uiModels -> uiModels.size == carAPIModelList.size})
        Mockito.verify(carsRepository, Mockito.times(1)).getCars()
    }

    @Test
    fun `getCars_failure`() = runBlockingTest{
        Mockito.reset(carsRepository)
        Mockito.`when`(carsRepository.getCars()).thenReturn(Response.Failure(exception))
        val response = getCarsUseCase.run()
        Assert.assertTrue(response.isFailure)
        response.decide({error -> error.message == exception.message}, {Assert.fail()})
        Mockito.verify(carsRepository, Mockito.times(1)).getCars()
    }

    companion object {
        private lateinit var getCarsUseCase: GetCarsUseCase
        private var carsRepository: CarsRepository =
            Mockito.mock(CarsRepository::class.java)
        private lateinit var carAPIModelList: List<CarAPIModel>
        private var mockJSONFileName = "getCars.json"
        private val exception = ErrorException.APIError("Empty message")

        @BeforeClass
        @JvmStatic
        fun initialize() {
            MockJSONHelper.getJSONString(mockJSONFileName)?.let {
                val listType: Type = object: TypeToken<List<CarAPIModel>>(){}.type
                carAPIModelList = Gson().fromJson(it, listType)
            }
            getCarsUseCase = GetCarsUseCase(
                carsRepository
            )
        }
    }
}