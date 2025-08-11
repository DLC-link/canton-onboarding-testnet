def main(): Unit = {

  val name = sys.env.getOrElse("PARTY_NAME", "test")
  val userName = sys.env.getOrElse("USER_NAME", "testUser")

  // This call requires just access to the admin_api port of the node
  val party = participant.parties.enable(name = name)

  // This call requires a JWT token for the participantAdmin,
  // and access to the ledger_api port of the node
  val user = participant.ledger_api.users.create(
    id = userName,
    actAs = Set(party),
    readAs = Set(party),
    primaryParty = Some(party),
    participantAdmin = false,
    annotations = Map("description" -> "Attestor Party")
  )

  println(user)
}

main()
