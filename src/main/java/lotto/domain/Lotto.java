package lotto.domain;

import lotto.exceptions.LottoException;
import lotto.utils.StringParser;

import java.util.*;
import java.util.stream.Collectors;

public class Lotto {
	public static final int LOTTO_TICKET_SIZE = 6;

	private final Set<LottoNumber> lottoNumbers;

	private Lotto(final Set<LottoNumber> lottoNumbers) {
		checkIsSizeSix(lottoNumbers);

		this.lottoNumbers = lottoNumbers;
	}

	/**
	 * @param input 예시) "1,2,3,4,5,6"
	 */
	public static Lotto of(String input) {
		List<Integer> integers = StringParser.stringToIntegerList(input);
		Set<LottoNumber> lottoNumbers = integers.stream()
				.map(LottoNumber::of)
				.collect(Collectors.toUnmodifiableSet());

		return new Lotto(lottoNumbers);
	}

	public static Lotto of(final List<LottoNumber> input) {
		return new Lotto(input.stream()
				.collect(Collectors.toUnmodifiableSet()));
	}

	private void checkIsSizeSix(Set<LottoNumber> lottoNumbers) {
		if (lottoNumbers.size() != LOTTO_TICKET_SIZE) {
			throw new LottoException();
		}
	}

	public boolean contains(LottoNumber lottoNumber) {
		return lottoNumbers.contains(lottoNumber);
	}

	public int countMatching(Lotto winningLottoNumbers) {
		return (int) lottoNumbers.stream()
				.filter(winningLottoNumbers::contains)
				.count();
	}

	public List<LottoNumber> sorted() {
		return lottoNumbers.stream()
				.sorted()
				.collect(Collectors.toUnmodifiableList());
	}

	public Set<LottoNumber> getLottoNumbers() {
		return Collections.unmodifiableSet(lottoNumbers);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Lotto that = (Lotto) o;
		return Objects.equals(lottoNumbers, that.lottoNumbers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottoNumbers);
	}

	@Override
	public String toString() {
		return String.join(", ", lottoNumbers.toString());
	}
}
