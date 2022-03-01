package model.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoNumbersGenerator {
	private static final List<Integer> lottoNumbers = new ArrayList<>();

	static {
		LottoNumber.makeLottoNumbers(lottoNumbers);
	}

	public static List<Integer> pickLottoNumbers() {
		List<Integer> shuffledNumbers = shuffleLottoNumbers();
		return LottoNumber.cutByLottoSize(shuffledNumbers);
	}

	private static List<Integer> shuffleLottoNumbers() {
		Collections.shuffle(lottoNumbers);
		return lottoNumbers;
	}
}
