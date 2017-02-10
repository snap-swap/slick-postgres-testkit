package com.snapswap.db

import org.scalatest.{AsyncWordSpec, Matchers, OneInstancePerTest}
import slick.jdbc.PostgresProfile.api._

class FlywayPreparerSpec
  extends AsyncWordSpec
    with Matchers
    with OneInstancePerTest {

  val db: Database = Postgres.nextDB

  "FlywayPreparer" should {
    "correct apply migration" in {
      val result = db.run(sql"""select count(*) from test_data""".as[Int].head)

      result map { count =>
        count shouldBe 1
      }
    }
  }
}