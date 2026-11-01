package sa.factory.app1289926.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.factory.app1289926.data.CommercialPremisesSketchAssignmentRepository
import sa.factory.app1289926.model.CommercialPremisesSketchAssignment
import sa.factory.app1289926.model.LightingPlanReferencePack
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CommercialPremisesSketchAssignmentViewModel(
    private val repository: CommercialPremisesSketchAssignmentRepository
) : ViewModel() {
    val catalog: StateFlow<List<LightingPlanReferencePack>> = repository.getAvailableCatalog()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeRequest: StateFlow<CommercialPremisesSketchAssignment?> = repository.getActiveRequest()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun submitNewOrder(request: CommercialPremisesSketchAssignment) {
        viewModelScope.launch {
            repository.submitRequest(request)
        }
    }
}
