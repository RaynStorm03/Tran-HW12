package loanCompany;

// Abstract Class
public abstract class Loan implements LoanConstants {

	// Declare fields
	int loanNum;
	String lastName;
	double loanAmount;
	double interestRate;
	int term;
	
	// Constructor
	public Loan(int loanNum, String lastName, double loanAmount, int term) {
		super();
		this.loanNum = loanNum;
		this.lastName = lastName;
		
		try {
		// Don't allow loan amounts over $500,000
		if (loanAmount <= maxLoan)
			this.loanAmount = loanAmount;
		else
			throw new Exception();
		}
		catch (Exception ex)
		{
			System.out.println("Error: Loan Amount can't go over $500,000");
		}
		
		// Force any loan term that isn't one of the three to short
		if (term == shortTerm || term == mediumTerm || term == longTerm)
			this.term = term;
		else
			this.term = shortTerm;
	}
	
	public double calculateOwed() {
		return loanAmount + (loanAmount * interestRate / 100) * term;
	}

	abstract public void setInterestRate(double interestRate);
	abstract public String toString();
	
	

	
}
