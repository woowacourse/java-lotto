package lotto.service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.domain.WinnerLotto;
import lotto.domain.generator.AutoLottoGenerator;
import lotto.domain.generator.LottoGenerator;
import lotto.domain.generator.ManualLottoGenerator;
import lotto.domain.vo.LottoNumber;

public class LottoService {

    public List<Lotto> createAutoLottos(int autoLottosAmount) {
        LottoGenerator lottoGenerator = new AutoLottoGenerator(autoLottosAmount);
        return lottoGenerator.generateLottos();
    }

    public List<Lotto> createManulLottos(List<List<Integer>> manualNumbers) {
        LottoGenerator lottoGenerator = new ManualLottoGenerator(manualNumbers);
        return lottoGenerator.generateLottos();
    }

    public Map<Rank, Integer> match(Lottos lottos, List<Integer> winnerNumbers, int bonusNumber) {
        WinnerLotto winnerLotto = new WinnerLotto(Lotto.of(numbersToLottoNumbers(winnerNumbers)), LottoNumber.of(bonusNumber));
        return createRankResults(lottos.match(winnerLotto));
    }

    public double caluateRate(Map<Rank, Integer> result, double inputMoney) {
        return sumReward(result) / inputMoney;
    }

    private Map<Rank, Integer> createRankResults(List<Rank> ranks) {
        Map<Rank, Integer> rankResults = new EnumMap<>(Rank.class);
        for (Rank value : Rank.values()) {
            rankResults.put(value, value.findRewardCount(ranks));
        }
        return rankResults;
    }

    private List<LottoNumber> numbersToLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
            .map(LottoNumber::of)
            .collect(Collectors.toList());
    }

    private long sumReward(Map<Rank, Integer> result) {
        return result.keySet().stream()
            .mapToLong(rank -> rank.getReward() * result.get(rank))
            .sum();
    }
}
