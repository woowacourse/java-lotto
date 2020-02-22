package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 클래스 이름 : Lotto.java
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class Lotto {
	public static final int LOTTO_LENGTH = 6;

	protected final Set<LottoNumber> lottoNumbers;

	public Lotto(final List<LottoNumber> inputLottoNumbers) {
		Objects.requireNonNull(inputLottoNumbers, "입력이 null 입니다.");
		this.lottoNumbers = inputLottoNumbers.stream()
				.collect(
						Collectors.toCollection(
								() -> new TreeSet<>((Comparator.comparing(LottoNumber::getLottoNumber)))
						)
				);
		validateLottoNumbers(inputLottoNumbers);
	}

	private void validateLottoNumbers(List<LottoNumber> inputLottoNumbers) {
		validateEmptyInput();
		validateDuplicatedInput(inputLottoNumbers);
		validateInputLength();
	}

	private void validateEmptyInput() {
		if (lottoNumbers.isEmpty()) {
			throw new IllegalArgumentException("로또번호가 입력되지 않았습니다.");
		}
	}

	private void validateDuplicatedInput(List<LottoNumber> inputLottoNumbers) {
		if (lottoNumbers.size() != inputLottoNumbers.size()) {
			throw new IllegalArgumentException("입력 로또번호에 중복이 있습니다.");
		}
	}

	private void validateInputLength() {
		if (lottoNumbers.size() != LOTTO_LENGTH) {
			throw new IllegalArgumentException("입력 로또번호는 6개가 입력되어야 합니다.");
		}
	}

	public boolean isContain(final LottoNumber lottoNumber) {
		return lottoNumbers.stream()
				.anyMatch(value -> value == lottoNumber);
	}

	public Set<LottoNumber> getLottoNumbers() {
		return this.lottoNumbers;
	}
}

