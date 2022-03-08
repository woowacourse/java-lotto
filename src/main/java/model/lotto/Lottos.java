package model.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.lottonumber.LottoNumbers;
import model.lottonumber.LottoNumbersGenerationStrategy;
import model.result.LottoResult;
import model.winningnumber.WinningLottoNumberDTO;

public class Lottos {
	private List<Lotto> lottoStorage;

	public Lottos(LottoCount manualLottoCount, LottoNumbersGenerationStrategy manualStrategy,
		LottoCount automaticLottoCount,
		LottoNumbersGenerationStrategy automaticStrategy) {
		lottoStorage = makeLottos(manualLottoCount, manualStrategy);
		lottoStorage.addAll(makeLottos(automaticLottoCount, automaticStrategy));
	}

	private List<Lotto> makeLottos(LottoCount lottoCount, LottoNumbersGenerationStrategy strategy) {
		return IntStream.range(0, lottoCount.getCount())
			.mapToObj((lottoNumbers) -> new Lotto(LottoNumbers.from(strategy)))
			.collect(Collectors.toList());
	}

	public LottoResult getResultOfLottos(WinningLottoNumberDTO winningLottoNumbersDTO) {
		LottoResult lottoResult = new LottoResult();
		lottoStorage.forEach(
			lotto -> lottoResult.increaseCountOfRank(lotto.match(winningLottoNumbersDTO)));
		return lottoResult;
	}

	public List<Lotto> getLottoStorage() {
		return new ArrayList<>(lottoStorage);
	}
}
