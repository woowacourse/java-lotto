package lotto.domain;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.lotto.CountOfManualLottoTicket;
import lotto.domain.lotto.Generator.AutoLottoTicketGenerator;
import lotto.domain.lotto.Generator.ManualLottoTicketGenerator;
import lotto.domain.lotto.Lotto;
import lotto.domain.money.Money;
import lotto.domain.number.NumberLinesOfManualLotto;

public class LottoMachine {
	public static final int LOTTO_PRICE = 1_000;
	private static final int LAST_COUNT = 0;

	private int countOfAllLotto;
	private int countOfAutoLottoTicket;
	private CountOfManualLottoTicket countOfManualLottoTicket;
	private List<Lotto> allLottoTicket;
	private int loopCountOfManualLotto;
	private Money money;

	public LottoMachine(String inputMoney, String inputCountOfManualLotto) {
		allLottoTicket = new ArrayList<>();
		money = new Money(inputMoney);

		countOfAllLotto = calculateCountOfLotto(money);
		countOfManualLottoTicket = new CountOfManualLottoTicket(inputCountOfManualLotto, countOfAllLotto);
		countOfAutoLottoTicket = countOfAllLotto - countOfManualLottoTicket.getCountOfManualLotto();
		loopCountOfManualLotto = countOfManualLottoTicket.getCountOfManualLotto();
	}

	private int calculateCountOfLotto(Money money) {
		return (int)money.getMoney() / LOTTO_PRICE;
	}

	public List<Lotto> buyLottoTicket(NumberLinesOfManualLotto manualLottoNumbers) {
		int countOfManualLottoTicket = manualLottoNumbers.size();
		countOfAutoLottoTicket = countOfAllLotto - countOfManualLottoTicket;

		ManualLottoTicketGenerator manualLottoTicketGenerator = new ManualLottoTicketGenerator(manualLottoNumbers);
		AutoLottoTicketGenerator autoLottoTicketGenerator = new AutoLottoTicketGenerator(countOfAutoLottoTicket);

		allLottoTicket.addAll(manualLottoTicketGenerator.generate());
		allLottoTicket.addAll(autoLottoTicketGenerator.generate());
		return allLottoTicket;
	}

	public boolean needMoreManualNumber() {
		if (loopCountOfManualLotto <= LAST_COUNT) {
			return false;
		}
		loopCountOfManualLotto--;
		return true;
	}

	public int getCountOfAutoLottoTicket() {
		return countOfAutoLottoTicket;
	}

	public int getCountOfManualLotto() {
		return countOfManualLottoTicket.getCountOfManualLotto();
	}

	public Money getMoney() {
		return money;
	}
}
