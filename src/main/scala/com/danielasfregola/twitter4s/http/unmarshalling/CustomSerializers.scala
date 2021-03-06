package com.danielasfregola.twitter4s.http.unmarshalling

import java.time.LocalDate

import com.danielasfregola.twitter4s.entities.enums.DisconnectionCode
import com.danielasfregola.twitter4s.entities.enums.DisconnectionCode.DisconnectionCode
import org.json4s.CustomSerializer
import org.json4s.JsonAST.{JInt, JNull, JString}

object CustomSerializers {

  val all = List(LocalDateSerializer, DisconnectionCodeSerializer)

}

case object LocalDateSerializer extends CustomSerializer[LocalDate](format =>
  ({
    case JString(dateString) =>
      dateString.split("-") match {
        case Array(year, month, date) => LocalDate.of(year.toInt, month.toInt, date.toInt)
        case _ => null
      }
    case JNull => null
  },
  {
    case date: LocalDate => JString(date.toString)
  }))

case object DisconnectionCodeSerializer extends CustomSerializer[DisconnectionCode](format =>
  ({
    case JInt(n) => DisconnectionCode(n.toInt)
    case JNull => null
  },
  {
    case code: DisconnectionCode => JInt(code.id)
  }))
