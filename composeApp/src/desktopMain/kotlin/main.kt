import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.religada.calculator.App


/**
 * ATTENTION: To run desktop is necessary to run the next command:
 *  - MacOS: ./gradlew :composeApp:run
 *  - Windows: .\gradlew :composeApp:run
 *  Source: https://stackoverflow.com/a/77643665
 */
fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CalculatorKmp",
    ) {
        App()
    }
}