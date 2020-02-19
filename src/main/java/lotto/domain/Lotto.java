package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto implements Iterable<LottoNumber> {
	public static final int SIZE = 6;
	private static final String DUPLICATED_NUMBER_MESSAGE = "로또 번호가 중복됩니다.";
	private static final String INVALID_SIZE_MESSAGE = "로또 번호가 존재하지 않습니다.";

	private final List<LottoNumber> lotto = new ArrayList<>();

	public Lotto(List<LottoNumber> lotto) {
		validate(lotto);
		this.lotto.addAll(lotto);
		Collections.sort(this.lotto);
	}

	private void validate(List<LottoNumber> lotto) {
		validateSize(lotto);
		validateDuplicate(lotto);
	}

	private void validateSize(List<LottoNumber> lotto) {
		if (lotto == null || lotto.size() != SIZE) {
			throw new IllegalArgumentException(INVALID_SIZE_MESSAGE);
		}
	}

	private void validateDuplicate(List<LottoNumber> lotto) {
		Set<LottoNumber> distinct = new HashSet<>(lotto);
		if (distinct.size() != lotto.size()) {
			throw new IllegalArgumentException(DUPLICATED_NUMBER_MESSAGE);
		}
	}

	public static Lotto of(int... lottoNumbers) {
		return new Lotto(Arrays.stream(lottoNumbers)
				.mapToObj(LottoNumber::of)
				.collect(Collectors.toList()));
	}

	public boolean contains(LottoNumber lottoNumber) {
		return lotto.contains(lottoNumber);
	}

	public long countOfMatch(Lotto that) {
		return lotto.stream()
				.filter(that::contains)
				.count();
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

	@Override
	public Iterator<LottoNumber> iterator() {
		return lotto.iterator();
	}
}
