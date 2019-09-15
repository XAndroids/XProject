package workshop1024.com.xproject.main.model.publisher.source

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import workshop1024.com.xproject.base.net.XRetrofit
import workshop1024.com.xproject.main.model.publisher.Publisher
import workshop1024.com.xproject.main.net.PublisherService
import java.util.*

/**
 * 发布者远程数据源
 */
class PublisherRepository private constructor() : PublisherDataSource {

    override fun getPublishersByContentType(contentId: String, loadPublishersCallback: PublisherDataSource.LoadPublishersCallback) {
        Log.i("XProject", "PublisherRemoteDataSource getPublishersByContentType =$contentId")
        val retrofit = XRetrofit.retrofit
        val publisherService = retrofit!!.create(PublisherService::class.java)
        val publisherListCall = publisherService.getPublishersByContentType(contentId)
        publisherListCall.enqueue(object : Callback<List<Publisher>> {
            override fun onResponse(call: Call<List<Publisher>>, response: Response<List<Publisher>>) {
                val publisherList = response.body()
                loadPublishersCallback.onPublishersLoaded(publisherList!!)
            }

            override fun onFailure(call: Call<List<Publisher>>, t: Throwable) {
                loadPublishersCallback.onDataNotAvailable()
            }
        })
    }

    override fun getPublishersByLanguageType(languageId: String, loadPublishersCallback: PublisherDataSource.LoadPublishersCallback) {
        Log.i("XProject", "PublisherRemoteDataSource getPublishersByLanguageType =$languageId")
        val retrofit = XRetrofit.retrofit
        val publisherService = retrofit!!.create(PublisherService::class.java)
        val publisherListCall = publisherService.getPublishersByLanguageType(languageId)
        publisherListCall.enqueue(object : Callback<List<Publisher>> {
            override fun onResponse(call: Call<List<Publisher>>, response: Response<List<Publisher>>) {
                val publisherList = response.body()
                loadPublishersCallback.onPublishersLoaded(publisherList!!)
            }

            override fun onFailure(call: Call<List<Publisher>>, t: Throwable) {
                loadPublishersCallback.onDataNotAvailable()
            }
        })
    }

    override fun subscribePublisherById(publisherId: String) {
        Log.i("XProject", "PublisherRemoteDataSource subscribePublisherById =$publisherId")
        val subscribedPublisher = PUBLISHERS_SERVICE_DATA!![publisherId]
        subscribedPublisher?.isSubscribed?.set(true)
    }

    override fun unSubscribePublisherById(publisherId: String) {
        Log.i("XProject", "PublisherRemoteDataSource unSubscribePublisherById =$publisherId")
        val subscribedPublisher = PUBLISHERS_SERVICE_DATA!![publisherId]
        subscribedPublisher?.isSubscribed?.set(false)
    }

    companion object {
        private var PUBLISHERS_SERVICE_DATA: MutableMap<String, Publisher>? = null

        private var INSTANCE: PublisherRepository? = null

        init {
            //TODO 如何本地提供Mock环境
            PUBLISHERS_SERVICE_DATA = LinkedHashMap(2)

            addPublisher("p001", "t001", "l001", "/imag1", "The Tech", "970601 subscribers", false)
            addPublisher("p002", "t001", "English", "/imag1", "Engadget", "1348433 subscribers", false)
            addPublisher("p003", "t001", "English", "/imag1", "Lifehacker", "934273 subscribers", false)
            addPublisher("p004", "t001", "English", "/imag1", "ReadWrite", "254332 subscribers", false)
            addPublisher("p005", "t001", "English", "/imag1", "Digital Trends", "145694 subscribers", false)
            addPublisher("p006", "t001", "English", "/imag1", "Business Insider", "331892 subscribers", false)
            addPublisher("p007", "t001", "中文", "/imag1", "月光博客", "254321 subscribers", false)
            addPublisher("p008", "t001", "中文", "/imag1", "36氪", "125345 subscribers", false)
            addPublisher("p009", "t001", "English", "/imag1", "TechCrunch", "994287 subscribers", false)


            addPublisher("p101", "News", "English", "/imag1", "The News", "970601 subscribers", false)
            addPublisher("p102", "News", "English", "/imag1", "Engadget", "1348433 subscribers", false)
            addPublisher("p103", "News", "English", "/imag1", "Lifehacker", "934273 subscribers", false)
            addPublisher("p104", "News", "English", "/imag1", "ReadWrite", "254332 subscribers", false)
            addPublisher("p105", "News", "English", "/imag1", "Digital Trends", "145694 subscribers", false)
            addPublisher("p106", "News", "English", "/imag1", "Business Insider", "331892 subscribers", false)
            addPublisher("p107", "News", "中文", "/imag1", "今日头条", "254321 subscribers", false)
            addPublisher("p108", "News", "中文", "/imag1", "腾讯新闻", "125345 subscribers", false)
            addPublisher("p109", "News", "English", "/imag1", "TechCrunch", "994287 subscribers", false)

            addPublisher("p201", "Business", "English", "/imag1", "The Business", "970601 subscribers", false)
            addPublisher("p202", "Business", "English", "/imag1", "The New York Times", "1348433 subscribers", false)
            addPublisher("p203", "Business", "English", "/imag1", "OZY", "934273 subscribers", false)
            addPublisher("p204", "Business", "English", "/imag1", "ABC News", "254332 subscribers", false)
            addPublisher("p205", "Business", "English", "/imag1", "FOX News", "145694 subscribers", false)
            addPublisher("p206", "Business", "English", "/imag1", "NRP News", "331892 subscribers", false)
            addPublisher("p207", "Business", "中文", "/imag1", "财经周刊", "254321 subscribers", false)
            addPublisher("p208", "Business", "中文", "/imag1", "交易时刻", "125345 subscribers", false)
            addPublisher("p209", "Business", "English", "/imag1", "BBC", "994287 subscribers", false)

            addPublisher("p301", "Health", "English", "/imag1", "zen habits", "970601 subscribers", false)
            addPublisher("p302", "Health", "English", "/imag1", "Skinnytaste", "1348433 subscribers", false)
            addPublisher("p303", "Health", "English", "/imag1", "Lifehacker", "934273 subscribers", false)
            addPublisher("p304", "Health", "English", "/imag1", "Mark's Daily Apple", "254332 subscribers", false)
            addPublisher("p305", "Health", "English", "/imag1", "Oh She Glows", "145694 subscribers", false)
            addPublisher("p306", "Health", "English", "/imag1", "Health", "331892 subscribers", false)
            addPublisher("p307", "Health", "中文", "/imag1", "健康之路", "254321 subscribers", false)
            addPublisher("p308", "Health", "中文", "/imag1", "星星点灯", "125345 subscribers", false)
            addPublisher("p309", "Health", "English", "/imag1", "NYT", "994287 subscribers", false)


            addPublisher("p401", "Gaming", "English", "/imag1", "The Gaming", "970601 subscribers", false)
            addPublisher("p402", "Gaming", "English", "/imag1", "Polygon", "1348433 subscribers", false)
            addPublisher("p403", "Gaming", "English", "/imag1", "Kotaku", "934273 subscribers", false)
            addPublisher("p404", "Gaming", "English", "/imag1", "Joystiq", "254332 subscribers", false)
            addPublisher("p505", "Gaming", "English", "/imag1", "IndieGames", "145694 subscribers", false)
            addPublisher("p606", "Gaming", "English", "/imag1", "Game Life", "331892 subscribers", false)
            addPublisher("p607", "Gaming", "中文", "/imag1", "电竞世界", "254321 subscribers", false)
            addPublisher("p608", "Gaming", "中文", "/imag1", "一起游戏", "125345 subscribers", false)
            addPublisher("p609", "Gaming", "English", "/imag1", "Penny Arcade", "994287 subscribers", false)
        }

        private fun addPublisher(publisherId: String, type: String, language: String, iconUrl: String,
                                 name: String, subscribeNum: String, isSubscribed: Boolean) {
            val newPublisher = Publisher(publisherId, type, language, iconUrl, name,
                    subscribeNum, isSubscribed)
            PUBLISHERS_SERVICE_DATA!![newPublisher.publisherId] = newPublisher
        }

        val instance: PublisherRepository
            get() {
                if (INSTANCE == null) {
                    INSTANCE = PublisherRepository()
                }

                return INSTANCE!!
            }
    }
}