package lotto.domain;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoTicket {
	private static final String NOT_MATCHED_SIZE = "로또는 총 6자리의 숫자로 이루어져야 합니다.";
	private static final String HAS_DUPLICATED_NUMBER = "로또 숫자는 중복 될 수 없습니다.";
	private static final String DELIMITER = ", ";
	private static final int NUMBERS_COUNT = 6;

	private final List<LottoNumber> lottoNumbers;

	private LottoTicket(final List<LottoNumber> lottoNumbers) {
		checkValidationOf(lottoNumbers);
		this.lottoNumbers = lottoNumbers;
	}

	private void checkValidationOf(List<LottoNumber> lottoNumbers) {
		if (lottoNumbers.size() != NUMBERS_COUNT) {
			throw new IllegalArgumentException(NOT_MATCHED_SIZE);
		}
		if (getDistinctCountOf(lottoNumbers) != NUMBERS_COUNT) {
			throw new IllegalArgumentException(HAS_DUPLICATED_NUMBER);
		}
	}

	private int getDistinctCountOf(List<LottoNumber> lottoNumbers) {
		return (int)lottoNumbers.stream()
			.distinct()
			.count();
	}

	public static LottoTicket of(String values) {
		return Arrays.stream(values.split(DELIMITER))
			.map(LottoNumber::new)
			.collect(Collectors.collectingAndThen(toList(), LottoTicket::new));
	}

	public static LottoTicket of(List<LottoNumber> lottoNumbers) {
		return lottoNumbers.stream()
			.collect(Collectors.collectingAndThen(toList(), LottoTicket::new));
	}

	public int getMatchCount(final WinningNumbers winningNumbers) {
		return (int)lottoNumbers.stream()
			.filter(winningNumbers.getWinningTicket()::contains)
			.count();
	}

	public boolean contains(LottoNumber lottoNumber) {
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
