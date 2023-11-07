
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App(modifier: Modifier = Modifier) {

  var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

  Surface(modifier) {
    if (shouldShowOnboarding) {
      OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
    } else {
      Greetings()
    }
  }
}

@Composable
fun OnboardingScreen(
  onContinueClicked: () -> Unit,
  modifier: Modifier = Modifier
) {

  Column(
    modifier = modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text("Welcome to the Basics Codelab!")
    Button(
      modifier = Modifier.padding(vertical = 24.dp),
      onClick = onContinueClicked
    ) {
      Text("Continue")
    }
  }
}

@Composable
fun Greetings(
  modifier: Modifier = Modifier,
  names: List<String> = List(500) { index -> "Greetings $index"}
) {
  LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
    items(items=names) { name ->
      Greeting(name)
    }
  }
}

@Composable
private fun Greeting(name: String) {

  val expanded = remember { mutableStateOf(false) }

  val extraPadding = if (expanded.value) 48.dp else 0.dp

  Surface(
    color = MaterialTheme.colors.primary,
    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
  ) {
    Row(modifier = Modifier.padding(24.dp)) {
      Column(modifier = Modifier
        .weight(1f)
        .padding(bottom = extraPadding)
      ) {
        Text(text = "Hello, ")
        Text(text = name)
      }
      Button(
        onClick = { expanded.value = !expanded.value },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
      ) {
        Text(text = if (expanded.value) "Show less" else "Show more")
      }
    }
  }
}
