package lotto.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.util.RandomUtils;

/**
 * Lotto.java
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class Lotto {
	public static final int LOTTO_LENGTH = 6;
	public static final int LOTTO_PRICE = 1_000;

	protected final List<LottoNumber> lottoNumbers;

	public Lotto(final List<LottoNumber> inputLottoNumbers) {
		validateLottoNumbers(inputLottoNumbers);
		this.lottoNumbers = inputLottoNumbers.stream()
				.sorted(Comparator.comparing(LottoNumber::getLottoNumber))
				.collect(Collectors.toList());
	}

	private void validateLottoNumbers(final List<LottoNumber> inputLottoNumbers) {
		Objects.requireNonNull(inputLottoNumbers, "입력이 null 입니다.");
		validateEmptyInput(inputLottoNumbers);
		validateInputLength(inputLottoNumbers);
		validateDuplicatedInput(inputLottoNumbers);
	}

	private void validateDuplicatedInput(final List<LottoNumber> inputLottoNumbers) {
		Set<LottoNumber> deDuplicatedLottoNumbers = new HashSet<>(inputLottoNumbers);
		if (deDuplicatedLottoNumbers.size() != inputLottoNumbers.size()) {
			throw new IllegalArgumentException("입력 리스트에 중복이 있습니다.");
		}
	}

	private void validateInputLength(final List<LottoNumber> inputLottoNumbers) {
		if (inputLottoNumbers.size() != LOTTO_LENGTH) {
			throw new IllegalArgumentException("입력 리스트의 길이가 6이어야 합니다.");
		}
	}

	private void validateEmptyInput(final List<LottoNumber> inputLottoNumbers) {
		if (inputLottoNumbers.isEmpty()) {
			throw new IllegalArgumentException("리스트에 요소가 없습니다.");
		}
	}

	public boolean isContain(final LottoNumber lottoNumber) {
		return lottoNumbers.stream()
				.anyMatch(value -> value.equals(lottoNumber));
	}

	public static Lotto createLottoAuto() {
		List<Integer> randomLottoNumbers = RandomUtils.getRandomIntList(
			LOTTO_LENGTH,
			LottoNumber.MINIMUM_LOTTO_NUMBER,
			LottoNumber.MAXIMUM_LOTTO_NUMBER
		);
		return createLotto(randomLottoNumbers);
	}

	public static Lotto createLottoManual(final List<Integer> inputLottoNumbers) {
		Objects.requireNonNull(inputLottoNumbers);
		return createLotto(inputLottoNumbers);
	}

	private static Lotto createLotto(final List<Integer> lottoNumbers) {
		checkLottoNumberLength(lottoNumbers);
		List<LottoNumber> lottoNumberList = new ArrayList<>();

		for (int lottoNumber : lottoNumbers) {
			lottoNumberList.add(LottoNumber.of(lottoNumber));
		}

		return new Lotto(lottoNumberList);
	}

	private static void checkLottoNumberLength(List<Integer> lottoNumbers) {
		if (lottoNumbers.size() != LOTTO_LENGTH) {
			throw new IllegalArgumentException("로또 한 장에는 6개의 로또번호가 있어야 합니다.");
		}
	}

	public List<LottoNumber> getLottoNumbers() {
		return this.lottoNumbers;
	}
}
