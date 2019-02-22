package me.mfathy.mobiquity.data.store.remote

import com.google.gson.GsonBuilder
import io.reactivex.observers.TestObserver
import me.mfathy.mobiquity.data.store.remote.model.NetCategory
import me.mfathy.mobiquity.data.store.remote.service.RemoteServiceApi
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals

/**
 * Created by Mohammed Fathy.
 * dev.mfathy@gmail.com
 *
 * Unit testing for RemoteApi using MockWebService
 * Test assumptions:
 * - Test success request.
 * - Test 500 Server error request.
 */
@RunWith(JUnit4::class)
class RemoteServiceApiTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var mRemoteApi: RemoteServiceApi

    @Before
    fun setUp() {

        //  Start mocking server.
        mockWebServer = MockWebServer()
        mockWebServer.start()

        // Get an okhttp client
        val okHttpClient = OkHttpClient.Builder()
            .build()

        // Get an instance of Retrofit
        val mGson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create(mGson))
            .client(okHttpClient)
            .build()

        // Get an instance of blogService
        mRemoteApi = retrofit.create(RemoteServiceApi::class.java)
    }

    /**
     * Tests getCategories() response OK from MockWebServer
     * Validates path.
     * Validates response has values
     */
    @Test
    fun testGetTractorsHasResponseOK() {
        val testObserver = TestObserver<List<NetCategory>>()
        val path = "/"

        //  Given
        val content =
            this.javaClass.classLoader?.getResource("get-categories/valid.json")?.readText(Charset.forName("UTF-8"))
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(content)
        )

        //  When
        mRemoteApi.getCategories().subscribe(testObserver)
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS)

        //  Then
        val values = testObserver.values()

        // No errors
        testObserver.assertNoErrors()
        // One list emitted
        testObserver.assertValueCount(1)

        // Get the request that was just made
        val request = mockWebServer.takeRequest()
        // Make sure we made the request to the required path
        assertEquals(path, request.path)

        // has the correct response.
        val resultTractorsList = values[0]
        assertEquals(resultTractorsList.size, 2)
    }

    /**
     * Tests getCategories() response 500 Server error from MockWebServer
     * Validates path.
     * Validates response has values
     */
    @Test
    fun testGetTractorsHasResponse500ServerError() {
        val testObserver = TestObserver<List<NetCategory>>()

        //  Given
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(500)
        )

        //  When
        mRemoteApi.getCategories().subscribe(testObserver)
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS)

        // 500 Server error
        testObserver.assertError(HttpException::class.java)
    }
}