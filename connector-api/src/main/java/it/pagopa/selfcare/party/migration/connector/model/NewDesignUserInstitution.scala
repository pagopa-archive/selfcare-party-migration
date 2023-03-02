package it.pagopa.selfcare.party.migration.connector.model

import java.time.OffsetDateTime

/**
 * @param institutionId  for example: ''null''
 * @param products  for example: ''null''
 * @param createdAt  for example: ''null''
*/
final case class NewDesignUserInstitution (
  institutionId: String,
  products: Seq[NewDesignUserInstitutionProduct],
  createdAt: OffsetDateTime
)


