package lotto.domain.result;

import lotto.domain.number.SerialLottoNumber;
import lotto.domain.number.SerialLottoNumberFactory;

import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketsFactory {
	public static LottoTickets of(List<String> lottoTicketsInput) {
		List<SerialLottoNumber> lottoTickets = lottoTicketsInput.stream()
				.map(SerialLottoNumberFactory::of)
				.collect(Collectors.toUnmodifiableList());

		return new LottoTickets(lottoTickets);
	}
}
