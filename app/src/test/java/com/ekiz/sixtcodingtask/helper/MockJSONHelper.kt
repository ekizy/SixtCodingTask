package com.ekiz.sixtcodingtask.helper


class MockJSONHelper {

    companion object {
        fun getJSONString(resourceName: String): String? {
            return this::class.java.classLoader?.getResource(resourceName)?.readText()
        }
    }

}