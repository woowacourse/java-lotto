package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 6개의 로또 번호를 가지고 있는 로또 클래스
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class Lotto {
	private static final String NULL_INPUT_EXCEPTION_MESSAGE = "입력이 null 입니다.";
	private static final String EMPTY_LOTTO_EXCEPTION_MESSAGE = "로또번호가 입력되지 않았습니다.";
	private static final String LOTTO_OVERLAP_EXCEPTION_MESSAGE = "입력 로또번호에 중복이 있습니다.";
	private static final String SIX_NUMBERS_FOR_ONT_LOTTO_EXCEPTION_MESSAGE = "입력 로또번호는 6개가 입력되어야 합니다.";
	private static final int LOTTO_LENGTH = 6;

	protected final Set<LottoNumber> lottoNumbers;

	public Lotto(final List<LottoNumber> inputLottoNumbers) {
		Objects.requireNonNull(inputLottoNumbers, NULL_INPUT_EXCEPTION_MESSAGE);
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
			throw new InvalidLottoException(EMPTY_LOTTO_EXCEPTION_MESSAGE);
		}
	}

	private void validateDuplicatedInput(List<LottoNumber> inputLottoNumbers) {
		if (lottoNumbers.size() != inputLottoNumbers.size()) {
			throw new InvalidLottoException(LOTTO_OVERLAP_EXCEPTION_MESSAGE);
		}
	}

	private void validateInputLength() {
		if (lottoNumbers.size() != LOTTO_LENGTH) {
			throw new InvalidLottoException(SIX_NUMBERS_FOR_ONT_LOTTO_EXCEPTION_MESSAGE);
		}
	}

	public boolean isContain(final LottoNumber inputLottoNumber) {
		return lottoNumbers.stream()
				.anyMatch(value -> value.equals(inputLottoNumber));
	}

	public Set<LottoNumber> getLottoNumbers() {
		return this.lottoNumbers;
	}
}

