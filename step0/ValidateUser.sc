def main(): Unit = {
  val name = sys.env.get("PARTY_NAME") match {
    case Some(value) => value
    case None =>
      System.err.println("Error: environment variable PARTY_NAME is missing!")
      sys.exit(1)  // stop execution with error code
  }

  val userName = sys.env.get("USER_NAME") match {
    case Some(value) => value
    case None =>
      System.err.println("Error: environment variable USER_NAME is missing!")
      sys.exit(1)
  }

  val existingParty = participant.parties.list(name)
  existingParty.foreach { result =>
    println(s"Party: ${result.party}")
    println("Participants:")
    result.participants.foreach { participantSync =>
      println(s"  Participant: ${participantSync.participant}")
      println("  Synchronizers:")
      participantSync.synchronizers.foreach { syncPermission =>
        println(s"    Synchronizer ID: ${syncPermission.synchronizerId}, Permission: ${syncPermission.permission}")
      }
    }
  }

  val user = participant.ledger_api.users.get(userName)
  println(user.party)
}

main()