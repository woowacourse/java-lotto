package lotto.domain;

import lotto.dto.LottoDto;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
	private static final int LOTTO_SIZE = 6;

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
			throw new IllegalArgumentException("null이나 빈 값은 들어올 수 없습니다!");
		}
	}

	private void validateSizeMismatch(List<Integer> lottoNumber) {
		if (lottoNumber.size() != LOTTO_SIZE) {
			throw new IllegalArgumentException("로또 번호는 " + LOTTO_SIZE + "개여야 합니다!");
		}
	}

	private void validateDuplicateNumber(List<Integer> lottoNumber) {
		if (lottoNumber.size() != new HashSet<>(lottoNumber).size()) {
			throw new IllegalArgumentException("중복된 번호가 존재합니다!");
		}
	}

	public WinningPrize findLottoPrize(WinningLotto winningNumber) {
		Set<Integer> concatenatedSet = new HashSet<>(lottoNumber);
		concatenatedSet.addAll(winningNumber.getWinningNumber());

		int matchCount = (LOTTO_SIZE * 2) - concatenatedSet.size();
		boolean bonusMatch = lottoNumber.contains(winningNumber.getBonusNumber());

		return WinningPrize.of(matchCount, bonusMatch);
	}

	public LottoDto makeLottoDto() {
		return new LottoDto(this.lottoNumber);
	}
}
