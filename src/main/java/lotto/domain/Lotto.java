package lotto.domain;

import java.util.HashSet;
import java.util.List;

public class Lotto {
	private static final int LOTTO_SIZE = 6;

	private final List<Integer> lottoNumber;

	public Lotto(final List<Integer> lottoNumber) {
		validate(lottoNumber);
		this.lottoNumber = lottoNumber;
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

	public int findLottoPrize(List<Integer> winningNumbers, int bonusNumber) {
		HashSet<Integer> concatenatedSet = new HashSet<>(lottoNumber);
		concatenatedSet.addAll(winningNumbers);
		int winningCount = (lottoNumber.size() * 2) - concatenatedSet.size();
		return 0;
	}
}
