package lotto.domain;

public class LottoCount {
	private static final int MINIMUM_LOTTO_COUNT = 1;
	private static final String INVALID_LOTTO_COUNT_EXCEPTION_MESSAGE = String.format("로또 구입 갯수는 최소 %d개 이상",
		MINIMUM_LOTTO_COUNT);

	private final long lottoCount;

	private LottoCount(long lottoCount) {
		validateCount(lottoCount);
		this.lottoCount = lottoCount;
	}

	public static LottoCount of(long lottoCount) {
		return new LottoCount(lottoCount);
	}

	private void validateCount(long count) {
		if (count < MINIMUM_LOTTO_COUNT) {
			throw new IllegalArgumentException(INVALID_LOTTO_COUNT_EXCEPTION_MESSAGE);
		}
	}

	public boolean isNonFullCount(int currentCount) {
		return lottoCount > currentCount;
	}

	@Override
	public String toString() {
		return String.valueOf(lottoCount);
	}
}
