import java.security.SecureRandom
import java.util.Scanner
import kotlin.system.exitProcess

// OOP was not allowed in this problem

// returns an int based on difficulty
fun generateNumber(difficulty: Int, rand: SecureRandom): Int
{
    var number = 0
    when(difficulty)
    {
        1 -> number = rand.nextInt(8) + 1 // so 0 is not a choice
        2 -> number = rand.nextInt(98) + 1
        3 -> number = rand.nextInt(998) + 1
        4 -> number = rand.nextInt(9998) + 1
    }

    return number
}

// returns answer to addition question
fun additionQuestion(rand: SecureRandom, difficulty: Int): Int
{
    val x = generateNumber(difficulty, rand)
    val y = generateNumber(difficulty, rand)

    println("How much is $x plus $y?")

    return x + y
}

// returns answer to the multiplication question
fun multiplicationQuestion(rand: SecureRandom, difficulty: Int): Int
{
    val x = generateNumber(difficulty, rand)
    val y = generateNumber(difficulty, rand)

    println("How much is $x times $y?")

    return x * y
}

// returns answer to the subtraction question (always positive)
fun subtractionQuestion(rand: SecureRandom, difficulty: Int): Int
{
    var x = generateNumber(difficulty, rand)
    var y = generateNumber(difficulty, rand)

    // makes sure that answer is positive
    while (y >= x)
    {
        x = generateNumber(difficulty, rand)
        y = generateNumber(difficulty, rand)
    }

    println("How much is $x minus $y?")

    return x - y
}

// returns answer to the division question (remainder is always 0, no fractions)
fun divisionQuestion(rand: SecureRandom, difficulty: Int): Int
{
    var x = generateNumber(difficulty, rand)
    var y = generateNumber(difficulty, rand)

    // makes sure there is no remainder
    while ((x%y != 0))
    {
        x = generateNumber(difficulty, rand)
        y = generateNumber(difficulty, rand)
    }

    println("How much is $x divided by $y?")

    return x / y
}

// returns 1 if answer is correct, else 0
fun feedBack(answer: Int, solution: Int, rand: SecureRandom): Int
{
    val feedBack = if (answer == solution) 1 else 0
    var feedBackText = rand.nextInt(4)

    if (feedBack == 1)
    {
        when(feedBackText)
        {
            0 -> println("Very good!")
            1 -> println("Excellent!")
            2 -> println("Nice work!")
            3 -> println("Keep up the good work!")
        }
    }
    else
    {
        when(feedBackText)
        {
            0 -> println("No. Please try again.")
            1 -> println("Wrong. Try once more.")
            2 -> println("Don’t give up!")
            3 -> println("No. Keep trying.")
        }
    }

    return feedBack
}

// returns int representing difficulty
fun difficultyMenu(scan: Scanner): Int
{
    val difficulty: Int

    println("Are you ready to sell your soul to the public school system?...")
    println("I mean... play a math game?")
    println("Please enter the level of difficulty: (4 the highest, 1 the lowest)")

    difficulty = scan.nextInt()
    if (difficulty !in 1..4)
    {
        println("Congratulations, you failed before the game started.")
        exitProcess(1)
    }

    return difficulty
}

// returns int representing the problem type
fun problemTypeMenu(scan: Scanner): Int
{
    val problemType: Int

    println("Please enter the problem type:")
    println("1 -> Addition")
    println("2 -> Multiplication")
    println("3 -> Subtraction")
    println("4 -> Division")
    println("5 -> Random")

    problemType = scan.nextInt()
    if (problemType !in 1..5)
    {
        println("Congratulations, you failed before the game started.")
        exitProcess(1)
    }

    return problemType
}

// returns correct answer
fun question(problemType: Int, rand: SecureRandom, difficulty: Int): Int
{
    var answer = 0
    when(problemType)
    {
        1 -> answer = additionQuestion(rand, difficulty)
        2 -> answer = multiplicationQuestion(rand, difficulty)
        3 -> answer = subtractionQuestion(rand, difficulty)
        4 -> answer = divisionQuestion(rand, difficulty)
        5 -> answer = question(rand.nextInt(4) + 1, rand, difficulty) // selects (1..4)
    }

    return answer
}

// returns 0 or 1
fun endMenu(scan: Scanner): Int
{
    val answer: Int

    println("Enter the following:")
    println("1 -> new game")
    println("2 -> quit")

    answer = scan.nextInt()
    return if (answer == 1) 1 else 0
}

fun main()
{
    val rand = SecureRandom()
    val scan = Scanner(System.`in`)
    var solution: Int
    var answer: Int
    var correctAnswer = 0
    var flag = 1
    var difficulty: Int
    var problemType: Int

    while (flag == 1)
    {
        difficulty = difficultyMenu(scan)
        problemType = problemTypeMenu(scan)

        for (i in 0..9)
        {
            solution = question(problemType, rand, difficulty)
            answer = scan.nextInt()
            correctAnswer += feedBack(answer, solution, rand)
        }

        // problem states that 7.5 is a fail but there are only 10 questions and no half points...
        if (correctAnswer < 8)
            println("Please ask your teacher for extra help.")
        else
            println("Congratulations, you are ready to go to the next level!")

        // resets score
        correctAnswer = 0

        // check if to quit program
        flag = endMenu(scan)
    }

}