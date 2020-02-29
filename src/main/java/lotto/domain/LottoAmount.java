package lotto.domain;

public class LottoAmount {
	private int autoLottoAmount;
	private int manualLottoAmount;

	public LottoAmount(long totalLottoAmount, int manualLottoAmount) {
		if (totalLottoAmount < manualLottoAmount) {
			throw new IllegalArgumentException("수동 갯수는 전체 구매한 로또 갯수보다 클수 없습니다.");
		}

		this.autoLottoAmount = (int)totalLottoAmount - manualLottoAmount;
		this.manualLottoAmount = manualLottoAmount;
	}

	public int getAutoLottoAmount() {
		return autoLottoAmount;
	}

	public int getManualLottoAmount() {
		return manualLottoAmount;
	}
}
