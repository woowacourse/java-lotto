package model.lotto;

import java.util.ArrayList;
import java.util.List;

import model.bonusball.BonusBallDTO;
import model.lottonumber.RandomLottoNumbersGenerator;
import model.result.RateOfReturn;
import model.winningnumber.LottoWinningNumberDTO;

public class LottoStorage {
	private List<Lotto> lottoStorage;

	public LottoStorage(LottoCount lottoCount) {
		this.lottoStorage = store(lottoCount);
	}

	private List<Lotto> store(LottoCount lottoCount) {
		List<Lotto> lottos = new ArrayList<>();

		while (lottoCount.haveRemainToMake()) {
			lottoCount.reduceCountOfRemain();
			lottos.add(new Lotto(RandomLottoNumbersGenerator.pickLottoNumbers()));
		}

		return lottos;
	}

	public List<LottoDTO> getLottoStorageDTO() {
		List<LottoDTO> lottoDTOS = new ArrayList<>();
		lottoStorage.forEach(lotto -> lottoDTOS.add(lotto.getLottoDTO()));
		return lottoDTOS;
	}

	public void checkWithWinningNumberAndBonus(BonusBallDTO bonusBallDTO, LottoWinningNumberDTO winningNumbersDTO,
		RateOfReturn rateOfReturn) {
		lottoStorage.forEach(
			lotto -> rateOfReturn.saveResult(lotto.match(bonusBallDTO, winningNumbersDTO)));
	}
}
