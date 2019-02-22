package me.mfathy.mobiquity.mock.server

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.nio.charset.Charset


/**
 * Created by Mohammed Fathy
 * dev.mfathy@gmail.com
 */
internal class MockServerDispatcher {

    /**
     * Return ok response from mock server
     */
    internal inner class RequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            val content =
                this.javaClass.classLoader?.getResource("get-categories/valid.json")?.readText(Charset.forName("UTF-8"))

            return when {
                request.path == "/" -> MockResponse().setResponseCode(200).setBody(content)
                else -> MockResponse().setResponseCode(404)
            }
        }
    }

    /**
     * Return error response from mock server
     */
    internal inner class ErrorDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return MockResponse().setResponseCode(500)
        }
    }
}