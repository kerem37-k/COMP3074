
package ca.georgebrown3074.kerembas.roomcustomerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.georgebrown3074.kerembas.roomcustomerapp.data.AppDatabase
import ca.georgebrown3074.kerembas.roomcustomerapp.ui.CustomerApp
import ca.georgebrown3074.kerembas.roomcustomerapp.ui.CustomerViewModel
import ca.georgebrown3074.kerembas.roomcustomerapp.ui.CustomerViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(applicationContext)

        setContent {
            MaterialTheme {
                val viewModel: CustomerViewModel = viewModel(
                    factory = CustomerViewModelFactory(
                        database.customerDao()
                    )
                )

                CustomerApp(viewModel)
            }
        }
    }
}
