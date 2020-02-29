package lotto.domain.lotto.Generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.lotto.Lotto;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.NumberLinesOfManualLotto;

public class ManualLottoTicketGenerator implements LottoTicketGenerator {
	public static final String DELIMITER = ",";

	private List<String> numberLines;

	public ManualLottoTicketGenerator(NumberLinesOfManualLotto manualLottoNumbers) {
		numberLines = manualLottoNumbers.getNumberLines();
	}

	private Lotto createLotto(String numberLine) {
		return new Lotto(Arrays.stream(numberLine.split(DELIMITER))
			.map(String::trim)
			.map(Integer::parseInt)
			.map(LottoNumber::valueOf)
			.collect(Collectors.toSet()));
	}

	@Override
	public List<Lotto> generate() {
		List<Lotto> lottoTicket = new ArrayList<>();
		for (String numberLine : numberLines) {
			lottoTicket.add(createLotto(numberLine));
		}
		return lottoTicket;
	}
}
