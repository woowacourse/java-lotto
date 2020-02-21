package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import lotto.domain.exception.InvalidLottoException;

public class Lotto {
	public static final int SIZE = 6;

	private final List<LottoNumber> lotto;

	public Lotto(List<LottoNumber> lotto) {
		validate(lotto);
		this.lotto = new ArrayList<>(lotto);
		Collections.sort(this.lotto);
	}

	private void validate(List<LottoNumber> lotto) {
		validateSize(lotto);
		validateDuplicate(lotto);
	}

	private void validateSize(List<LottoNumber> lotto) {
		if (lotto == null || lotto.size() != SIZE) {
			throw new InvalidLottoException("로또는 6개의 로또 번호가 필요합니다.");
		}
	}

	private void validateDuplicate(List<LottoNumber> lotto) {
		Set<LottoNumber> distinct = new HashSet<>(lotto);
		if (distinct.size() != lotto.size()) {
			throw new InvalidLottoException("로또 번호는 중복될 수 없습니다.");
		}
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
		return Collections.unmodifiableList(lotto);
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
