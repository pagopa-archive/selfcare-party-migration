package it.pagopa.selfcare.party.migration.connector.model

import java.time.OffsetDateTime

/**
 * @param relationshipId  for example: ''null''
 * @param productRole  for example: ''null''
 * @param status  for example: ''null''
 * @param createdAt  for example: ''null''
 * @param updatedAt  for example: ''null''
*/
final case class NewDesignUserInstitutionProductRole (
  relationshipId: String,
  productRole: String,
  status: RelationshipState,
  createdAt: OffsetDateTime,
  updatedAt: Option[OffsetDateTime] = None
)


