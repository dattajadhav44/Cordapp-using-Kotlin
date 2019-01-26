package com.template

import net.corda.core.contracts.*
import net.corda.core.identity.Party
import net.corda.core.transactions.LedgerTransaction

// ************
// * Contract *
// ************
class IOUContract : Contract {
    companion object {
        const val ID = "com.template.IOUContract"
    }

    // Our Create command.
    class Create : CommandData

    override fun verify(tx: LedgerTransaction) {
        val command = tx.commands.requireSingleCommand<Create>()

        requireThat {
            // Constraints on the shape of the transaction.
            "No inputs should be consumed when issuing an IOU." using (tx.inputs.isEmpty())
            "There should be one output state of type IOUState." using (tx.outputs.size == 1)

            // IOU-specific constraints.
            val output = tx.outputsOfType<IOUState>().single()
            "The IOU's reg number must be less 10 digit." using (output.reg < 9999)
            "The IOU's name must be less than 10 char" using (output.name.length < 10)
            "The IOU's age must be less than 100" using (output.age < 100)
//            "The owner and the trader cannot be the same entity." using (output.issuer != output.owner)

//            // Constraints on the signers.
            val expectedSigners = listOf(output.issuer.owningKey, output.owner.owningKey)
            "There must be at least one signers." using (command.signers.toSet().size >= 1)
           "The trader and owner must be signers." using (command.signers.containsAll(expectedSigners))
        }
    }
}

// *********
// * State *
// *********
class IOUState(val reg: Int,
               val name: String,
               val email: String,
               val add: String,
               val age: Int,
               val issuer: Party,
               val owner: Party) : ContractState {
    override val participants get() = listOf(issuer, owner)
}
