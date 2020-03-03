package lotto.domain.ticket;

import lotto.domain.number.SerialLottoNumber;

import java.util.List;
import java.util.stream.Collectors;

public class ManualLottoTicketsFactory implements TicketsGenerator {
	private final List<String> lottoTicketsInput;

	private ManualLottoTicketsFactory(final List<String> lottoTicketsInput) {
		this.lottoTicketsInput = lottoTicketsInput;
	}

	public static ManualLottoTicketsFactory of(final List<String> lottoTicketsInput) {
		return new ManualLottoTicketsFactory(lottoTicketsInput);
	}

	@Override
	public List<SerialLottoNumber> create() {
		return lottoTicketsInput.stream()
				.map(SerialLottoNumber::of)
				.collect(Collectors.toUnmodifiableList());
	}
}
