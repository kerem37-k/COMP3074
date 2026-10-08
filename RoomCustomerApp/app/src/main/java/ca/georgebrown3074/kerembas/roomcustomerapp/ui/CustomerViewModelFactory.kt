package ca.georgebrown3074.kerembas.roomcustomerapp.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ca.georgebrown3074.kerembas.roomcustomerapp.data.CustomerDao

class CustomerViewModelFactory(
    private val customerDao: CustomerDao
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        if (modelClass.isAssignableFrom(CustomerViewModel::class.java)) {
            return CustomerViewModel(customerDao) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
