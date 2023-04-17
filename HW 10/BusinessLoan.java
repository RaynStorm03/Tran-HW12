package loanCompany;
//Business Loan Class that extends from Abstract class
public class BusinessLoan extends Loan{

	public BusinessLoan(int loanNum, String lastName, double loanAmount, int term) {
		super(loanNum, lastName, loanAmount, term);
	}
	
	@Override
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate + 1;
	}

	@Override
	public String toString() {
		return "Business Loan [Loan # = " + loanNum + ", Last Name = " + lastName + ", Loan Amount = $" + loanAmount
				+ ", Interest Rate = " + interestRate + "%, Term Years = " + term + "]";
	}

}
