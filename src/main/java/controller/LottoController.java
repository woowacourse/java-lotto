package controller;

import domain.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoController {

    public Lottos purchase(int inputMoney, int manualLottoCount, List<String[]> manualLottoNumbers) {
        Money money = new Money(inputMoney);
        int autoLottoCount = money.getAutoLottoCount(manualLottoCount);
        List<LottoGenerator> lottoGenerators = getLottoGenerators(manualLottoNumbers, autoLottoCount);
        return Lottos.generateLottos(lottoGenerators);

    }

    private List<LottoGenerator> getLottoGenerators(List<String[]> manualLottoNumbers, int autoLottoCount) {
        List<LottoGenerator> lottoGenerators = manualLottoNumbers.stream()
                .map(ManualLottoGenerator::new)
                .collect(Collectors.toList());
        IntStream.range(0, autoLottoCount)
                .mapToObj(i -> new AutoLottoGenerator())
                .forEach(lottoGenerators::add);
        return lottoGenerators;
    }

    public Statistic winningResult(String[] inputWinningNumber, int inputBonusBall, Lottos lottos) {
        Lotto winningNumber = new ManualLottoGenerator(inputWinningNumber).generateLotto();
        LottoNumber bonusBall = LottoNumber.of(inputBonusBall);
        WinningLotto winningLotto = new WinningLotto(winningNumber, bonusBall);
        return lottos.getWinningStatistics(winningLotto);
    }
}
