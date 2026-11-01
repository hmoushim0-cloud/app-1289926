package sa.factory.app1289926.model

import kotlinx.serialization.Serializable

@Serializable
data class LightingPlanReferencePack(
    val id: String,
    val title: String,
    val description: String,
    val priceMinorUnits: Long,
    val iconVector: String = "verified"
)

@Serializable
data class CommercialPremisesSketchAssignment(
    val requestId: String,
    val items: List<LightingPlanReferencePack>,
    val customerPhone: String,
    val totalAmountMinorUnits: Long,
    val status: CommercialPremisesSketchAssignmentStatus = CommercialPremisesSketchAssignmentStatus.PENDING,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

enum class CommercialPremisesSketchAssignmentStatus {
    PENDING,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
