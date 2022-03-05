package model.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.lottonumber.LottoNumbers;
import model.lottonumber.generationstrategy.LottoNumbersGenerationStrategy;
import model.lottonumber.generationstrategy.RandomLottoNumbersGenerationStrategy;
import model.result.LottoResult;
import model.winningnumber.WinningLottoNumberDTO;

public class Lottos {
	private List<Lotto> lottoStorage;

	public Lottos(LottoCount lottoCount) {
		this.lottoStorage = makeLottos(lottoCount);
	}

	private List<Lotto> makeLottos(LottoCount lottoCount) {
		LottoNumbersGenerationStrategy strategy = new RandomLottoNumbersGenerationStrategy();

		return IntStream.range(0, lottoCount.getCount())
			.mapToObj((lottoNumbers) -> new Lotto(LottoNumbers.from(strategy)))
			.collect(Collectors.toList());
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

	public void add(List<Lotto> passiveLottos) {
		for (Lotto passiveLotto : passiveLottos) {
			lottoStorage.add(passiveLottos.indexOf(passiveLotto), passiveLotto);
		}
	}

	public List<Lotto> getLottoStorage() {
		return lottoStorage;
	}
}
