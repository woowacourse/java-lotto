package model.lottonumber;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbers {
	private static final String NUMBER_SIZE_ERROR_MESSAGE = "[Error]: 로또는 %d개의 숫자여야 합니다.";
	private static final String LOTTO_NUMBER_REDUPLICATION_ERROR_MESSAGE = "[Error]: 로또 번호는 중복이 있으면 안됩니다";
	private static final int LOTTO_SIZE = 6;

	private final List<LottoNumber> numbers;

	private LottoNumbers(List<LottoNumber> numbers) {
		this.numbers = numbers;
	}

	public static LottoNumbers from(LottoNumbersGenerationStrategy lottoNumbersGenerationStrategy) {
		List<LottoNumber> generatedLottoNumbers = lottoNumbersGenerationStrategy.generate(LOTTO_SIZE);
		validateSize(generatedLottoNumbers);
		validateReduplication(generatedLottoNumbers);
		return new LottoNumbers(generatedLottoNumbers);
	}

	public static LottoNumbers valueOf(LottoNumbers otherNumbers) {
		return new LottoNumbers(otherNumbers.numbers);
	}

	public long countMatchedNumbers(List<LottoNumber> winningNumbers) {
		return numbers.stream()
			.filter(winningNumbers::contains)
			.count();
	}

	public boolean containLottoNumber(LottoNumber number) {
		return numbers.contains(number);
	}

	private static void validateSize(List<LottoNumber> numbers) {
		if (numbers.size() != LOTTO_SIZE) {
			throw new IllegalArgumentException(String.format(NUMBER_SIZE_ERROR_MESSAGE, LOTTO_SIZE));
		}
	}

	private static void validateReduplication(List<LottoNumber> numbers) {
		long count = numbers.stream().distinct().count();
		if (count != numbers.size()) {
			throw new IllegalArgumentException(LOTTO_NUMBER_REDUPLICATION_ERROR_MESSAGE);
		}
	}

	public List<LottoNumber> getNumbers() {
		return new ArrayList<>(numbers);
	}

	@Override
	public String toString() {
		return "[" + String.join(", ", numbers.stream().map(LottoNumber::toString).collect(Collectors.toList())) + "]";
	}
}
