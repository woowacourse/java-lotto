package domain;

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
	public List<Integer> generate() {
		Collections.shuffle(candidates);
		return candidates.subList(0, 6);
	}
}
