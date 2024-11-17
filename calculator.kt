import java.util.Scanner

// Data class to hold operands and operation
data class Calculation(val operand1: Double, val operand2: Double, val operation: String)

// Calculator class to encapsulate arithmetic logic
class Calculator {
    // Perform calculation based on the operation
    fun calculate(calc: Calculation): Double {
        return when (calc.operation) {
            "+" -> calc.operand1 + calc.operand2
            "-" -> calc.operand1 - calc.operand2
            "*" -> calc.operand1 * calc.operand2
            "/" -> {
                if (calc.operand2 == 0.0) {
                    throw IllegalArgumentException("Division by zero is not allowed.")
                }
                calc.operand1 / calc.operand2
            }
            "%" -> calc.operand1 % calc.operand2
            else -> throw IllegalArgumentException("Invalid operation. Supported operations are +, -, *, /, %.")
        }
    }

    // Display the list of supported operations
    fun displayOperations() {
        println("Supported operations:")
        println("1. Addition (+)")
        println("2. Subtraction (-)")
        println("3. Multiplication (*)")
        println("4. Division (/)")
        println("5. Modulo (%)")
    }
}

// Utility object for input handling
object InputHandler {
    private val scanner = Scanner(System.`in`)

    // Get a double input from the user
    fun getDouble(prompt: String): Double {
        while (true) {
            print(prompt)
            val input = scanner.nextLine()
            try {
                return input.toDouble()
            } catch (e: NumberFormatException) {
                println("Invalid input. Please enter a valid number.")
            }
        }
    }

    // Get an operation input from the user
    fun getOperation(): String {
        while (true) {
            print("Enter an operation (+, -, *, /, %): ")
            val input = scanner.nextLine()
            if (input in listOf("+", "-", "*", "/", "%")) {
                return input
            } else {
                println("Invalid operation. Please input something valid.")
            }
        }
    }

    // Get a yes/no response from the user
    fun getYesNo(prompt: String): Boolean {
        while (true) {
            print(prompt)
            val input = scanner.nextLine().lowercase()
            if (input in listOf("yes", "no")) {
                return input == "yes"
            } else {
                println("Invalid response. Please enter 'yes' or 'no'.")
            }
        }
    }
}

// Main function
fun main() {
    val calculator = Calculator()
    var continueCalculations = true

    println("Good afternoon, welcome to the Kotlin Calculator")
    println("Calculator designed by: Landon Smith")
    println("This calculator supports basic arithmetic operations with enhanced error handling.")

    // Main calculation loop
    while (continueCalculations) {
        // Display supported operations
        calculator.displayOperations()

        // Get user inputs
        val num1 = InputHandler.getDouble("Enter the first number: ")
        val operation = InputHandler.getOperation()
        val num2 = InputHandler.getDouble("Enter the second number: ")

        // Perform the calculation and display the result
        try {
            val calculation = Calculation(num1, num2, operation)
            val result = calculator.calculate(calculation)
            println("Result: $result")
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }

        // Ask if the user wants to perform another calculation
        continueCalculations = InputHandler.getYesNo("Do you want to perform another calculation? (yes/no): ")
    }

    println("Thank you for using the Kotlin Calculator. Goodbye!")
}
