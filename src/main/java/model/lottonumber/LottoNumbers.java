package model.lottonumber;

import java.util.List;

public class LottoNumbers {
	private static final String NUMBER_SIZE_ERROR_MESSAGE = "[Error]: 로또는 %d개의 숫자여야 합니다.";
	private static final int LOTTO_SIZE = 6;

	private final List<LottoNumber> numbers;

	private LottoNumbers(List<LottoNumber> numbers) {
		this.numbers = numbers;
	}

	public static LottoNumbers from(LottoNumbersGenerationStrategy lottoNumbersGenerationStrategy) {
		List<LottoNumber> generatedLottoNumbers = lottoNumbersGenerationStrategy.generate(LOTTO_SIZE);

		return new LottoNumbers(generatedLottoNumbers);
	}

	public static LottoNumbers changeFrom(List<LottoNumber> numbers) {
		if (numbers.size() != LOTTO_SIZE) {
			throw new IllegalArgumentException(String.format(NUMBER_SIZE_ERROR_MESSAGE, LOTTO_SIZE));
		}

		return new LottoNumbers(numbers);
	}

	public static LottoNumbers valueOf(LottoNumbers numbers) {
		return new LottoNumbers(numbers.getNumbers());
	}

	public long countMatchedNumbers(List<LottoNumber> winningNumbers) {
		return numbers.stream()
			.filter(winningNumbers::contains)
			.count();
	}

	public boolean validateMatchWithBonus(LottoNumber number) {
		return numbers.contains(number);
	}

	public LottoNumbersDTO getLottoNumbersDTO() {
		return new LottoNumbersDTO(numbers);
	}

	public List<LottoNumber> getNumbers() {
		return this.numbers;
	}
}
