package model.lottonumber;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbers {
	private static final int LOTTO_SIZE = 6;

	private final List<LottoNumber> numbers;

	private LottoNumbers(List<LottoNumber> numbers) {
		this.numbers = numbers;
	}

	public static LottoNumbers from(LottoNumbersGenerationStrategy lottoNumbersGenerationStrategy) {
		List<LottoNumber> generatedLottoNumbers = lottoNumbersGenerationStrategy.generate(LOTTO_SIZE);

		return new LottoNumbers(generatedLottoNumbers);
	}

	public static LottoNumbers valueOf(LottoNumbers numbers) {
		return new LottoNumbers(numbers.getNumbers());
	}

	public long countMatchedNumbers(List<LottoNumber> winningNumbers) {
		return numbers.stream()
			.filter(winningNumbers::contains)
			.count();
	}

	public boolean checkMatchWithBonus(LottoNumber number) {
		return numbers.contains(number);
	}

	public List<LottoNumber> getNumbers() {
		return new ArrayList<>(numbers);
	}

	@Override
	public String toString() {
		return "[" + String.join(", ", numbers.stream().map(LottoNumber::toString).collect(Collectors.toList())) + "]";
	}
}
