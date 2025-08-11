def main(): Unit = {

  val name = sys.env.getOrElse("PARTY_NAME", "test")
  val userName = sys.env.getOrElse("USER_NAME", "testUser")

  val user = participant.ledger_api.users.get(userName)
  println(user.party)
}

main()