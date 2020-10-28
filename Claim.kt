package Projects.cpsc411.homework1.claim

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

data class Claim (var id : String?, var title : String?, var date : String?, var isSolved : Boolean = false)

fun main() {
    val cObj = Claim("testID", "testTitle", "testDate", false)
    val jsonStr = Gson().toJson(cObj)
    println("The converted JSON string: ${jsonStr}")

    var cList : MutableList<Claim> = mutableListOf()
    cList.add(cObj)

    cList.add(Claim("testID2", "testTitle2", "testDate2", true))
    val listJsonString = Gson().toJson(cList)
    of Claim objects
    println("${listJsonString}")

    val claimListType : Type = object : TypeToken<Claim>(){}.type
    val listJsonStr = Gson()

    var cObj1 : Claim

    cObj1 = Gson().fromJson(jsonStr, Claim :: class.java)

    println("${cObj1.toString()}")
}