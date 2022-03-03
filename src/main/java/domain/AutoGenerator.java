package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoGenerator implements LottoGenerator {
	private static final int LOWER_BOUND = 1;
	private static final int UPPER_BOUND = 45;
	private static final int START_INDEX = 0;
	private static final int END_INDEX = 6;
	private final List<LottoNumber> candidates;

	public AutoGenerator() {
		this.candidates = IntStream
			.rangeClosed(LOWER_BOUND, UPPER_BOUND)
			.mapToObj(LottoNumber::new)
			.collect(Collectors.toList());
	}

	@Override
	public List<LottoNumber> generateLottoNumbers() {
		List<LottoNumber> lottoNumbers = new ArrayList<>();
		Collections.shuffle(candidates);
		for (int index = START_INDEX; index < END_INDEX; index++) {
			lottoNumbers.add(candidates.get(index));
		}
		return lottoNumbers;
	}
}
