package model.lotto;

import java.util.ArrayList;
import java.util.List;

import model.lottonumber.LottoNumbers;
import model.lottonumber.RandomLottoNumbersGenerationStrategy;
import model.result.RateOfReturn;
import model.winningnumber.WinningLottoNumberDTO;

public class Lottos {
	private List<Lotto> lottoStorage;

	public Lottos(LottoCount lottoCount) {
		this.lottoStorage = store(lottoCount);
	}

	private List<Lotto> store(LottoCount lottoCount) {
		List<Lotto> lottos = new ArrayList<>();

		while (lottoCount.haveRemainToMake()) {
			lottoCount.reduceCountOfRemain();
			lottos.add(new Lotto(LottoNumbers.from(new RandomLottoNumbersGenerationStrategy())));
		}

		return lottos;
	}

	public List<LottoDTO> getLottosDTO() {
		List<LottoDTO> lottosDTOS = new ArrayList<>();
		lottoStorage.forEach(lotto -> lottosDTOS.add(lotto.getLottoDTO()));
		return lottosDTOS;
	}

	public void checkWithWinningNumberAndBonus(WinningLottoNumberDTO winningLottoNumbersDTO,
		RateOfReturn rateOfReturn) {
		lottoStorage.forEach(
			lotto -> rateOfReturn.increaseCountOfRank(lotto.match(winningLottoNumbersDTO)));
	}

	public void add(List<LottoNumbers> passiveLottos) {
		for (LottoNumbers passiveLotto : passiveLottos) {
			lottoStorage.add(passiveLottos.indexOf(passiveLotto), new Lotto(passiveLotto));
		}
	}
}
