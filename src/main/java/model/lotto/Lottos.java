package model.lotto;

import java.util.ArrayList;
import java.util.List;

import model.lottonumber.LottoNumbers;
import model.lottonumber.generationstrategy.RandomLottoNumbersGenerationStrategy;
import model.result.LottoResult;
import model.winningnumber.WinningLottoNumberDTO;

public class Lottos {
	private List<Lotto> lottoStorage;

	public Lottos(LottoCount lottoCount) {
		this.lottoStorage = makeLottos(lottoCount);
	}

	private List<Lotto> makeLottos(LottoCount lottoCount) {
		List<Lotto> lottos = new ArrayList<>();

		while (lottoCount.haveRemainToMake()) {
			lottoCount.increaseMadeLottoCount();
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
		LottoResult lottoResult) {
		lottoStorage.forEach(
			lotto -> lottoResult.increaseCountOfRank(lotto.match(winningLottoNumbersDTO)));
	}

	public void add(List<LottoNumbers> passiveLottos) {
		for (LottoNumbers passiveLotto : passiveLottos) {
			lottoStorage.add(passiveLottos.indexOf(passiveLotto), new Lotto(passiveLotto));
		}
	}
}
