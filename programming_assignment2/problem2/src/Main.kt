fun main()
{
    // initiates two accounts
    val account1 = SavingsAccount(2000.00)
    val account2 = SavingsAccount(3000.00)

    account1.modifyInterestRate(4.00)

    // prints out balance of each account after each month of the year
    for (i in 1..12)
    {
        account1.calculateMonthlyInterest()
        println("For month $i: Total balance of account1 is %.2f".format(account1.getBalance()))
        account2.calculateMonthlyInterest()
        println("For month $i: Total balance of account2 is %.2f".format(account2.getBalance()))
    }

    // changing the interest rate to 5.0 and getting balance for one more month
    account1.modifyInterestRate(5.00)
    account1.calculateMonthlyInterest()
    println("For month 13: Total balance of account1 is %.2f".format(account1.getBalance()))
    account2.calculateMonthlyInterest()
    println("For month 13: Total balance of account1 is %.2f".format(account2.getBalance()))

}