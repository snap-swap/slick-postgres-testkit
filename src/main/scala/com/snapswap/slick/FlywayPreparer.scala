package com.snapswap.slick

import javax.sql.DataSource

import com.opentable.db.postgres.embedded.DatabasePreparer
import org.flywaydb.core.Flyway

case class FlywayPreparerLocation(location: String, schema: Option[String] = None)

class FlywayPreparer(locations: Seq[FlywayPreparerLocation]) extends DatabasePreparer {
  private val flyways: Seq[(Flyway, Option[String])] =
    locations.groupBy(_.schema).map { case (schema, groupedLocations) =>
      val flyway = new Flyway
      flyway.setLocations(groupedLocations.map(_.location): _*)

      (flyway, schema)
    }.toSeq

  override def prepare(ds: DataSource): Unit = {
    flyways.foreach { case (flyway, schema) =>
      flyway.setDataSource(ds)
      schema.foreach(s => flyway.setSchemas(s))
      flyway.migrate
    }
  }
}

object FlywayPreparer {
  def apply(locations: (String, Option[String])*): FlywayPreparer = {
    val flywayLocation = locations.map { case (location, schema) =>
      FlywayPreparerLocation(location, schema)
    }

    new FlywayPreparer(flywayLocation)
  }
}