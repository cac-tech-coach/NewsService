package com.cac.news

import com.cac.news.util.JsonUtil
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class NewService {

    val errorResult = ClassPathResource("data/error")
    @ResponseBody
    @RequestMapping("/news/type")
    fun getNewsType(): String {
        val resource = ClassPathResource("data/news_type_json")
        return JsonUtil.getJsonFromFile(resource)
    }

    @ResponseBody
    @RequestMapping("/news/list")
    fun getNewsList(id:String): String {
        try {
            val resource = ClassPathResource("data/news_id_$id")
            return JsonUtil.getJsonFromFile(resource)
        }catch (e:Exception){
            return JsonUtil.getJsonFromFile(errorResult)
        }
    }

    @ResponseBody
    @RequestMapping("/news/advertisement")
    fun getAdvertisement(): String {
        try {
            val resource = ClassPathResource("data/news_advertisement")
            return JsonUtil.getJsonFromFile(resource)
        }catch (e:Exception){
            return JsonUtil.getJsonFromFile(errorResult)
        }
    }
}
