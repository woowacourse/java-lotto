package model.lottonumber;

import java.util.Collections;
import java.util.List;

public class RandomLottoNumbersGenerator {
	private static final List<model.lottonumber.LottoNumber> lottoNumbers;

	static {
		lottoNumbers = LottoNumber.makeLottoNumbers();
	}

	public static List<LottoNumber> pickLottoNumbers() {
		List<LottoNumber> shuffledNumbers = shuffleLottoNumbers();
		return LottoNumber.cutByLottoSize(shuffledNumbers);
	}

	private static List<LottoNumber> shuffleLottoNumbers() {
		Collections.shuffle(lottoNumbers);
		return lottoNumbers;
	}
}
