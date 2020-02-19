package lotto.domain;

import lotto.exceptions.LottoTicketIllegalArgumentException;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoTicket {
	public static final int LOTTO_TICKET_SIZE = 6;
	private final List<LottoNumber> lottoNumbers;

	public LottoTicket(final List<LottoNumber> lottoNumbers) {
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

	public List<LottoNumber> getLottoNumbers() {
		return Collections.unmodifiableList(lottoNumbers);
	}
}
