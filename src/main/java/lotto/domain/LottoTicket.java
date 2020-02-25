package lotto.domain;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Objects;

public class LottoTicket {
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
		final LottoTicket winningTicket = winningNumbers.getWinningTicket();
		return winningTicket.contains(lottoNumber);
	}

	private boolean contains(LottoNumber lottoNumber) {
		return lottoNumbers.contains(lottoNumber);
	}

	public boolean isBonusNotMatch(final WinningNumbers winningNumbers) {
		return lottoNumbers.stream()
			.noneMatch(winningNumbers::isMatchWithBonus);
	}

	public List<String> getNumbers() {
		return lottoNumbers.stream()
			.map(LottoNumber::toString)
			.collect(toList());
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
