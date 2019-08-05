package com.cac.news.util

import org.springframework.core.io.ClassPathResource
import java.io.BufferedReader
import java.io.InputStreamReader


object JsonUtil {

    fun getJsonFromFile(file: ClassPathResource): String {
        val stream = file.inputStream
        var buffer = BufferedReader(InputStreamReader(stream))
        var resultStr = StringBuffer()
        var tempStr: String?
        do {
            tempStr = buffer.readLine()
            if (tempStr == null) {
                break
            }
            resultStr.append(tempStr+"\n")

        } while (true)
        buffer.close()
        return resultStr.toString()
    }
}