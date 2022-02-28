package domain;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Lotto {
	public static final int LOTTO_SIZE = 6;

	private final TreeSet<LottoNumber> lotto;

	public Lotto(List<LottoNumber> lottoNumbers) {
		checkLottoSize(lottoNumbers);
		this.lotto = new TreeSet<>(lottoNumbers);
		checkDuplicatedLottoNumber(lotto);
	}

	public Lotto(Set<LottoNumber> lottoNumbers) {
		checkDuplicatedLottoNumber(lottoNumbers);
		this.lotto = new TreeSet<>(lottoNumbers);
	}

	public static Lotto of(String[] lottoNumbers) {
		return new Lotto(toLotto(lottoNumbers));
	}

	private static Set<LottoNumber> toLotto(String[] lottoNumbers) {
		Set<LottoNumber> lotto = new TreeSet<>();
		for (String number : lottoNumbers) {
			int lottoNumber = Integer.parseInt(number.trim());
			lotto.add(LottoNumber.of(lottoNumber));
		}
		return lotto;
	}

	private void checkLottoSize(List<LottoNumber> lottoNumbers) {
		if (lottoNumbers.size() != LOTTO_SIZE) {
			throw new IllegalArgumentException("로또 숫자가 6개가 아닙니다.");
		}
	}

	private void checkDuplicatedLottoNumber(Set<LottoNumber> lottoNumbers) {
		if (lottoNumbers.size() != LOTTO_SIZE) {
			throw new IllegalArgumentException("6개의 숫자는 모두 달라야합니다.");
		}
	}

	public int calculateMatchCount(Lotto targetLotto) {
		return (int)lotto.stream()
			.filter(lottoNumber -> targetLotto.contains(lottoNumber))
			.count();
	}

	public boolean contains(LottoNumber lottoNumber) {
		return lotto.contains(lottoNumber);
	}

	public TreeSet<LottoNumber> getLotto() {
		return new TreeSet<>(lotto);
	}
}
