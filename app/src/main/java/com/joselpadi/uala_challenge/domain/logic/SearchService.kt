package com.joselpadi.uala_challenge.domain.logic

import android.util.Log
import com.joselpadi.uala_challenge.domain.model.City
import kotlin.text.isEmpty
import kotlin.text.lowercase
import kotlin.text.startsWith

object SearchService {
    /**
     * return a pair with init and finish position of the array that is filtered.
     */
    fun filterList(newTextFilter: String, list: List<City>): Pair<Int, Int> {
        val startTime = System.currentTimeMillis()
        val result = filterListfun(newTextFilter, list)
        val endTime = System.currentTimeMillis()
        Log.d("timespend", result.toString())
        Log.d("timeSpend", "time: ${endTime - startTime}  -- string:${newTextFilter}")
        return result

    }

    internal fun filterListfun(
        textFilter: String, list: List<City>
    ): Pair<Int, Int> {
        var filteredEnd = list.size - 1
        var filteredStart = 0
        val lowerCaseTextFilter = textFilter.lowercase()
        if (lowerCaseTextFilter.isEmpty()) {
            return Pair(0, list.size - 1)
        } else {
            val pair = Pair(
                searchMinPivote(lowerCaseTextFilter, filteredStart, filteredEnd, list),
                searchMaxPivote(lowerCaseTextFilter, filteredStart, filteredEnd, list)
            )
            return pair
        }
    }


    /**
     * textFilter is always lowercase
     */
    internal fun searchMinPivote(textFilter: String,start:Int, end:Int, list: List<City>):Int {
        if( textFilter.isEmpty()) return start
        val mid = (start + end) / 2
        if (start >= end) return 0
        if (mid == start || mid == end){
            if (list[start].name.startsWith(textFilter, true)) {
                return start
            } else  if (list[end].name.startsWith(textFilter, true)) {
                return end
            }else
                return 0
        } else
        if (list[mid].name.startsWith(textFilter, true)) {
            if (mid!=0 && list[mid - 1].name.startsWith(textFilter, true)) {
                return searchMinPivote(textFilter, start, mid, list)
            } else return mid
        } else if (list[mid].name.lowercase() > textFilter)
            return searchMinPivote(textFilter, start, mid, list)
        else return searchMinPivote(textFilter, mid, end, list)
    }

    /**
     * textFilter is always lowercase
     */
    internal fun searchMaxPivote(textFilter: String,start:Int, end:Int, list: List<City>):Int{
        if( textFilter.isEmpty()) return end
        val mid = (start + end) / 2
        if (start >= end) return -1
        if (mid == start || mid == end){
            if (list[start].name.startsWith(textFilter, true)) {
                return start
            } else  if (list[end].name.startsWith(textFilter, true)) {
                return end
            }else
                return -1
        } else
        if (list[mid].name.startsWith(textFilter,true)){
            if (mid<list.size-1 && list[mid+1].name.startsWith(textFilter,true)){
                return searchMaxPivote(textFilter, mid, end, list)
            } else return mid
        } else  if (list[mid].name.lowercase()>textFilter)
            return searchMaxPivote(textFilter, start, mid, list)
        else return searchMaxPivote(textFilter, mid, end, list)
    }
}