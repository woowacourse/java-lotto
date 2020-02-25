package lotto.domain;

import lotto.dto.LottoDto;
import lotto.exception.LottoException;

import java.util.*;

public class Lotto {
	public static final int SIZE = 6;

	private final List<Integer> lottoNumber;

	public Lotto(final List<Integer> lottoNumber) {
		validate(lottoNumber);
		this.lottoNumber = Collections.unmodifiableList(lottoNumber);
	}

	private void validate(List<Integer> lottoNumber) {
		validateNullAndEmpty(lottoNumber);
		validateSizeMismatch(lottoNumber);
		validateDuplicateNumber(lottoNumber);
	}

	private void validateNullAndEmpty(List<Integer> lottoNumber) {
		if (lottoNumber == null || lottoNumber.isEmpty()) {
			throw new LottoException("null이나 빈 값은 들어올 수 없습니다!");
		}
	}

	private void validateSizeMismatch(List<Integer> lottoNumber) {
		if (lottoNumber.size() != SIZE) {
			throw new LottoException("로또 번호는 " + SIZE + "개여야 합니다!");
		}
	}

	private void validateDuplicateNumber(List<Integer> lottoNumber) {
		if (lottoNumber.size() != new HashSet<>(lottoNumber).size()) {
			throw new LottoException("중복된 번호가 존재합니다!");
		}
	}

	public WinningPrize findLottoPrize(WinningLotto winningNumber) {
		Set<Integer> concatenatedSet = new HashSet<>(lottoNumber);
		concatenatedSet.addAll(winningNumber.getWinningNumber());

		int matchCount = (SIZE * 2) - concatenatedSet.size();
		boolean bonusMatch = lottoNumber.contains(winningNumber.getBonusNumber());

		return WinningPrize.of(matchCount, bonusMatch);
	}

	public LottoDto makeLottoDto() {
		return new LottoDto(this.lottoNumber);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Lotto lotto = (Lotto) o;
		return Objects.equals(lottoNumber, lotto.lottoNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottoNumber);
	}
}
