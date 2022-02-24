package utils;

import domain.LottoNumber;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberGenerator {
	public static final int END_INDEX = 6;
	public static final int FROM_INDEX = 0;

	public static List<LottoNumber> generate() {
		List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumber.values());
		Collections.shuffle(lottoNumbers);
		return lottoNumbers.subList(FROM_INDEX, END_INDEX);
	}
}
