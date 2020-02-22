package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import lotto.domain.exception.InvalidLottoException;

public class Lotto {
	public static final int SIZE = 6;

	private final List<LottoNumber> lotto;

	public Lotto(List<LottoNumber> lotto) {
		validate(lotto);
		this.lotto = copyAndSort(lotto);
	}

	private List<LottoNumber> copyAndSort(List<LottoNumber> lotto) {
		List<LottoNumber> copiedLotto = new ArrayList<>(lotto);
		Collections.sort(copiedLotto);
		return Collections.unmodifiableList(copiedLotto);
	}

	private void validate(List<LottoNumber> lotto) {
		if (isIncorrectSize(lotto)) {
			throw new InvalidLottoException("로또는 6개의 로또 번호가 필요합니다.");
		}
		if (isDuplicate(lotto)) {
			throw new InvalidLottoException("로또 번호는 중복될 수 없습니다.");
		}
	}

	private boolean isIncorrectSize(List<LottoNumber> lotto) {
		return lotto == null || lotto.size() != SIZE;
	}

	private boolean isDuplicate(List<LottoNumber> lotto) {
		return lotto.stream().distinct().count() != lotto.size();
	}

	public boolean contains(LottoNumber lottoNumber) {
		return lotto.contains(lottoNumber);
	}

	public int countOfMatch(Lotto that) {
		return (int)lotto.stream()
				.filter(that::contains)
				.count();
	}

	public List<LottoNumber> getValue() {
		return lotto;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Lotto that = (Lotto)o;
		return Objects.equals(lotto, that.lotto);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lotto);
	}
}
