package domain.generator;

import java.util.List;
import java.util.stream.Collectors;

import domain.Lotto;

public class ManualLottoGenerator implements LottoGenerator {

	private final List<String[]> numbers;

	public ManualLottoGenerator(List<String[]> numbers) {
		this.numbers = numbers;
	}

	@Override
	public List<Lotto> creatLottos() {
		return numbers.stream()
			.map(Lotto::of)
			.collect(Collectors.toUnmodifiableList());
	}
}
