package com.perfect.sirma.data

import io.ktor.config.ApplicationConfig
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.net.URI
import java.sql.Connection

object DatabaseHelper {


    fun init(config: ApplicationConfig, dropIfExists: Boolean = true) {
        connectToDatabase(config.property("uri").getString())

        transaction {
            if (dropIfExists) {
                deleteSchema()
                flushCache()
            }

            createSchema()
            flushCache()

            insertBaseData()
            commit()
        }

    }

    fun createSchema() {
        SchemaUtils.create(*ALL_MODELS)
    }

    fun deleteSchema() {
        SchemaUtils.drop(*ALL_MODELS)
    }

    fun connectToDatabase(uri: String) {

        val connectionStringUri = URI.create(uri)
        val database = Database.connect(
            "jdbc:${connectionStringUri.scheme}://${connectionStringUri.host}:${connectionStringUri.port}${connectionStringUri.path}",
            user = connectionStringUri.userInfo.split(":")[0],
            password = connectionStringUri.userInfo.split(":")[1],
            driver = "org.postgresql.Driver",
            setupConnection = { connection: Connection -> connection.autoCommit = false; }
        )
    }


}