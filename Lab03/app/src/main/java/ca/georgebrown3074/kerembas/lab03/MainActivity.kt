package ca.georgebrown3074.kerembas.lab03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.georgebrown3074.kerembas.lab03.ui.theme.Lab03Theme

data class Message(
    val name: String,
    val text: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            Lab03Theme {
                MessageList()
            }
        }
    }
}

@Composable
fun MessageCard(message: Message) {

    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(70.dp)
                .border(
                    width = 3.dp,
                    color = Color.Red,
                    shape = CircleShape
                )
                .padding(5.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {

            Text(
                text = message.name,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Surface(
                modifier = Modifier.padding(top = 8.dp),
                shape = RoundedCornerShape(20.dp),
                shadowElevation = 3.dp
            ) {

                Text(
                    text = message.text,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 8.dp
                    )
                )
            }
        }
    }
}

@Composable
fun MessageList() {

    val messages = listOf(
        Message("Joe", "Hi!"),
        Message("Jim", "How are you?"),
        Message("Joe", "Test..1..2...3"),
        Message("Joe", "I hate coding!!!"),
        Message("Joe", "Hi!"),
        Message("Jim", "How are you?"),
        Message("Joe", "Test..1..2...3"),
        Message("Joe", "I hate coding!!!")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top=30.dp)
    ) {

        items(messages) { message ->

            MessageCard(message)

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MessagePreview() {

    Lab03Theme {
        MessageList()
    }
}