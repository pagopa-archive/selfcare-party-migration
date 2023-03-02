package it.pagopa.selfcare.party.migration.connector.model

import java.time.OffsetDateTime

/**
 * @param productId  for example: ''null''
 * @param tokenId  for example: ''null''
 * @param contract  for example: ''null''
 * @param role  for example: ''null''
 * @param productRoles  for example: ''null''
 * @param env  for example: ''null''
 * @param createdAt  for example: ''null''
 * @param updatedAt  for example: ''null''
*/
final case class NewDesignUserInstitutionProduct (
  productId: String,
  tokenId: Option[String] = None,
  contract: Option[String] = None,
  role: PartyRole,
  productRoles: Seq[NewDesignUserInstitutionProductRole],
  env: String,
  createdAt: OffsetDateTime,
  updatedAt: Option[OffsetDateTime] = None
)


