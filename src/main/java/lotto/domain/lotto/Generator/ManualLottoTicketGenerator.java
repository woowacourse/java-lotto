package lotto.domain.lotto.Generator;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.lotto.Lotto;
import lotto.domain.number.NumberLinesOfManualLotto;

public class ManualLottoTicketGenerator implements LottoTicketGenerator {
	public static final String DELIMITER = ",";

	private List<String> numberLines;

	public ManualLottoTicketGenerator(NumberLinesOfManualLotto manualLottoNumbers) {
		numberLines = manualLottoNumbers.getNumberLines();
	}

	@Override
	public List<Lotto> generate() {
		List<Lotto> lottoTicket = new ArrayList<>();
		for (String numberLine : numberLines) {
			lottoTicket.add(Lotto.of(numberLine));
		}
		return lottoTicket;
	}
}
