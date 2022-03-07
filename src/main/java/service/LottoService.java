package service;

import domain.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoService {

    public Money createMoney(int value) {
        return new Money(value);
    }

    public int getAutoLottoCount(Money money, int manualLottoCount) {
        return money.getAutoLottoCount(manualLottoCount);
    }

    public Lottos generateLottos(List<String[]> manualLottoNumbers, int autoLottoCount) {
        List<LottoGenerator> lottoGenerators = manualLottoNumbers.stream()
                .map(ManualLottoGenerator::new)
                .collect(Collectors.toList());
        IntStream.range(0, autoLottoCount)
                .mapToObj(i -> new AutoLottoGenerator())
                .forEach(lottoGenerators::add);
        return Lottos.generateLottos(lottoGenerators);
    }

    public WinningLotto generateWinningLotto(LottoGenerator lottoGenerator, int inputBonusBall) {
        Lotto lotto = lottoGenerator.generateLotto();
        LottoNumber bonusBall = LottoNumber.of(inputBonusBall);

        return new WinningLotto(lotto, bonusBall);
    }
}
