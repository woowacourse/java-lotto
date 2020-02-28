package lotto.domain;

import lotto.domain.lotto.CountOfManualLotto;
import lotto.domain.lotto.Generator.AutoLottoGenerator;
import lotto.domain.lotto.Generator.ManualLottoGenerator;
import lotto.domain.lotto.Lottos;
import lotto.domain.lottonumber.NumberLinesOfManualLotto;

public class LottoMachine {
	private static final int DEFAULT_COUNT = 0;

	private Lottos allLottos;
	private int countOfAutoLottos;

	public LottoMachine() {
		allLottos = new Lottos();
		countOfAutoLottos = DEFAULT_COUNT;
	}

	public Lottos buyLottoTicket(int countOfAllLotto, CountOfManualLotto countOfManualLotto,
		NumberLinesOfManualLotto manualLottoNumbers) {
		int countOfAutoLottos = calculateCountOfAutoLottos(countOfAllLotto, countOfManualLotto);

		ManualLottoGenerator manualLottoGenerator = new ManualLottoGenerator(manualLottoNumbers);
		AutoLottoGenerator autoLottoGenerator = new AutoLottoGenerator(countOfAutoLottos);

		allLottos.add(manualLottoGenerator.generate());
		allLottos.add(autoLottoGenerator.generate());

		return allLottos;
	}

	public int calculateCountOfAutoLottos(int countOfAllLotto, CountOfManualLotto countOfManualLotto) {
		return countOfAllLotto - countOfManualLotto.getCountOfManualLotto();
	}

	public int getCountOfAutoLottos() {
		return countOfAutoLottos;
	}
}
