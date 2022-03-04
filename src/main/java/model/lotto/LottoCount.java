package model.lotto;

public class LottoCount {
	private static final int STANDARD_CAN_MAKE = 0;

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
}
