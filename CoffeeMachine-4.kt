package machine

class CoffeeMachine(var water: Int, var milk: Int, var beans: Int, var cups: Int, var money: Int)

enum class CoffeeMachineStates {
    ACTION,
    BUY
}

/* Initial coffee machine amounts */

const val INITIAL_WATER = 400
const val INITIAL_MILK = 540
const val INITIAL_BEANS = 120
const val INITIAL_CUPS = 9
const val INITIAL_MONEY = 550

/* Global variable (to avoid passing it to every function) */

val coffeeMachine = CoffeeMachine(INITIAL_WATER, INITIAL_MILK, INITIAL_BEANS, INITIAL_CUPS, INITIAL_MONEY)

fun main() {
    while (true) {
        println()
        println("Write action (buy, fill, take, remaining, exit):")

        when (readln()) {
            "buy" -> buyAction()
            "exit" -> break
            "fill" -> fillAction()
            "remaining" -> remainingAction()
            "take" -> takeAction()
        }
    }
}

fun buyAction() {
    println()
    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")

    when (readln()) {
        "1" -> buyEspresso()
        "2" -> buyLatte()
        "3" -> buyCappuccino()
        "back" -> return
    }
}

fun buyEspresso() {
    if (checkResources(250, 0,16, 1)) {
        coffeeMachine.water -= 250
        coffeeMachine.beans -= 16
        coffeeMachine.cups -= 1
        coffeeMachine.money += 4
    }
}

fun buyLatte() {
    if (checkResources(350, 75,  20, 1)) {
        coffeeMachine.water -= 350
        coffeeMachine.milk -= 75
        coffeeMachine.beans -= 20
        coffeeMachine.cups -= 1
        coffeeMachine.money += 7
    }
}

fun buyCappuccino() {
    if (checkResources(200, 100,  12, 1)) {
        coffeeMachine.water -= 200
        coffeeMachine.milk -= 100
        coffeeMachine.beans -= 12
        coffeeMachine.cups -= 1
        coffeeMachine.money += 6
    }
}

fun checkResources(waterNeeded: Int, milkNeeded: Int, beansNeeded: Int, cupsNeeded: Int): Boolean {
    if (coffeeMachine.water < waterNeeded) {
        println("Sorry, not enough water!")
        return false
    }

    if (coffeeMachine.milk < milkNeeded) {
        println("Sorry, not enough milk!")
        return false
    }

    if (coffeeMachine.beans < beansNeeded) {
        println("Sorry, not enough coffee beans!")
        return false
    }

    if (coffeeMachine.cups < cupsNeeded) {
        println("Sorry, not enough cups!")
        return false
    }

    println("I have enough resources, making you a coffee!")

    return true
}

fun fillAction() {
    println()
    println("Write how many ml of water you want to add:")

    val waterToAdd = readln().toInt()

    println("Write how many ml of milk you want to add:")

    val milkToAdd = readln().toInt()

    println("Write how many grams of coffee beans you want to add:")

    val beansToAdd = readln().toInt()

    println("Write how many disposable cups you want to add:")

    val cupsToAdd = readln().toInt()

    coffeeMachine.water += waterToAdd
    coffeeMachine.milk += milkToAdd
    coffeeMachine.beans += beansToAdd
    coffeeMachine.cups += cupsToAdd
}

fun remainingAction() {
    println()
    println("The coffee machine has:")
    println("${coffeeMachine.water} ml of water")
    println("${coffeeMachine.milk} ml of milk")
    println("${coffeeMachine.beans} g of coffee beans")
    println("${coffeeMachine.cups} disposable cups")
    println("\$${coffeeMachine.money} of money")
}

fun takeAction() {
    println("I gave you \$${coffeeMachine.money}")

    coffeeMachine.money = 0
}
