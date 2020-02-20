package lotto.domain;

public class LottoCount {
	private static final int MINIMUM_LOTTO_COUNT = 1;
	private static final String INVALID_LOTTO_COUNT_EXCEPTION_MESSAGE = String.format("로또 구입 갯수는 최소 %d개 이상",
		MINIMUM_LOTTO_COUNT);

	private final int lottoCount;

	public LottoCount(int lottoCount) {
		validateCount(lottoCount);
		this.lottoCount = lottoCount;
	}

	private void validateCount(int count) {
		if (count < MINIMUM_LOTTO_COUNT) {
			throw new IllegalArgumentException(INVALID_LOTTO_COUNT_EXCEPTION_MESSAGE);
		}
	}

	public int getLottoCount() {
		return lottoCount;
	}
}
