package it.pagopa.selfcare.party.migration.connector.model

/**
 * Represents the generic available role types for the relationship
 *
*/


sealed trait PartyRole

object PartyRole {
  import spray.json._

  case object MANAGER extends PartyRole
  case object DELEGATE extends PartyRole
  case object SUB_DELEGATE extends PartyRole
  case object OPERATOR extends PartyRole

  implicit object PartyRoleFormat extends RootJsonFormat[PartyRole] {
      def write(obj: PartyRole): JsValue =
        obj match {
           case MANAGER => JsString("MANAGER")
           case DELEGATE => JsString("DELEGATE")
           case SUB_DELEGATE => JsString("SUB_DELEGATE")
           case OPERATOR => JsString("OPERATOR")
        }

      def read(json: JsValue): PartyRole =
        json match {
           case JsString("MANAGER") => MANAGER
           case JsString("DELEGATE") => DELEGATE
           case JsString("SUB_DELEGATE") => SUB_DELEGATE
           case JsString("OPERATOR") => OPERATOR
          case unrecognized     => deserializationError(s"PartyRole serialization error ${unrecognized.toString}")
        }
  }

  def fromValue(value: String): Either[Throwable, PartyRole] =
    value match {
       case "MANAGER" => Right(MANAGER)
       case "DELEGATE" => Right(DELEGATE)
       case "SUB_DELEGATE" => Right(SUB_DELEGATE)
       case "OPERATOR" => Right(OPERATOR)
       case other => Left(new RuntimeException(s"Unable to decode value $other"))
    }

}

