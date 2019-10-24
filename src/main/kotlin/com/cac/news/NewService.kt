package com.cac.news

import com.cac.news.util.GsonUtil
import com.cac.news.util.GsonUtil.GsonToList
import com.cac.news.util.JsonUtil
import com.thoughtworks.cac.news.data.entities.DataModel
import com.thoughtworks.cac.news.data.entities.News
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.io.FileNotFoundException

@Controller
class NewService {

    val errorResult = ClassPathResource("data/error")
    val newsMap = HashMap<String, List<News>>()
    val pageSize = 10
    @ResponseBody
    @RequestMapping("/news/type")
    fun getNewsType(): String {
        val resource = ClassPathResource("data/news_type_json")
        return JsonUtil.getJsonFromFile(resource)
    }

    @ResponseBody
    @RequestMapping("/news/list")
    fun getNewsList(id: String?, index: Int?): String {
        try {
            var selectId = id
            selectId = if (id.isNullOrEmpty()) {
                "1"
            } else {
                id
            }
            var news = newsMap[selectId]
            if (news != null && news.isNotEmpty()) {
                return if (index == null) {
                    GsonUtil.GsonString(DataModel(true, "",news.size, news))
                } else {
                    var pageData = news.subList(index * pageSize, index * pageSize + pageSize)
                    GsonUtil.GsonString(DataModel(true, "",news.size, pageData))
                }
            } else {
                val resource = ClassPathResource("data/news_id_$selectId")
                var data = GsonToList(JsonUtil.getJsonFromFile(resource), News::class.java)
                selectId?.let { newsMap.put(it, data) }
                return if (index == null) {
                    GsonUtil.GsonString(DataModel(true, "", data.size,data))
                } else {
                    var pageData = data.subList(index * pageSize, index * pageSize + pageSize)
                    GsonUtil.GsonString(DataModel(true, "", data.size,pageData))
                }
            }
        } catch (e:FileNotFoundException){
            return  GsonUtil.GsonString(DataModel(false, "id invalid", 0,ArrayList<News>()))
        } catch (e:IndexOutOfBoundsException){
            return  GsonUtil.GsonString(DataModel(false, "index overflow", 0,ArrayList<News>()))
        }catch (e: Exception) {
            return JsonUtil.getJsonFromFile(errorResult)
        }
    }

    @ResponseBody
    @RequestMapping("/news/advertisement")
    fun getAdvertisement(): String {
        try {
            val resource = ClassPathResource("data/news_advertisement")
            return JsonUtil.getJsonFromFile(resource)
        } catch (e: Exception) {
            return JsonUtil.getJsonFromFile(errorResult)
        }
    }
}
