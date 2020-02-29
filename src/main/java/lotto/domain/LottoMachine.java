package lotto.domain;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.lotto.Generator.AutoLottoTicketGenerator;
import lotto.domain.lotto.Generator.ManualLottoTicketGenerator;
import lotto.domain.lotto.Lotto;
import lotto.domain.number.NumberLinesOfManualLotto;

public class LottoMachine {
	private static final int DEFAULT_COUNT = 0;

	private static List<Lotto> allLottoTicket;
	private static int countOfAutoLottoTicket;

	static {
		allLottoTicket = new ArrayList<>();
		countOfAutoLottoTicket = DEFAULT_COUNT;
	}

	public static List<Lotto> buyLottoTicket(int countOfAllLotto, NumberLinesOfManualLotto manualLottoNumbers) {
		int countOfManualLottoTicket = manualLottoNumbers.size();
		countOfAutoLottoTicket = calculateCountOfAutoLottoTicket(countOfAllLotto, countOfManualLottoTicket);

		ManualLottoTicketGenerator manualLottoTicketGenerator = new ManualLottoTicketGenerator(manualLottoNumbers);
		AutoLottoTicketGenerator autoLottoTicketGenerator = new AutoLottoTicketGenerator(countOfAutoLottoTicket);

		allLottoTicket.addAll(manualLottoTicketGenerator.generate());
		allLottoTicket.addAll(autoLottoTicketGenerator.generate());
		return allLottoTicket;
	}

	public static int calculateCountOfAutoLottoTicket(int countOfAllLotto, int countOfManualLottoTicket) {
		return countOfAllLotto - countOfManualLottoTicket;
	}

	public static int getCountOfAutoLottoTicket() {
		return countOfAutoLottoTicket;
	}
}
