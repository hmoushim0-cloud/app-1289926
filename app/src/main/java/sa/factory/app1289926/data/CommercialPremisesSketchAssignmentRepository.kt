package sa.factory.app1289926.data

import sa.factory.app1289926.model.CommercialPremisesSketchAssignment
import sa.factory.app1289926.model.LightingPlanReferencePack
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface CommercialPremisesSketchAssignmentRepository {
    fun getAvailableCatalog(): Flow<List<LightingPlanReferencePack>>
    fun getActiveRequest(): Flow<CommercialPremisesSketchAssignment?>
    suspend fun submitRequest(request: CommercialPremisesSketchAssignment): Result<String>
}

class InMemoryCommercialPremisesSketchAssignmentRepository : CommercialPremisesSketchAssignmentRepository {
    private val _catalog = MutableStateFlow<List<LightingPlanReferencePack>>(listOf(
        LightingPlanReferencePack("1", "حزمة تحويل مخطط الأوتوكاد إلى سكيتش اب", "تحويل عناصر المخطط إلى موديل SketchUp مع ضبط القواعد الهندسية والتفاصيل المطلوبة للمحل التجاري وتسليم ملف سكيتش اب فقط", 5000L, "verified"),
        LightingPlanReferencePack("2", "حزمة تفصيل الواجهة والفراغات الداخلية", "إضافة تفاصيل تشغيلية للمداخل والواجهات والأقسام الداخلية لتصبح المخرجات جاهزة للتنسيق البصري في الرندر النهائي الذي يقوم به العميل", 9500L, "bolt")
    ))
    private val _active = MutableStateFlow<CommercialPremisesSketchAssignment?>(null)

    override fun getAvailableCatalog(): Flow<List<LightingPlanReferencePack>> = _catalog.asStateFlow()
    override fun getActiveRequest(): Flow<CommercialPremisesSketchAssignment?> = _active.asStateFlow()

    override suspend fun submitRequest(request: CommercialPremisesSketchAssignment): Result<String> {
        _active.value = request
        return Result.success(request.requestId)
    }
}
