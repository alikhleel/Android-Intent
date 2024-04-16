package com.alikhalil.androidintent

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.alikhalil.androidintent.ui.theme.AndroidIntentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidIntentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Button(onClick = { openDeepLink(applicationContext) }) {
                        Text("Open Deep Link")
                    }
                }
            }
        }
    }


    private fun openDeepLink(applicationContext: Context) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://example.com/123")
        )
        val deepLinkPendingIntent: PendingIntent? =
            TaskStackBuilder.create(applicationContext).run {
                addNextIntentWithParentStack(intent)
                getPendingIntent(
                    3,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            }
        deepLinkPendingIntent?.send()
    }

    @Composable
    fun SendEmail() {
        var email by remember { mutableStateOf("example@example.com") }
        var subject by remember { mutableStateOf("This is the subject") }
        var body by remember { mutableStateOf("This is the body") }

        Column {

            Text(text = "Send Email")
            Row {
                Text("To: ")
                TextField(value = email, onValueChange = { email = it })
            }
            Row {
                Text("Subject: ")
                TextField(value = subject, onValueChange = { subject = it })
            }
            Row {
                Text("Body: ")
                TextField(value = body, onValueChange = { body = it })
            }

            Button(onClick = {
                sendEmail(
                    addresses = arrayOf(email),
                    subject = subject,
                    body = body,
                )
            }) {
                Text("Send Email")
            }
        }
    }

    private fun sendEmail(addresses: Array<String>, subject: String, body: String) {
        try {
            val intent = Intent(Intent.ACTION_SEND).apply {
//                data = Uri.parse("mailto:")
                type = "*/*"
                putExtra(Intent.EXTRA_EMAIL, addresses)
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, body)
            }
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}


