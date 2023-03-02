package it.pagopa.selfcare.party.migration.connector.model

/**
 * @param abiCode ABI Code for example: ''null''
 * @param businessRegisterNumber ID Registration Number on Business Register for example: ''null''
 * @param legalRegisterName Chairman name on Business Register for example: ''null''
 * @param legalRegisterNumber Chairman ID on Business Register for example: ''null''
 * @param vatNumberGroup true when vat number identify a group for example: ''null''
*/
final case class PaymentServiceProvider (
  abiCode: Option[String] = None,
  businessRegisterNumber: Option[String] = None,
  legalRegisterName: Option[String] = None,
  legalRegisterNumber: Option[String] = None,
  vatNumberGroup: Option[Boolean] = None
)


