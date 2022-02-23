package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Lotto {
	private static final int LOTTO_SIZE = 6;
	private final List<LottoNumber> lotto;

	public Lotto(List<LottoNumber> lottoNumbers) {
		checkLottoSize(lottoNumbers);
		checkDuplicatedLottoNumber(lottoNumbers);
		Collections.sort(lottoNumbers);
		this.lotto = lottoNumbers;
	}

	public static Lotto of(String[] lottoNumbers) {
		return new Lotto(toLotto(lottoNumbers));
	}

	private static List<LottoNumber> toLotto(String[] lottoNumbers) {
		List<LottoNumber> lotto = new ArrayList<>();
		for (String number : lottoNumbers) {
			lotto.add(createLottoNumber(number));
		}
		return lotto;
	}

	private static LottoNumber createLottoNumber(String number) {
		return new LottoNumber(number);
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

	public int calculateMatchCount(Lotto targetLotto) {
		return (int) lotto.stream()
				.filter(lottoNumber -> targetLotto.isContain(lottoNumber))
				.count();
	}

	public boolean isContain(LottoNumber lottoNumber) {
		return lotto.contains(lottoNumber);
	}

	public List<LottoNumber> getLotto() {
		return lotto;
	}
	@Override
	public String toString() {
		return "추첨된 번호는 " + lotto + " 입니다.";
	}
}
