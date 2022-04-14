package bank;

/*Otp class follows SINGLE RESPONSIBILITY PRINCIPLE
 * Otp - This class will generate Otp to customer to give information about the account status.
 */

public class GenerateOtp extends CustomerAccount //Otp class to send message to customer about their account status
{
	//method to generate message for deposit info:
	public static final void generateDepositOtp(CustomerAccount customer_acc,double deposit_amount)
	{
			System.out.println("OTP: User Mobile Number --> " + customer_acc.phoneNumber);
			System.out.println("Amount Rs." + deposit_amount + "successfully deposited to account: " + customer_acc.accountNumber);
			System.out.println("your account "+ customer_acc.accountNumber + " current balance is " + customer_acc.balance);
	}
	
	//method to generate message for withdraw info:
	public static final void generateWithdrawOtp(CustomerAccount customer_acc,double withdraw_amount)
	{
			System.out.println("OTP: User Mobile Number --> " + customer_acc.phoneNumber);
			System.out.println("Amount Rs." + withdraw_amount + "successfully withdrawn from account: " + customer_acc.accountNumber);
			System.out.println("your account "+ customer_acc.accountNumber + " current balance is " + customer_acc.balance);
	}
	
	//method to generate message for transferring money info:
	public static final void generateOtpSenderOtp(CustomerAccount sender_acc,double transfered_amount,CustomerAccount receiver)
	{
		System.out.println("SENDER OTP: User Mobile Number --> " + sender_acc.phoneNumber);
		System.out.println("Money Rs." + transfered_amount + "is tranfered from your account" + sender_acc.accountNumber +"to account" + receiver.accountNumber);
		System.out.println("your account "+ sender_acc.accountNumber + " current balance is " + sender_acc.balance);
        
	}
	
	//method to generate message for receiving money info:
	public static final void generateOtpReceiverOtp(CustomerAccount sender_acc,double transfered_amount,CustomerAccount receiver)
	{
		System.out.println("RECEIVER OTP: User Mobile Number --> " + receiver.phoneNumber);
		System.out.println("Money Rs." + transfered_amount + "is Credited to your account" + receiver.accountNumber +"from account" + sender_acc.accountNumber);
		System.out.println("your account "+ receiver.accountNumber + " current balance is " + receiver.balance);
        
	}
}
