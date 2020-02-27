package lotto.domain;

import lotto.dto.LottoDto;
import lotto.exception.LottoException;

import java.util.*;

public class Lotto {
	public static final int SIZE = 6;
	public static final String LOTTOBALL_AMOUNT_ERROR = "로또 번호는 " + SIZE + "개여야 합니다!";
	public static final String NULL_OR_EMPTY_VALUE_ERROR = "null이나 빈 값은 들어올 수 없습니다!";
	public static final String DUPLICATE_BALL_ERROR = "중복된 번호가 존재합니다!";

	private final List<LottoNumber> lottoNumbers;

	public Lotto(final List<LottoNumber> lottoNumber) {
		validate(lottoNumber);
		this.lottoNumbers = Collections.unmodifiableList(lottoNumber);
	}

	private void validate(List<LottoNumber> lottoNumber) {
		validateNullAndEmpty(lottoNumber);
		validateSizeMismatch(lottoNumber);
		validateDuplicateNumber(lottoNumber);
	}

	private void validateNullAndEmpty(List<LottoNumber> lottoNumber) {
		if (lottoNumber == null || lottoNumber.isEmpty()) {
			throw new LottoException(NULL_OR_EMPTY_VALUE_ERROR);
		}
	}

	private void validateSizeMismatch(List<LottoNumber> lottoNumber) {
		if (lottoNumber.size() != SIZE) {
			throw new LottoException(LOTTOBALL_AMOUNT_ERROR);
		}
	}

	private void validateDuplicateNumber(List<LottoNumber> lottoNumber) {
		if (lottoNumber.size() != new HashSet<>(lottoNumber).size()) {
			throw new LottoException(DUPLICATE_BALL_ERROR);
		}
	}

	public WinningPrize findLottoPrize(WinningLotto winningLotto) {
		Set<LottoNumber> concatenatedSet = new HashSet<>(lottoNumbers);
		concatenatedSet.addAll(winningLotto.getWinningLotto().lottoNumbers);

		int matchCount = (SIZE * 2) - concatenatedSet.size();
		boolean bonusMatch = lottoNumbers.contains(winningLotto.getBonusNumber());

		return WinningPrize.of(matchCount, bonusMatch);
	}

	public boolean contains(LottoNumber lottoNumber) {
		return lottoNumbers.stream()
				.anyMatch(x -> x.equals(lottoNumber));

	}

	public LottoDto makeLottoDto() {
		return new LottoDto(this.lottoNumbers);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Lotto lotto = (Lotto) o;
		return Objects.equals(lottoNumbers, lotto.lottoNumbers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottoNumbers);
	}
}
