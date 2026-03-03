package com.joselpadi.uala_challenge


import com.joselpadi.uala_challenge.domain.logic.SearchService
import com.joselpadi.uala_challenge.domain.model.City
import com.joselpadi.uala_challenge.domain.model.Coord
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
class SearchServiceUnitTest {

    private lateinit var cities: List<City>
    @Before
    fun init(){
        cities = listOf(
            City("","aaaaab",0, Coord(1.1,1.1)),//0
            City("","aaab",0, Coord(1.1, 1.1)),  //1
            City("","aac",0, Coord(1.1,1.1)),   //2
            City("","ad",0, Coord(1.1,1.1)),    //3
            City("","ae",0, Coord(1.1,1.1)),    //4
            City("","af",0, Coord(1.1,1.1)),    //5
            City("","ag",0, Coord(1.1,1.1)),    //6
            City("","ah",0, Coord(1.1,1.1)),    //7
            City("","b",0, Coord(1.1,1.1)),     //8
            City("","c",0, Coord(1.1,1.1)),     //9
            City("","d",0, Coord(1.1,1.1)),     //10
            City("","Ea",0, Coord(1.1,1.1)),    //11
            City("","Eb",0, Coord(1.1,1.1)),    //12
            City("","Ec",0, Coord(1.1,1.1)),    //13
            City("","Ed",0, Coord(1.1,1.1)),    //14
            City("","Ee",0, Coord(1.1,1.1)),    //15
            City("","f",0, Coord(1.1,1.1)),     //16
            City("","fa",0, Coord(1.1,1.1)),    //17
            City("","faa",0, Coord(1.1,1.1)),   //18
            City("","faaa",0, Coord(1.1,1.1)),  //19
            City("","fbaa",0, Coord(1.1,1.1)),  //20
            City("","fbb",0, Coord(1.1,1.1)),   //21
            City("","fc",0, Coord(1.1,1.1)),    //22
            City("","fe",0, Coord(1.1,1.1)),    //23
            City("","ff",0, Coord(1.1,1.1)),    //24
            City("","zaaa",0, Coord(1.1,1.1)),  //25
            City("","zaa",0, Coord(1.1,1.1)),   //26
            City("","za",0, Coord(1.1,1.1)),    //27
            City("","zzb",0, Coord(1.1,1.1)),   //28
            City("","zzb",0, Coord(1.1,1.1)),   //29
            City("","zzb",0, Coord(1.1,1.1)),   //30
            City("","zzb",0, Coord(1.1,1.1)),   //31
            City("","zzb",0, Coord(1.1,1.1)),   //32
            City("","zzz",0, Coord(1.1,1.1))    //33
        ).sortedBy { it.name.lowercase()  }
    }

    @Test
    fun `test searchMinPivote not found`() {

        val result = SearchService.searchMinPivote("bbb", 0 , cities.size-1, cities)
        Assert.assertEquals(0, result)
    }

    @Test
    fun `test searchMinPivote empty string`() {
        val result = SearchService.searchMinPivote("", 0 , cities.size-1, cities)
        Assert.assertEquals(0, result)
    }
    @Test
    fun `test searchMinPivote min pivote lower and upper case`() {
        val textUper="eb"
        val textLower= "zzb"
        val uper = SearchService.searchMinPivote(textUper, 0 , cities.size-1, cities)
        val lower = SearchService.searchMinPivote(textLower, 0 , cities.size-1, cities)
        assert(cities[uper].name.lowercase().startsWith(textUper) && !cities[uper-1].name.lowercase().startsWith(textUper) )
        assert(cities[lower].name.lowercase().startsWith(textLower) && !cities[lower-1].name.lowercase().startsWith(textLower) )
    }
    @Test
    fun `test searchMinPivote min frontier`() {
        val result = SearchService.searchMinPivote("aaaaab", 0 , cities.size-1, cities)
        Assert.assertEquals(0, result)
    }


    @Test
    fun `test searchMaxPivote not found`() {
        val result = SearchService.searchMaxPivote("bbb", 0 , cities.size-1, cities)
        Assert.assertEquals(-1, result)
    }

    @Test
    fun `test searchMaxPivote empty string`() {
        val result = SearchService.searchMaxPivote("", 0 , cities.size-1, cities)
        assertEquals(cities.size-1, result) // "fa" está en la posición 17
    }
    @Test
    fun `test searchMaxPivote max pivote lower and upper case`() {
        val textUper = "eb"
        val textLower = "zzb"
        val uper = SearchService.searchMaxPivote(textUper, 0 , cities.size-1, cities)
        val lower = SearchService.searchMaxPivote(textLower, 0 , cities.size-1, cities)
        assert(cities[uper].name.lowercase().startsWith(textUper) && !cities[uper+1].name.lowercase().startsWith(textUper) )
        assert(cities[lower].name.lowercase().startsWith(textLower) && !cities[lower+1].name.lowercase().startsWith(textLower) )

    }
    @Test
    fun `test searchMaxPivote min frontier`() {
        val result = SearchService.searchMaxPivote("zzz", 0 , cities.size-1, cities)
        assertEquals(cities.size-1, result)
    }
    @Test
    fun `test filterList not found`() {
        val result1   = SearchService.filterListfun("dderew", cities)
        assert(result1.first==0 && result1.second==-1)
    }
    @Test
    fun `test filterList notFound char by char and return to empty string`() {
        val st0 = ""
        val st1 = "f"
        val st2 = "fb"
        val st3 = "fbb"
        val st4 = "fbbb"
        val st5 = "fbbbe"
        val result1   = SearchService.filterListfun(st1, cities)
        val result2   = SearchService.filterListfun(st2, cities)
        val result3   = SearchService.filterListfun(st3, cities)
        val result4   = SearchService.filterListfun(st4, cities)
        val result5   = SearchService.filterListfun(st5, cities)
        val return4   = SearchService.filterListfun(st4, cities)
        val return3   = SearchService.filterListfun(st3, cities)
        val return2   = SearchService.filterListfun(st2, cities)
        val return1   = SearchService.filterListfun(st1, cities)
        val return0   = SearchService.filterListfun(st0, cities)


        assert(SearchService.searchMinPivote(st1,0,cities.size-1, cities) == result1.first &&
                SearchService.searchMaxPivote(st1,0,cities.size-1, cities) == result1.second)
        assert(SearchService.searchMinPivote(st2,0,cities.size-1, cities) == result2.first &&
                SearchService.searchMaxPivote(st2,0,cities.size-1, cities) == result2.second)
        assert(SearchService.searchMinPivote(st3,0,cities.size-1, cities) == result3.first &&
                SearchService.searchMaxPivote(st3,0,cities.size-1, cities) == result3.second)
        assert(SearchService.searchMinPivote(st4,0,cities.size-1, cities) == result4.first &&
                SearchService.searchMaxPivote(st4,0,cities.size-1, cities) == result4.second)
        assert(SearchService.searchMinPivote(st5,0,cities.size-1, cities) == result5.first &&
                SearchService.searchMaxPivote(st5,0,cities.size-1, cities) == result5.second)

        assert(SearchService.searchMinPivote(st4,0,cities.size-1, cities) == return4.first &&
                SearchService.searchMaxPivote(st4,0,cities.size-1, cities) == return4.second)
        assert(SearchService.searchMinPivote(st3,0,cities.size-1, cities) == return3.first &&
                SearchService.searchMaxPivote(st3,0,cities.size-1, cities) == return3.second)
        assert(SearchService.searchMinPivote(st2,0,cities.size-1, cities) == return2.first &&
                SearchService.searchMaxPivote(st2,0,cities.size-1, cities) == return2.second)
        assert(SearchService.searchMinPivote(st1,0,cities.size-1, cities) == return1.first &&
                SearchService.searchMaxPivote(st1,0,cities.size-1, cities) == return1.second)
        assert(SearchService.searchMinPivote(st0,0,cities.size-1, cities) == return0.first &&
                SearchService.searchMaxPivote(st0,0,cities.size-1, cities) == return0.second)

    }



}

