package lotto.domain;

import lotto.domain.lotto.CountOfManualLottoTicket;
import lotto.domain.lotto.Generator.AutoLottoTicketGenerator;
import lotto.domain.lotto.Generator.ManualLottoTicketGenerator;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.number.NumberLinesOfManualLotto;

public class LottoMachine {
	private static final int DEFAULT_COUNT = 0;

	private LottoTicket allLottoTicket;
	private int countOfAutoLottoTicket;

	public LottoMachine() {
		allLottoTicket = new LottoTicket();
		countOfAutoLottoTicket = DEFAULT_COUNT;
	}

	public LottoTicket buyLottoTicket(int countOfAllLotto, NumberLinesOfManualLotto manualLottoNumbers) {
		int countOfManualLottoTicket = manualLottoNumbers.size();
		int countOfAutoLottoTicket = calculateCountOfAutoLottoTicket(countOfAllLotto, countOfManualLottoTicket);

		ManualLottoTicketGenerator manualLottoTicketGenerator = new ManualLottoTicketGenerator(manualLottoNumbers);
		AutoLottoTicketGenerator autoLottoTicketGenerator = new AutoLottoTicketGenerator(countOfAutoLottoTicket);

		allLottoTicket.add(manualLottoTicketGenerator.generate());
		allLottoTicket.add(autoLottoTicketGenerator.generate());

		return allLottoTicket;
	}

	public int calculateCountOfAutoLottoTicket(int countOfAllLotto, int countOfManualLottoTicket) {
		return countOfAllLotto - countOfManualLottoTicket;
	}

	public int getCountOfAutoLottoTicket() {
		return countOfAutoLottoTicket;
	}
}
