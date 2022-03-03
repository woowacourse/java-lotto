package model.lottonumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoNumbersGenerator {
	private static final List<model.lottonumber.LottoNumber> lottoNumbers = new ArrayList<>();

	static {
		model.lottonumber.LottoNumber.makeLottoNumbers(lottoNumbers);
	}

	public static List<model.lottonumber.LottoNumber> pickLottoNumbers() {
		List<model.lottonumber.LottoNumber> shuffledNumbers = shuffleLottoNumbers();
		return model.lottonumber.LottoNumber.cutByLottoSize(shuffledNumbers);
	}

	private static List<LottoNumber> shuffleLottoNumbers() {
		Collections.shuffle(lottoNumbers);
		return lottoNumbers;
	}
}
