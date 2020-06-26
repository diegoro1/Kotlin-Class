// unfortunately, unlike java, Kotlin does not allow me to create a static variable (I believe)
// therefore, i had to use a companion object

class SavingsAccount(private var savingsBalance: Double = 0.0)
{
    // static annual interest rate
    companion object
    {
        private var annualInterestRate = 0.0
    }

    // this is a very generous equation given, i wish my bank did this
    fun calculateMonthlyInterest()
    {
        savingsBalance += (savingsBalance * annualInterestRate) / 12.0
    }

    // sets newInterest
    fun modifyInterestRate(newInterest: Double)
    {
        annualInterestRate = newInterest
    }

    // getter for private savingsBalance
    fun getBalance(): Double = savingsBalance
}