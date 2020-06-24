fun encryptor(n:Int) : Int = (n + 7) % 10

fun decryptor(n:Int) : Int
{
    var solution = n - 7

    if (solution < 0)
        solution = solution + 10

    return solution
}

fun encrypt(number:Int) : Int
{
    var temp : Int
    var solution : Int

    var fourth = (number - (number % 1000)) / 1000
    var third = ((number % 1000) - (number % 100)) / 100
    var second = ((number % 100) - (number % 10)) / 10
    var first = (number % 10)

    // encrypts individual digits
    fourth = encryptor(fourth)
    third = encryptor(third)
    second = encryptor(second)
    first = encryptor(first)

    // swaps first with third
    temp = first
    first = third
    third = temp

    // swaps second with fourth
    temp = second
    second = fourth
    fourth = temp

    // places numbers back in their places
    solution = fourth * 1000 + third * 100 + second * 10 + first

    return solution
}

fun decrypt(number:Int) : Int
{
    var temp : Int
    var solution : Int

    var fourth = (number - (number % 1000)) / 1000
    var third = ((number % 1000) - (number % 100)) / 100
    var second = ((number % 100) - (number % 10)) / 10
    var first = (number % 10)

    // decrypts individual digits
    fourth = decryptor(fourth)
    third = decryptor(third)
    second = decryptor(second)
    first = decryptor(first)

    // swaps first with third
    temp = first
    first = third
    third = temp

    // swaps second with fourth
    temp = second
    second = fourth
    fourth = temp

    // places numbers back in their places
    solution = fourth * 1000 + third * 100 + second * 10 + first

    return solution
}

fun menu()
{
    println("Please select the following:\n" +
            "->1 to encrypt\n" +
            "->2 to decrypt\n" +
            "->0 to quit")
}

fun main()
{
    var flag : Int
    var input : Int

    menu()

    // reads input from user
    flag = Integer.valueOf(readLine())

    while (flag in 1..2)
    {
        when (flag)
        {
            // runs encryption if input is not negative
            1 ->
            run {
                println("Please enter the value to be encrypted:")
                input = Integer.valueOf(readLine())
                if (input < 0)
                {
                    println("Please enter a positive integer.")
                    return@run
                }
                println(encrypt(input))
            }

            // runs decryption if input is not negative
            2 ->
            {
                println("Please enter the value to be decrypted:")
                input = Integer.valueOf(readLine())
                if (input < 0)
                run {
                    println("Please enter a positive integer.")
                    return@run
                }
                println(decrypt(input))
            }
        }

        menu()
        flag = Integer.valueOf(readLine())
    }

    println("Goodbye!")
}