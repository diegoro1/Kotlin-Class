import java.util.Scanner

// main menu
fun menu()
{
    println("Choose the following:")
    println("1 -> SI (kilo, meters)")
    println("2 -> American (Pound, inches)")
    println("3 -> quit")
}

// returns BMI using SI units
fun si_bmi() : Double
{
    var weight : Double
    var height : Double
    var result : Double
    val read = Scanner(System.`in`)

    println("Please enter your height in meters:")
    height = read.nextDouble()

    println("Please enter your weight in kilograms")
    weight = read.nextDouble()

    result = si_calc(weight, height)

    println("Your bmi is %.1f".format(result))

    return result
}

// returns BMI using US units
fun us_bmi() : Double
{
    var weight : Double
    var height : Double
    var result : Double
    val read = Scanner(System.`in`)

    println("Please enter your height in inches:")
    height = read.nextDouble()

    println("Please enter your weight in pounds")
    weight = read.nextDouble()

    result = us_calc(weight, height)

    println("Your bmi is %.1f".format(result))

    return result
}

// outputs final result of bmi test
fun final_result(calculated : Double)
{
    if (calculated < 18.5)
        println("You are currently underweight.")
    else if (calculated in 18.5..24.9)
        println("You currently at a normal weight")
    else if (calculated in 25.0..29.9)
        println("You are currently overweight")
    else
        println("You are currently obese")
}

// calculates bmi using SI units
fun si_calc(weight: Double, height: Double) : Double
{
    return weight / (height * height)
}

// calculates bmi using american units
fun us_calc(weight: Double, height: Double) : Double
{
    return 703 * weight / (height * height)
}

fun main()
{
    var choice : Int
    menu()
    choice = Integer.valueOf(readLine())

    when(choice)
    {
        1 -> final_result(si_bmi())
        2 -> final_result(us_bmi())
    }

    println("Have a nice day!")
}