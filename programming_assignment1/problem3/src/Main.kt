import java.net.CacheResponse
import java.util.*
import kotlin.system.exitProcess

fun start_menu()
{
    println("Welcome to this very useful survey!")
    println("Where we will be stealing your data and doing absolutely nothing with it!")
    println("We will present you with 5 topics and you will rate them by importance from 1 - 10")
    println("with 1 being least important and 10 being most important")
    println("Dont worry about repeating values because that's ok!")
    println("And don't try to do anything silly! the survay will get sad and sell your data to...")
    println("I have said too much... START NOW")
    println()
    println("Enter 1 to Start")
    println("Enter 0 to Quit... while you still can")
}

fun continue_menu()
{
    println("Enter the following")
    println("1 -> Next person to take survey")
    println("2 -> exit and check results")
}

// gets the total given the topic index
fun total(response: Array<Array<Int>>, index: Int): Int
{
    var total_value = 0

    // multiplies by (i + 1) to give the frequency * magnetude of vote
    for (i in 0..9)
        total_value += (response[index][i] * (i + 1))

    return total_value
}

// gets the average given the topic index
fun average_result(response: Array<Array<Int>>, index: Int, survey_quantity: Int): Double = total(response, index).toDouble() / survey_quantity.toDouble()


fun display_results(response: Array<Array<Int>>, topics: Array<String>, survey_quantity: Int)
{
    print("%32s   |".format("Topic"))
    for (i in 0..9)
        print("(%d) | ".format(i+1))
    print("Average")
    println()

    for (i in 0..4)
    {
        print("%32s   |".format(topics[i]))
        for (j in 0..9)
        {
            print("%d   | ".format(response[i][j]))
        }
        print("%.2f".format(average_result(response, i, survey_quantity)))
        println()
    }
}

// gets index with highest total points
fun highest(response: Array<Array<Int>>, topics: Array<String>)
{
    var highest = 0
    var total_val = 0
    var current = 0

    for (i in 0..4)
    {
        current = total(response, i)
        if (total_val <= current)
        {
            highest = i
            total_val = current
        }
    }

    println("Highest point total: %s with a total of %d".format(topics[highest], total_val))
}

// gets index with lowest total points
fun lowest(response: Array<Array<Int>>, topics: Array<String>)
{
    var lowest = 0
    var total_val = 2147483647
    var current = 0

    for (i in 0..4)
    {
        current = total(response, i)
        if (total_val >= current)
        {
            lowest = i
            total_val = current
        }
    }

    println("Lowest point total: %s with a total of %d".format(topics[lowest], total_val))
}

fun main()
{
    val topics = arrayOf("Politics", "Food", "Home Improvement", "Pets", "Pop Culture")
    var response = arrayOf<Array<Int>>() // 2D array for responses
    var choice: Int // for start menu
    var rate: Int
    var survey_quantity = 0
    val read = Scanner(System.`in`)


    // initialising the array with 0s
    for (i in 0..4)
    {
        var array = arrayOf<Int>()
        for (j in 0..9)
        {
            array += 0
        }
        response += array
    }

    start_menu()
    choice = Integer.valueOf(readLine())

    // exits programs if 1 is not chosen
    if (choice != 1)
    {
        println("Good choice!")
        exitProcess(0)
    }

   loop@while (choice == 1)
   {
       for (i in 0..4)
       {
           //println("How in interesting is %s".format(topics[i]))
           // checks is input in an int value
           try
           {
               println("How in interesting is %s".format(topics[i]))
               rate = read.nextInt()
               // checks if rate is a valid input
               if (rate !in 1..10)
               {
                   println("Invalid input, exiting the survey.")
                   break@loop
               }

               // adds to rated index
               response[i][rate - 1]++
           }
           catch (exception: InputMismatchException)
           {
               println("This is not an integer.")
               break@loop
           }
       }
       survey_quantity++ // adds to amounts of surveys taken
       continue_menu()
       choice = Integer.valueOf(readLine())
   }

    display_results(response, topics, survey_quantity)
    highest(response, topics)
    lowest(response, topics)
}