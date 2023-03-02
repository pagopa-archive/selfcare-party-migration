package it.pagopa.selfcare.party.migration.connector.model

/**
 * Represents the party relationship state
 *
*/


sealed trait RelationshipState

object RelationshipState {
  import spray.json._

  case object PENDING extends RelationshipState
  case object ACTIVE extends RelationshipState
  case object SUSPENDED extends RelationshipState
  case object DELETED extends RelationshipState
  case object REJECTED extends RelationshipState
  case object TOBEVALIDATED extends RelationshipState

  implicit object RelationshipStateFormat extends RootJsonFormat[RelationshipState] {
      def write(obj: RelationshipState): JsValue =
        obj match {
           case PENDING => JsString("PENDING")
           case ACTIVE => JsString("ACTIVE")
           case SUSPENDED => JsString("SUSPENDED")
           case DELETED => JsString("DELETED")
           case REJECTED => JsString("REJECTED")
           case TOBEVALIDATED => JsString("TOBEVALIDATED")
        }

      def read(json: JsValue): RelationshipState =
        json match {
           case JsString("PENDING") => PENDING
           case JsString("ACTIVE") => ACTIVE
           case JsString("SUSPENDED") => SUSPENDED
           case JsString("DELETED") => DELETED
           case JsString("REJECTED") => REJECTED
           case JsString("TOBEVALIDATED") => TOBEVALIDATED
          case unrecognized     => deserializationError(s"RelationshipState serialization error ${unrecognized.toString}")
        }
  }

  def fromValue(value: String): Either[Throwable, RelationshipState] =
    value match {
       case "PENDING" => Right(PENDING)
       case "ACTIVE" => Right(ACTIVE)
       case "SUSPENDED" => Right(SUSPENDED)
       case "DELETED" => Right(DELETED)
       case "REJECTED" => Right(REJECTED)
       case "TOBEVALIDATED" => Right(TOBEVALIDATED)
       case other => Left(new RuntimeException(s"Unable to decode value $other"))
    }

}

