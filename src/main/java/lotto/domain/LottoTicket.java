package lotto.domain;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Objects;

public class LottoTicket {
	private static final String DELIMITER = ", ";
	private static final String START_BRACKET = "[";
	private static final String END_BRACKET = "]";

	private final List<LottoNumber> lottoNumbers;

	public LottoTicket(final List<LottoNumber> lottoNumbers) {
		this.lottoNumbers = lottoNumbers;
	}

	public int getMatchCount(final WinningNumbers winningNumbers) {
		return (int)lottoNumbers.stream()
			.filter(lottoNumber -> isMatch(winningNumbers, lottoNumber))
			.count();
	}

	private boolean isMatch(final WinningNumbers winningNumbers, final LottoNumber lottoNumber) {
		final List<LottoNumber> ordinaries = winningNumbers.getOrdinaries();
		return ordinaries.contains(lottoNumber);
	}

	public boolean isBonusNotMatch(final WinningNumbers winningNumbers) {
		return lottoNumbers.stream()
			.noneMatch(winningNumbers::isMatchWithBonus);
	}

	@Override
	public String toString() {
		return START_BRACKET +
			lottoNumbers.stream()
			.map(LottoNumber::toString)
			.collect(joining(DELIMITER)) +
			END_BRACKET;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LottoTicket that = (LottoTicket)o;
		return Objects.equals(lottoNumbers, that.lottoNumbers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottoNumbers);
	}
}
