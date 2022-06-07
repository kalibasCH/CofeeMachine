package machine

fun main() {
    CoffeeMachine().theStateOfMachine()
}


class CoffeeMachine(var theState: String = "ChooseAnAction") {

    fun theStateOfMachine() {
        when (theState) {
            "ChooseAnAction" -> {
                print("Write action (buy, fill, take, remaining, exit): ")
                chooseAnAction()
            }
            "ChoosingAVariantOfCoffee" -> {
                print("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
                Buying().makeCoffee()
            }
        }
    }

    private fun chooseAnAction(command: String = readln()) {
        when (command) {
            "buy" -> {
                theState = "ChoosingAVariantOfCoffee"
                theStateOfMachine()
            }
            "fill" -> fill()
            "take" -> take()
            "remaining" -> remaining()
            "exit" -> return
        }
    }

    private fun fill() {
        print("\nWrite how many ml of water do you want to add: ")
        Resources.WATER.value += readLine()!!.toInt()
        print("Write how many ml of milk do you want to add: ")
        Resources.MILK.value += readLine()!!.toInt()
        print("Write how many grams of coffee beans do you want to add: ")
        Resources.COFFEE.value += readLine()!!.toInt()
        print("Write how many disposable cups of coffee do you want to add: ")
        Resources.CUPS.value += readLine()!!.toInt()
        theState = "ChooseAnAction"
        theStateOfMachine()
    }

    private fun take() {
        println("I gave you $${Resources.MONEY.value}")
        Resources.MONEY.value = 0
        theState = "ChooseAnAction"
        theStateOfMachine()
    }

    private fun remaining() {
        println(
            "The coffee machine has: \n" +
                    "${Resources.WATER.value} ml of water\n" +
                    "${Resources.MILK.value} ml of milk\n" +
                    "${Resources.COFFEE.value} g of coffee beans\n" +
                    "${Resources.CUPS.value} disposable cups\n" +
                    "$${Resources.MONEY.value} of money\n"
        )
        theState = "ChooseAnAction"
        theStateOfMachine()
    }

    inner class Buying {
        fun makeCoffee(command: String = readln()) {
            when (command) {
                "1" -> getEspresso()
                "2" -> getLatte()
                "3" -> getCappuccino()
                "back" -> {
                    theState = "ChooseAnAction"
                    theStateOfMachine()
                }
            }
        }

        private fun getEspresso() {
            if (
                Resources.WATER.value - VolumeOf.WAT_ESPRESSO.value >= VolumeOf.ZERO.value &&
                Resources.COFFEE.value - VolumeOf.COFFEE_ESPRESSO.value >= VolumeOf.ZERO.value &&
                Resources.CUPS.value > VolumeOf.ONE.value
            ) {
                println("I have enough resources, making you a coffee!\n")
                Resources.WATER.value -= VolumeOf.WAT_ESPRESSO.value
                Resources.COFFEE.value -= VolumeOf.COFFEE_ESPRESSO.value
                Resources.CUPS.value -= VolumeOf.ONE.value
                Resources.MONEY.value += VolumeOf.COST_ESPRESSO.value
                theState = "ChooseAnAction"
                theStateOfMachine()
            } else {
                var resources = ""
                when {
                    Resources.WATER.value - VolumeOf.WAT_ESPRESSO.value < VolumeOf.ZERO.value -> resources =
                        "water"
                    Resources.COFFEE.value - VolumeOf.COFFEE_ESPRESSO.value < VolumeOf.ZERO.value -> resources =
                        "coffee"
                    Resources.CUPS.value < VolumeOf.ONE.value -> resources = "cups"
                }
                println("Sorry, not enough $resources!\n")
                theState = "ChooseAnAction"
                theStateOfMachine()
            }
        }

        private fun getLatte() {
            if (
                Resources.WATER.value - VolumeOf.WAT_LATTE.value >= VolumeOf.ZERO.value &&
                Resources.COFFEE.value - VolumeOf.COFFEE_LATTE.value >= VolumeOf.ZERO.value &&
                Resources.MILK.value - VolumeOf.MILK_LATTE.value >= VolumeOf.ZERO.value &&
                Resources.CUPS.value > VolumeOf.ONE.value
            ) {
                println("I have enough resources, making you a coffee!\n")
                Resources.WATER.value -= VolumeOf.WAT_LATTE.value
                Resources.COFFEE.value -= VolumeOf.COFFEE_LATTE.value
                Resources.MILK.value -= VolumeOf.MILK_LATTE.value
                Resources.CUPS.value -= VolumeOf.ONE.value
                Resources.MONEY.value += VolumeOf.COST_LATTE.value
                theState = "ChooseAnAction"
                theStateOfMachine()
            } else {
                var resources = ""
                when {
                    Resources.WATER.value - VolumeOf.WAT_ESPRESSO.value < VolumeOf.ZERO.value -> resources =
                        "water"
                    Resources.COFFEE.value - VolumeOf.COFFEE_ESPRESSO.value < VolumeOf.ZERO.value -> resources =
                        "coffee"
                    Resources.MILK.value - VolumeOf.MILK_LATTE.value < VolumeOf.ZERO.value -> resources =
                        "milk"
                    Resources.CUPS.value < VolumeOf.ONE.value -> resources = "cups"
                }
                println("Sorry, not enough $resources!\n")
                theState = "ChooseAnAction"
                theStateOfMachine()
            }
        }

        private fun getCappuccino() {
            if (
                Resources.WATER.value - VolumeOf.WAT_CAPPUCCINO.value >= VolumeOf.ZERO.value &&
                Resources.COFFEE.value - VolumeOf.COFFEE_CAPPUCCINO.value >= VolumeOf.ZERO.value &&
                Resources.MILK.value - VolumeOf.MILK_CAPPUCCINO.value >= VolumeOf.ZERO.value &&
                Resources.CUPS.value > VolumeOf.ONE.value
            ) {
                println("I have enough resources, making you a coffee!\n")
                Resources.WATER.value -= VolumeOf.WAT_CAPPUCCINO.value
                Resources.COFFEE.value -= VolumeOf.COFFEE_CAPPUCCINO.value
                Resources.MILK.value -= VolumeOf.MILK_CAPPUCCINO.value
                Resources.CUPS.value -= VolumeOf.ONE.value
                Resources.MONEY.value += VolumeOf.COST_CAPPUCCINO.value
                theState = "ChooseAnAction"
                theStateOfMachine()
            } else {
                var resources = ""
                when {
                    Resources.WATER.value - VolumeOf.WAT_CAPPUCCINO.value < VolumeOf.ZERO.value -> resources =
                        "water"
                    Resources.COFFEE.value - VolumeOf.COFFEE_CAPPUCCINO.value < VolumeOf.ZERO.value -> resources =
                        "coffee"
                    Resources.MILK.value - VolumeOf.MILK_CAPPUCCINO.value < VolumeOf.ZERO.value -> resources =
                        "milk"
                    Resources.CUPS.value < VolumeOf.ONE.value -> resources = "cups"
                }
                println("Sorry, not enough $resources!\n")
                theState = "ChooseAnAction"
                theStateOfMachine()
            }
        }
    }

    enum class VolumeOf(val value: Int) {
        WAT_ESPRESSO(250),
        WAT_LATTE(350),
        WAT_CAPPUCCINO(200),
        MILK_LATTE(75),
        MILK_CAPPUCCINO(100),
        COFFEE_ESPRESSO(16),
        COFFEE_LATTE(20),
        COFFEE_CAPPUCCINO(12),
        COST_ESPRESSO(4),
        COST_LATTE(7),
        COST_CAPPUCCINO(6),
        ONE(1),
        ZERO(0)
    }

    enum class Resources(var value: Int) {
        WATER(400),
        MILK(540),
        COFFEE(120),
        CUPS(9),
        MONEY(550)
    }
}