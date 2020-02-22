package lotto.domain;

import lotto.exceptions.LottoTicketIllegalArgumentException;

import java.util.*;

public class SerialLottoNumber {
	public static final int LOTTO_TICKET_SIZE = 6;

	private final Set<LottoNumber> lottoNumbers;

	public SerialLottoNumber(final List<LottoNumber> lottoNumbers) throws LottoTicketIllegalArgumentException {
		this(new TreeSet<>(lottoNumbers));
	}

	public SerialLottoNumber(final Set<LottoNumber> lottoNumbers) throws LottoTicketIllegalArgumentException {
		checkIsSizeSix(lottoNumbers);

		this.lottoNumbers = Collections.unmodifiableSet(new TreeSet<>(lottoNumbers));
	}

	private void checkIsSizeSix(Set<LottoNumber> lottoNumbers) {
		if (lottoNumbers.size() != LOTTO_TICKET_SIZE) {
			throw new LottoTicketIllegalArgumentException();
		}
	}

	public boolean contains(LottoNumber lottoNumber) {
		return lottoNumbers.contains(lottoNumber);
	}

	public int countMatching(SerialLottoNumber winningLottoNumbers) {
		return (int) lottoNumbers.stream()
				.filter(winningLottoNumbers::contains)
				.count();
	}

	public Set<LottoNumber> getLottoNumbers() {
		return Collections.unmodifiableSet(lottoNumbers);
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

	@Override
	public String toString() {
		return String.join(", ", lottoNumbers.toString());
	}
}
