package workshop1024.com.xproject.main.publisher.data.source

import workshop1024.com.xproject.main.publisher.data.Publisher
import workshop1024.com.xproject.main.publisher.data.PublisherType

/**
 * 发布者数据源接口，定义了关于发布者数据相关的处理接口
 */
interface PublisherDataSource {

    fun getPublishersAndPublisherTypes(loadCallback: LoadCallback)

    /**
     * 获取指定类型的发布者
     *
     * @param contentId 发布者指定类型的id
     * @param LoadCallback 加载发布者信息回调
     */
    fun getPublishersByContentType(contentId: String, loadCallback: LoadCallback)

    /**
     * 获取指定语言的发布者
     *
     * @param languageId 发布者指定的语言
     * @param LoadCallback 加载发布者信息回调
     */
    fun getPublishersByLanguageType(languageId: String, loadCallback: LoadCallback)

    /**
     * 订阅指定id的发布者
     *
     * @param id 要订阅的发布者id
     */
    fun subscribePublisherById(id: String)

    /**
     * 取消订阅指定id发布者
     *
     * @param id 要取消订阅的发布者id
     */
    fun unSubscribePublisherById(id: String)


    fun deleteAllPublishers()

    fun deleteAllPublisherTypes()

    fun savePublisher(publisher: Publisher)

    fun savePublisherType(publisherType: PublisherType)

    fun getIsRequestRemote(): Boolean

    interface LoadPublisherAndPublisherTypeCallback : LoadRemotePubliserAndPublisherTypeCallback, LoadCacheOrLocalPublisherAndPublisherTypeCallback

    interface LoadCacheOrLocalPublisherAndPublisherTypeCallback : LoadCacheOrLocalPublisherCallback {
        fun onCacheOrLocalPublisherTypesLoaded(contentTypeList: List<PublisherType>, languageTyleList: List<PublisherType>)
    }

    interface LoadRemotePubliserAndPublisherTypeCallback : LoadRemotePublisherCallback {
        fun onRemotePublisherTypesLoaded(contentTypeList: List<PublisherType>, languageTyleList: List<PublisherType>)
    }

    interface LoadPublisherCallback : LoadRemotePublisherCallback, LoadCacheOrLocalPublisherCallback

    interface LoadCacheOrLocalPublisherCallback : LoadCallback {
        fun onCacheOrLocalPublishersLoaded(publisherList: List<Publisher>)
    }

    interface LoadRemotePublisherCallback : LoadCallback {
        fun onRemotePublishersLoaded(publisherList: List<Publisher>)
    }

    //TODO 公共LoadCallback接口，可以抽离公共
    interface LoadCallback {
        fun onDataNotAvailable()
    }
}