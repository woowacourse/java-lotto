package lotto.service;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoBuyMoney;
import lotto.domain.Lottos;
import lotto.domain.Statistics;
import lotto.domain.WinnerLotto;
import lotto.domain.generator.AutoLottoGenerator;
import lotto.domain.generator.LottoGenerator;
import lotto.domain.generator.ManualLottoGenerator;
import lotto.domain.vo.LottoNumber;

public class LottoService {

    public Lottos createLottos(LottoBuyMoney lottoBuyMoney, int manualAmount, List<List<Integer>> manualNumbers) {
        int autoLottoAmount = lottoBuyMoney.countAutoAmountByManualAmount(manualAmount);
        List<Lotto> lottos = createManualLottos(manualNumbers);
        lottos.addAll(createAutoLottos(autoLottoAmount));
        return new Lottos(lottos);
    }

    public Statistics match(List<List<Integer>> lottoNumbers, List<Integer> winnerNumbers, int bonusNumber) {
        List<List<LottoNumber>> lists = collectNumbersToCollectLottoNumbers(lottoNumbers);
        Lottos lottos = new Lottos(numbersToLottos(lists));
        return Statistics.of(lottos.match(createWinningLotto(winnerNumbers, bonusNumber)));
    }

    public double getProfitRate(Statistics statistics, int inputMoney) {
        return statistics.calculateProfitRate(inputMoney);
    }

    private List<Lotto> createManualLottos(List<List<Integer>> manualNumbers) {
        LottoGenerator lottoGenerator = new ManualLottoGenerator(collectNumbersToCollectLottoNumbers(manualNumbers));
        return lottoGenerator.generateLottos();
    }

    private List<Lotto> createAutoLottos(int autoLottosAmount) {
        LottoGenerator lottoGenerator = new AutoLottoGenerator(autoLottosAmount);
        return lottoGenerator.generateLottos();
    }

    private WinnerLotto createWinningLotto(List<Integer> winnerNumbers, int bonusNumber) {
        return new WinnerLotto(Lotto.of(numbersToLottoNumbers(winnerNumbers)), LottoNumber.of(bonusNumber));
    }

    private List<List<LottoNumber>> collectNumbersToCollectLottoNumbers(List<List<Integer>> collectNumbers) {
        return collectNumbers.stream()
            .map(this::numbersToLottoNumbers)
            .collect(Collectors.toList());
    }

    private List<LottoNumber> numbersToLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
            .map(LottoNumber::of)
            .collect(Collectors.toList());
    }

    private List<Lotto> numbersToLottos(List<List<LottoNumber>> lists) {
        return lists.stream()
            .map(Lotto::of)
            .collect(Collectors.toList());
    }
}
