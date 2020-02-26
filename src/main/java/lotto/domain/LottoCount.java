package lotto.domain;

import java.util.Objects;

public final class LottoCount {
	private static final int MINIMUM_LOTTO_COUNT = 0;
	private static final String INVALID_LOTTO_COUNT_EXCEPTION_MESSAGE = String.format("로또 구입 갯수는 최소 %d개 이상",
		MINIMUM_LOTTO_COUNT);
	private static final String INVALID_CURRENT_COUNT_VALUE_EXCEPTION_MESSAGE = "유효한 count 인자가 아닙니다.";

	private final int lottoCount;

	private LottoCount(int lottoCount) {
		validateCount(lottoCount);
		this.lottoCount = lottoCount;
	}

	private void validateCount(int count) {
		if (count < MINIMUM_LOTTO_COUNT) {
			throw new IllegalArgumentException(INVALID_LOTTO_COUNT_EXCEPTION_MESSAGE);
		}
	}

	public static LottoCount valueOf(int lottoCount) {
		return new LottoCount(lottoCount);
	}

	public boolean isNonFullCount(int currentCount) {
		if (lottoCount < currentCount) {
			throw new IllegalArgumentException(INVALID_CURRENT_COUNT_VALUE_EXCEPTION_MESSAGE);
		}
		return lottoCount > currentCount;
	}

	public boolean isBiggerThan(LottoCount otherLottoCount) {
		return this.lottoCount > otherLottoCount.lottoCount;
	}

	public LottoCount minus(LottoCount otherLottoCount) {
		return LottoCount.valueOf(this.lottoCount - otherLottoCount.lottoCount);
	}

	public boolean isNonRightCount(int lottoCount) {
		return this.lottoCount != lottoCount;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		LottoCount that = (LottoCount)object;
		return this.lottoCount == that.lottoCount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottoCount);
	}

	@Override
	public String toString() {
		return String.valueOf(lottoCount);
	}
}
