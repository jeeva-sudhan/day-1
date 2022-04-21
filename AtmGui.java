package bank;
//to achieve abstraction by hiding its implementation
public interface AtmGui {
	void deposit();
	void withDraw();
	void checkBalance();
	void transferMoney();
	void removeAccount();
}
