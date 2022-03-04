package model.lotto;

public class LottoCount {
	private static final int STANDARD_CAN_MAKE = 0;
	public static final String CANT_MAKE_AMOUNT_ERROR_MESSAGE = "[Error]: 이 만큼의 로또를 생성할 수 없습니다.";

	private int count;

	public LottoCount(int count) {
		this.count = count;
	}

	public boolean haveRemainToMake() {
		return count > STANDARD_CAN_MAKE;
	}

	public void reduceCountOfRemain() {
		if (haveRemainToMake()) {
			count--;
		}
	}

	public int getCount() {
		return Integer.valueOf(count);
	}

	public void useCountForPassive(int count) {
		checkCanMake(count);
		this.count -= count;
	}
	
	private void checkCanMake(int count) {
		if (this.count < count) {
			throw new IllegalArgumentException(CANT_MAKE_AMOUNT_ERROR_MESSAGE);
		}
	}
}
