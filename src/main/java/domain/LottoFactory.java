package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {

	private final List<Integer> lottoNumbers = IntStream.range(1, 46)
		.boxed()
		.collect(Collectors.toList());

	public List<Integer> generateLotto() {
		Collections.shuffle(lottoNumbers);
		return this.lottoNumbers.subList(0, 6);
	}
}
