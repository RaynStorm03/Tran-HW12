package loanCompany;
// Personal Loan Class that extends from Abstract Class
public class PersonalLoan extends Loan {

	public PersonalLoan(int loanNum, String lastName, double loanAmount, int term) {
		super(loanNum, lastName, loanAmount, term);
	}

	@Override
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate + 2;
	}
	
	@Override
	public String toString() {
		return "Personal Loan [Loan # = " + loanNum + ", Last Name = " + lastName + ", Loan Amount = $" + loanAmount
				+ ", Interest Rate = " + interestRate + "%, Term Years = " + term + "]";
	}

}
