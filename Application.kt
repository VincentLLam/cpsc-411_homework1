package Projects.cpsc411.homework1

import com.google.gson.Gson
import csuf.cpsc411.Dao.Database
import csuf.cpsc411.Dao.claim.Claim
import csuf.cpsc411.Dao.claim.ClaimDao
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.utils.io.*
import java.util.*
fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
@kotlin.jvm.JvmOverloads

fun Application.module(testing: Boolean = false) {
    routing {
        get("/ClaimService/getAll") {
            val cList = ClaimDao().getAll()
            println("Number of claims: ${cList.size}")

            val responseJson = Gson().toJson(cList)
            call.respondText(
                responseJson, status = HttpStatusCode.OK, contentType =
                ContentType.Application.Json
            )
        }
        post("/post") {
            val contType = call.request.contentType()
            val data = call.request.receiveChannel()
            val dataLength = data.availableForRead
            var output = ByteArray(dataLength)
            data.readAvailable(output)
            output

            val str = String(output)
            val gsonString = Gson().fromJson(str, Claim::class.java)
            println(gsonString)
            val claimObj = Claim(
                UUID.randomUUID().toString(), gsonString.title,
                gsonString.date, false
            )
            val dao = ClaimDao().addClaim(claimObj)
            println("HTTP is using POST method with /post ${contType}")
            call.respondText("The POST request was successfully processed.", status=
            HttpStatusCode.OK, contentType = ContentType.Text.Plain)
        }
    }
}