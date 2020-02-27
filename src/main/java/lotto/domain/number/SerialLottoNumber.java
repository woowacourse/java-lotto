package lotto.domain.number;

import lotto.exceptions.NotSixSizeException;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class SerialLottoNumber {
	private static final int SERIAL_SIZE = 6;

	private final Set<LottoNumber> lottoNumbers;

	SerialLottoNumber(final Set<LottoNumber> lottoNumbers) {
		checkIsValidSize(lottoNumbers);

		this.lottoNumbers = Collections.unmodifiableSet(new TreeSet<>(lottoNumbers));
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
