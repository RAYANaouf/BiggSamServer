package com.example

import com.example.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.ktorm.database.Database
import kotlin.test.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            var database = Database.connect(
                url = "jdbc:postgresql://ep-red-cherry-13821704.us-west-2.aws.neon.tech/neondb",
                driver = "org.postgresql.Driver",
                user = "RAYANaouf",
                password = "whqo65NdXMze"
            )

            configureRouting(database = database)
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World!", bodyAsText())
        }
    }
}
