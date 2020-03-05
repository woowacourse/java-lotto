package lotto.domain;

import lotto.exceptions.LottoIllegalArgumentException;
import lotto.utils.StringParser;

import java.util.*;
import java.util.stream.Collectors;

public class Lotto {
	public static final int LOTTO_TICKET_SIZE = 6;

	private final List<LottoNumber> lottoNumbers;

	private Lotto(final List<LottoNumber> lottoNumbers) {
		checkIsSizeSix(lottoNumbers);
		checkIsDuplicated(lottoNumbers);

		this.lottoNumbers = lottoNumbers.stream()
				.sorted(LottoNumber::compareTo)
				.collect(Collectors.toUnmodifiableList());
	}

	/**
	 * @param input 예시) "1,2,3,4,5,6"
	 */
	public static Lotto of(String input) {
		List<Integer> integers = StringParser.stringToIntegerList(input);
		List<LottoNumber> lottoNumbers = integers.stream()
				.map(LottoNumber::of)
				.collect(Collectors.toUnmodifiableList());

		return new Lotto(lottoNumbers);
	}

	public static Lotto of(int... input) {
		List<LottoNumber> lottoNumbers = Arrays.stream(input)
				.boxed()
				.map(LottoNumber::of)
				.collect(Collectors.toUnmodifiableList());

		return new Lotto(lottoNumbers);
	}

	public static Lotto of(final List<LottoNumber> input) {
		return new Lotto(input);
	}

	private void checkIsDuplicated(List<LottoNumber> lottoNumbers) {
		Set<LottoNumber> distinctLottoNumbers = new HashSet<>(lottoNumbers);
		if (distinctLottoNumbers.size() != lottoNumbers.size()) {
			throw new LottoIllegalArgumentException();
		}
	}

	private void checkIsSizeSix(List<LottoNumber> lottoNumbers) {
		if (lottoNumbers.size() != LOTTO_TICKET_SIZE) {
			throw new LottoIllegalArgumentException();
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

	public List<LottoNumber> getLottoNumbers() {
		return Collections.unmodifiableList(lottoNumbers);
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
