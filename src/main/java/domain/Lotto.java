package domain;

import java.util.List;

public class Lotto {
	private static final int LOTTO_SIZE = 6;
	private final List<LottoNumber> lotto;

	public Lotto(List<LottoNumber> lottoNumbers) {
		checkLottoSize(lottoNumbers);
		checkDuplicatedLottoNumber(lottoNumbers);
		this.lotto = lottoNumbers;
	}

	private void checkLottoSize(List<LottoNumber> lottoNumbers) {
		if (lottoNumbers.size() != LOTTO_SIZE) {
			throw new IllegalArgumentException("로또 숫자가 6개가 아닙니다.");
		}
	}

	private void checkDuplicatedLottoNumber(List<LottoNumber> lottoNumbers) {
		if (lottoNumbers.stream().distinct().count() != lottoNumbers.size()) {
			throw new IllegalArgumentException("6개의 숫자는 모두 달라야합니다.");
		}
	}
}
