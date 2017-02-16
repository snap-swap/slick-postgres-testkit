package com.snapswap.db

import javax.sql.DataSource

import com.opentable.db.postgres.embedded.PreparedDbProvider
import com.typesafe.config.{Config, ConfigFactory}
import slick.jdbc.JdbcBackend

import scala.collection.JavaConversions._


trait Postgres {
  val conf: Config = ConfigFactory.load()

  private val preparer = {
    val paths: Seq[(String, Option[String])] = conf.getStringList("flyway-preparer").map { value =>
      val pair: Array[String] = value.split(':')

      if (pair.length == 1) {
        (pair(0), None)
      } else {
        (pair(0), Option(pair.apply(1)))
      }
    }

    FlywayPreparer(paths: _*)
  }
  protected val provider: PreparedDbProvider = PreparedDbProvider.forPreparer(preparer)
  protected lazy val dataSource: DataSource = provider.createDataSource
}

object Postgres extends Postgres with JdbcBackend {

  def nextDB: Database =
    Database.forDataSource(provider.createDataSource)

  trait Fixture {
    val db: Database = Database.forDataSource(provider.createDataSource)
  }

}