package workshop1024.com.xproject.model.publisher.source;

import java.util.List;

import workshop1024.com.xproject.model.publisher.Publisher;

/**
 * 发布者数据源接口，定义了关于发布者数据相关的处理接口
 */
public interface PublisherDataSource {
    /**
     * 获取指定类型的发布者
     *
     * @param type                   发布者指定的类型
     * @param loadPublishersCallback 加载发布者信息回调
     */
    void getPublishersByType(String type, LoadPublishersCallback loadPublishersCallback);

    /**
     * 获取指定语言的发布者
     *
     * @param language               发布者指定的语言
     * @param loadPublishersCallback 加载发布者信息回调
     */
    void getPublishersByLanguage(String language, LoadPublishersCallback loadPublishersCallback);

    /**
     * 保存发布者信息
     *
     * @param publisher 要保存的发布者信息
     */
    void savePublisher(Publisher publisher);

    /**
     * 订阅指定id的发布者
     *
     * @param id 要订阅的发布者id
     */
    void subscribePublisherById(String id);

    /**
     * 取消订阅指定id发布者
     *
     * @param id 要取消订阅的发布者id
     */
    void unSubscribePublisherById(String id);

    /**
     * 刷新缓冲发布者信息
     */
    void refreshPublishers(String type);


    /**
     * 获取发布者信息回调
     */
    interface LoadPublishersCallback {
        /**
         * 发布者信息加载完毕
         *
         * @param publisherList 加载返回的发布者信息
         */
        void onPublishersLoaded(List<Publisher> publisherList);

        /**
         * 没有有效的发布者信息
         */
        void onDataNotAvailable();
    }
}