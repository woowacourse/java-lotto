package lotto.generator;

import lotto.domain.*;

import java.util.*;

public class LottoGenerateManager {
    private static final List<LottoGenerator> lottoGenerators = Arrays.asList(new ManualLottoGenerator(), new AutoLottoGenerator());
    private final BettingInfo bettingInfo;

    public LottoGenerateManager(BettingInfo bettingInfo) {
        this.bettingInfo = bettingInfo;
    }

    public Lottos createLottos() {
        Lottos lottos = new Lottos();
        for (LottoGenerator lottoGenerator : lottoGenerators) {
            lottos.addLottos(lottoGenerator.createLottos(this.bettingInfo));
        }
        return lottos;
    }

    public static WinningLotto createWinningLotto(List<String> lottoNumbers, String bonusNumber) {
        Lotto lotto = ManualLottoGenerator.createLotto(lottoNumbers);
        return new WinningLotto(lotto, LottoNumber.valueOf(bonusNumber));
    }
}
