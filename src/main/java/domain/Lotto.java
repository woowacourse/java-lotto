package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lotto {

	private final List<Number> lotto;

	public Lotto(List<Number> lotto) {
		checkLottoNumber(lotto);
		this.lotto = lotto;
	}

	public static Lotto from(String[] userInput) {
		return new Lotto(Stream.of(userInput)
			.map(Number::from)
			.collect(Collectors.toList()));
	}

	private void checkLottoNumber(List<Number> lotto) {
		checkLottoNumberSize(lotto);
		checkDuplicate(lotto);
	}

	private void checkDuplicate(List<Number> lotto) {
		boolean duplicated = lotto.stream()
			.distinct()
			.count() != lotto.size();

		if (duplicated) {
			throw new IllegalArgumentException("중복된 숫자를 입력할 수 없습니다");
		}
	}

	private void checkLottoNumberSize(List<Number> lotto) {
		if (lotto.size() != 6) {
			throw new IllegalArgumentException("로또 번호는 6개의 숫자여야 합니다");
		}
	}

	public boolean isContain(Number number) {
		return lotto.contains(number);
	}

	public LottoRank confirmWinningResult(Lotto winningNumbers, Number bonusNumber) {
		int count = (int)lotto.stream()
			.filter(winningNumbers::isContain)
			.count();
		return LottoRank.findRank(count, lotto.contains(bonusNumber));
	}

	public List<Number> getLotto() {
		return lotto;
	}
}
