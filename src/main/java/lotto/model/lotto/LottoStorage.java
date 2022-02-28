package lotto.model.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lotto.model.bonusball.BonusBallResponse;
import lotto.model.result.WinningResult;
import lotto.model.winningnumber.LottoWinningNumberResponse;

public class LottoStorage {
    private final List<Lotto> lottoStorage;

    public LottoStorage(LottoCount lottoCount) {
        this.lottoStorage = store(lottoCount);
    }

    private List<Lotto> store(LottoCount lottoCount) {
        List<Lotto> lottos = new ArrayList<>();

        while (!lottoCount.isZero()) {
            lottos.add(new Lotto(RandomLottoNumbersGenerator.pickSixNumbers()));
            lottoCount.makeLotto();
        }
        return lottos;
    }

    public List<LottoResponse> getLottoStorage() {
        final List<LottoResponse> lottoResponses = new ArrayList<>();
        lottoStorage.forEach(lotto -> lottoResponses.add(new LottoResponse(lotto.getNumbers())));
        return Collections.unmodifiableList(lottoResponses);
    }

    public WinningResult calcWinningNumber(BonusBallResponse bonusBallResponse,
                                           LottoWinningNumberResponse winningNumberResponse) {
        WinningResult winningResult = new WinningResult();
        lottoStorage.forEach(lotto -> lotto.calcWinningNumber(winningResult, bonusBallResponse, winningNumberResponse));
        return winningResult;
    }
}
