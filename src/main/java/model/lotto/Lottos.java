package model.lotto;

import java.util.ArrayList;
import java.util.List;

import model.bonusball.BonusBallDTO;
import model.lottonumber.RandomLottoNumbersGenerator;
import model.result.RateOfReturn;
import model.winningnumber.LottoWinningNumberDTO;

public class Lottos {
	private List<Lotto> lottoStorage;

	public Lottos(LottoCount lottoCount) {
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

	public List<LottoDTO> getLottosDTO() {
		List<LottoDTO> lottosDTOS = new ArrayList<>();
		lottoStorage.forEach(lotto -> lottosDTOS.add(lotto.getLottoDTO()));
		return lottosDTOS;
	}

	public void checkWithWinningNumberAndBonus(BonusBallDTO bonusBallDTO, LottoWinningNumberDTO winningNumbersDTO,
		RateOfReturn rateOfReturn) {
		lottoStorage.forEach(
			lotto -> rateOfReturn.increaseCountOfRank(lotto.match(bonusBallDTO, winningNumbersDTO)));
	}
}
