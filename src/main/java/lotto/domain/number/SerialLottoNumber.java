package lotto.domain.number;

import lotto.exceptions.NotSixSizeException;
import lotto.util.StringParser;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class SerialLottoNumber {
	private static final int SERIAL_SIZE = 6;

	private final Set<LottoNumber> lottoNumbers;

	private SerialLottoNumber(final Set<LottoNumber> lottoNumbers) {
		checkIsValidSize(lottoNumbers);

		this.lottoNumbers = Collections.unmodifiableSet(new TreeSet<>(lottoNumbers));
	}

	public static SerialLottoNumber of(final Set<LottoNumber> lottoNumbers) {
		return new SerialLottoNumber(lottoNumbers);
	}

	public static SerialLottoNumber of(String input) {
		Set<LottoNumber> lottoNumbers = StringParser.parseIntegerList(input)
				.stream()
				.map(LottoNumber::of)
				.collect(Collectors.toSet());

		return of(lottoNumbers);
	}

	private void checkIsValidSize(Set<LottoNumber> lottoNumbers) {
		if (lottoNumbers.size() != SERIAL_SIZE) {
			throw new NotSixSizeException(lottoNumbers);
		}
	}

	public boolean contains(LottoNumber lottoNumber) {
		return lottoNumbers.contains(lottoNumber);
	}

	public int countMatchingNumber(SerialLottoNumber that) {
		return (int) lottoNumbers.stream()
				.filter(that::contains)
				.count();
	}

	public Set<LottoNumber> getLottoNumbers() {
		return lottoNumbers;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SerialLottoNumber that = (SerialLottoNumber) o;
		return Objects.equals(lottoNumbers, that.lottoNumbers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottoNumbers);
	}
}
