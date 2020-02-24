package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LottoNumbers {
	private List<LottoNumber> lottoNumbers;

	public LottoNumbers(List<LottoNumber> lottoNumbers) {
		validateNullAndEmpty(lottoNumbers);
		validateDuplicateNumber(lottoNumbers);
		this.lottoNumbers = new ArrayList<>(lottoNumbers);
	}

	private void validateNullAndEmpty(List<LottoNumber> lottoNumbers) {
		if (lottoNumbers == null || lottoNumbers.isEmpty()) {
			throw new IllegalArgumentException("null이나 빈 값이 들어올 수 없습니다.");
		}
	}

	private void validateDuplicateNumber(List<LottoNumber> lottoNumber) {
		boolean isDuplicate = lottoNumber.size() != new HashSet<>(lottoNumber).size();
		if (isDuplicate) {
			throw new IllegalArgumentException("중복된 번호가 존재합니다!");
		}
	}

	public int size() {
		return lottoNumbers.size();
	}

	public boolean contains(LottoNumber lottoNumber) {
		return lottoNumbers.contains(lottoNumber);
	}

	public List<LottoNumber> getLottoNumbers() {
		return lottoNumbers;
	}

	public int matchCount(LottoNumbers lottoNumbers) {
		return (int)lottoNumbers.getLottoNumbers()
			.stream()
			.filter(lottoNumber -> this.lottoNumbers.contains(lottoNumber))
			.count();
	}
}
