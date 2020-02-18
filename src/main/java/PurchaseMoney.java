import lotto.IllegalMoneyUnitException;

public class PurchaseMoney {
	public static final int MONEY_UNIT = 1000;
	public static final int ZERO = 0;

	private int purchaseMoney;

	public PurchaseMoney(String purchaseMoney) {
		int money = numberFormatValidate(purchaseMoney);
		validate(money);
		this.purchaseMoney = money;
	}


	private int numberFormatValidate(String money) {
		try {
			return Integer.parseInt(money);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("문자열은 입력되지 않습니다.");
		}
	}

	private void validate(int purchaseMoney) {
		checkMoneyUnit(purchaseMoney);
	}

	private void checkMoneyUnit(int purchaseMoney) {
		if(purchaseMoney % MONEY_UNIT != ZERO){
			throw new IllegalMoneyUnitException("구입금액은 1000원단위로만 가능합니다.");
		}
	}
}
