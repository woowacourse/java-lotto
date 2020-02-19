package lotto.domain;

public class LottoCount {
	public static final int MINIMUM_LOTTO_COUNT = 1;
	private final int lottoCount;

	public LottoCount(int lottoCount) {
		validateCount(lottoCount);
		this.lottoCount = lottoCount;
	}

	private void validateCount(int count) {
		if (count < MINIMUM_LOTTO_COUNT) {
			throw new IllegalArgumentException("로또 구입 갯수는 최소 1개이상");
		}
	}

	public int getLottoCount() {
		return lottoCount;
	}
}
