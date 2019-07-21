package workshop1024.com.xproject.model.filter.source

import android.os.Handler

import java.util.ArrayList
import java.util.LinkedHashMap

import workshop1024.com.xproject.model.filter.Filter

class FilterRepository private constructor() : FilterDataSource {

    override fun getFilters(loadFiltersCallback: FilterDataSource.LoadFiltersCallback) {
        val handler = Handler()
        handler.postDelayed({
            val filterList = ArrayList(FILTERS_SERVICE_DATA!!.values)
            loadFiltersCallback.onPublishersLoaded(filterList)
        }, SERVICE_LATENCY_IN_MILLIS.toLong())
    }

    override fun addFilterByName(filterName: String) {
        val handler = Handler()
        handler.postDelayed({
            val filter = Filter("f101", filterName)
            FILTERS_SERVICE_DATA!![filter.filterId!!] = filter
        }, SERVICE_LATENCY_IN_MILLIS.toLong())

    }

    override fun deleteFilterById(filterId: String) {

        val handler = Handler()
        handler.postDelayed({ FILTERS_SERVICE_DATA!!.remove(filterId) }, SERVICE_LATENCY_IN_MILLIS.toLong())

    }

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS = 1000

        private var FILTERS_SERVICE_DATA: MutableMap<String, Filter>? = null

        private var INSTANCE: FilterRepository? = null

        init {
            //TODO 如何本地提供Mock环境
            FILTERS_SERVICE_DATA = LinkedHashMap(2)

            addFilter("f001", "china")
            addFilter("f002", "android")
            addFilter("f003", "usa")
            addFilter("f004", "news")
            addFilter("f005", "rusia")
            addFilter("f006", "fight")
            addFilter("f007", "boate")
            addFilter("f008", "car")
            addFilter("f009", "bycycle")
        }

        private fun addFilter(filterId: String, filterName: String) {
            val filter = Filter(filterId, filterName)
            FILTERS_SERVICE_DATA!![filter.filterId!!] = filter
        }

        val instance: FilterRepository?
            get() {
                if (INSTANCE == null) {
                    INSTANCE = FilterRepository()
                }

                return INSTANCE
            }
    }
}