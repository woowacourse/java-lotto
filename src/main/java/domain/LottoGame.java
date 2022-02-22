package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LottoGame {

	public static final int LOTTO_NUMBER_SIZE = 6;

	public Lotto createLotto() {
		Set<LottoNumber> lottoNumbers = new HashSet<>();

		while (lottoNumbers.size() != LOTTO_NUMBER_SIZE) {
			lottoNumbers.add(LottoNumber.createByRandom());
		}

		return new Lotto(new ArrayList<>(lottoNumbers));
	}
}
