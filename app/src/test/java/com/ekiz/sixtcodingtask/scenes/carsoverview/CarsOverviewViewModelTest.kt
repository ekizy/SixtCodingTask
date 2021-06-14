package com.ekiz.sixtcodingtask.scenes.carsoverview

import com.ekiz.sixtcodingtask.data.api.datasource.CarsRemoteDataSource
import com.ekiz.sixtcodingtask.data.api.responsemodels.CarAPIModel
import com.ekiz.sixtcodingtask.data.api.services.CarsService
import com.ekiz.sixtcodingtask.domain.GetCarsUseCase
import com.ekiz.sixtcodingtask.domain.GetCarsUseCaseTest
import com.ekiz.sixtcodingtask.helper.MockJSONHelper
import com.ekiz.sixtcodingtask.util.ErrorException
import com.ekiz.sixtcodingtask.util.Response
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import org.mockito.Mockito
import org.mockito.Mockito.mock
import java.lang.reflect.Type

@ExperimentalCoroutinesApi
class CarsOverviewViewModelTest {

    private lateinit var carsOverviewViewModel: CarsOverviewViewModel
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    private var getCarsUseCase = mock(GetCarsUseCase::class.java)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `getCars_success`() = runBlockingTest{
        Mockito.reset(getCarsUseCase)
        val uiModels = carAPIModelList.map { it.toUIModel() }
        Mockito.`when`(getCarsUseCase.run())
            .thenReturn(Response.Success(uiModels))
        carsOverviewViewModel = CarsOverviewViewModel(getCarsUseCase)
        carsOverviewViewModel.bgScope = testScope
        carsOverviewViewModel.getCars()
        Mockito.verify(getCarsUseCase, Mockito.times(1)).run()
    }

    @Test
    fun `getCars_failure`() = testDispatcher.runBlockingTest{
        Mockito.reset(getCarsUseCase)
        Mockito.`when`(getCarsUseCase.run())
            .thenReturn(Response.Failure(errorException))
        carsOverviewViewModel = CarsOverviewViewModel(getCarsUseCase)
        carsOverviewViewModel.bgScope = testScope
        carsOverviewViewModel.getCars()
        Mockito.verify(getCarsUseCase, Mockito.times(1)).run()
    }

    companion object {
        private lateinit var carAPIModelList: List<CarAPIModel>
        private var mockJSONFileName = "getCars.json"
        private var errorException = ErrorException.APIError("Empty message")

        @BeforeClass
        @JvmStatic
        fun initialize() {
            MockJSONHelper.getJSONString(mockJSONFileName)?.let {
                val listType: Type = object: TypeToken<List<CarAPIModel>>(){}.type
                carAPIModelList = Gson().fromJson(it, listType)
            }
        }
    }

}