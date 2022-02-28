package util;

import domain.LottoNumbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoNumberGenerator implements LottoNumbersGenerator {
	private static final ArrayList<Integer> candidates = new ArrayList<>();

	public RandomLottoNumberGenerator() {
		for (int eachNumber = 1; eachNumber <= 45; eachNumber++) {
			candidates.add(eachNumber);
		}
	}

	@Override
	public List<LottoNumbers> generate(int count) {
		List<LottoNumbers> result = new ArrayList<>();

		while (count-- > 0) {
			Collections.shuffle(candidates);
			result.add(LottoNumbers.of(candidates.subList(0, 6)));
		}

		return result;
	}
}
