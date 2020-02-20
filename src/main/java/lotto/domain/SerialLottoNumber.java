package lotto.domain;

import lotto.exceptions.LottoTicketIllegalArgumentException;

import java.util.*;
import java.util.stream.Collectors;

public class SerialLottoNumber {
	public static final int LOTTO_TICKET_SIZE = 6;

	private final List<LottoNumber> lottoNumbers;

	public SerialLottoNumber(final List<LottoNumber> lottoNumbers) throws LottoTicketIllegalArgumentException {
		checkIsSizeSix(lottoNumbers);
		checkIsDuplicated(lottoNumbers);

		this.lottoNumbers = lottoNumbers.stream()
				.sorted(LottoNumber::compare)
				.collect(Collectors.toUnmodifiableList());
	}

	private void checkIsDuplicated(List<LottoNumber> lottoNumbers) {
		Set<LottoNumber> distinctLottoNumbers = new HashSet<>(lottoNumbers);
		if (distinctLottoNumbers.size() != lottoNumbers.size()) {
			throw new LottoTicketIllegalArgumentException();
		}
	}

	private void checkIsSizeSix(List<LottoNumber> lottoNumbers) {
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

	public List<LottoNumber> getLottoNumbers() {
		return Collections.unmodifiableList(lottoNumbers);
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
