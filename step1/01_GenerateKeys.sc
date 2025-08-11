import com.google.protobuf.ByteString

def main(): Unit = {
  // This creates a signing key that will only be used for namespace management. In the decentralized 
  // party context, the namespace is the unique identifier for the party’s topology and governance.
  val namespaceKey = participant.keys.secret.generate_signing_key(
    "cbtc-network-namespace",
    SigningKeyUsage.NamespaceOnly
  )

  // The namespace is derived from the fingerprint (hash) of your namespace signing key.
  val namespace = Namespace(namespaceKey.fingerprint)

  // Canton uses Synchronizers as trust domains for transaction coordination.
  // We’ll register this namespace in the global coordination network.
  val synchronizerId = participant.synchronizers.id_of(
    com.digitalasset.canton.SynchronizerAlias.tryCreate("global")
  )

  // Propose a delegation for the namespace
  participant.topology.namespace_delegations.propose_delegation(
    namespace,
    namespaceKey,
    DelegationRestriction.CanSignAllMappings,
    store = synchronizerId
  )

  // Generate a DAML key for the decentralized party
  val damlKey = participant.keys.secret.generate_signing_key(
    "cbtc-network-daml-transactions",
    SigningKeyUsage.ProtocolOnly
  )

  utils.write_to_file(
    Seq(namespaceKey.toProtoV30, damlKey.toProtoV30),
    "output/attestor-public-keys.bin"
  )
  utils.write_to_file(ByteString.copyFromUtf8(participant.id.toProtoPrimitive), "output/participant-id.bin")
  println("Keys generated and saved to output/attestor-public-keys.bin and output/participant-id.bin")
}

main()
