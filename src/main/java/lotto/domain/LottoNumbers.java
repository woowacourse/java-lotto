package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class LottoNumbers {
	public static final int SIZE = 6;

	private List<LottoNumber> lottoNumbers;

	public LottoNumbers(List<LottoNumber> lottoNumbers) {
		vailidate(lottoNumbers);
		Collections.sort(lottoNumbers);
		this.lottoNumbers = new ArrayList<>(lottoNumbers);
	}

	private void vailidate(List<LottoNumber> lottoNumbers) {
		validateNullAndEmpty(lottoNumbers);
		validateDuplicateNumber(lottoNumbers);
		validateSizeMismatch(lottoNumbers);
	}

	private void validateNullAndEmpty(List<LottoNumber> lottoNumbers) {
		if (lottoNumbers == null || lottoNumbers.isEmpty()) {
			throw new IllegalArgumentException("null이나 빈 값이 들어올 수 없습니다.");
		}
	}

	private void validateDuplicateNumber(List<LottoNumber> lottoNumbers) {
		boolean isDuplicate = lottoNumbers.size() != new HashSet<>(lottoNumbers).size();
		if (isDuplicate) {
			throw new IllegalArgumentException("중복된 번호가 존재합니다!");
		}
	}

	private void validateSizeMismatch(List<LottoNumber> lottoNumbers) {
		if (lottoNumbers.size() != SIZE) {
			throw new IllegalArgumentException("로또 번호는 " + SIZE + "개여야 합니다!");
		}
	}

	public boolean contains(LottoNumber lottoNumber) {
		return lottoNumbers.contains(lottoNumber);
	}

	public int matchCount(LottoNumbers lottoNumbers) {
		return (int)lottoNumbers.getLottoNumbers()
			.stream()
			.filter(lottoNumber -> this.lottoNumbers.contains(lottoNumber))
			.count();
	}

	public List<LottoNumber> getLottoNumbers() {
		return lottoNumbers;
	}
}
