package lotto.model.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lotto.model.bonusball.BonusBallResponse;
import lotto.model.result.WinningResult;
import lotto.model.winningnumber.WinningNumberResponse;

public class LottoStorage {
    private final List<Lotto> lottoNumbers;

    public LottoStorage(LottoCount lottoCount) {
        this.lottoNumbers = store(lottoCount);
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
        lottoNumbers.forEach(lotto -> lottoResponses.add(new LottoResponse(lotto.getNumbers())));

        return Collections.unmodifiableList(lottoResponses);
    }

    public WinningResult calcWinningNumber(BonusBallResponse bonusBallResponse,
                                           WinningNumberResponse winningNumberResponse) {
        WinningResult winningResult = new WinningResult();
        lottoNumbers.forEach(lotto -> lotto.calcWinningNumber(winningResult, bonusBallResponse, winningNumberResponse));
        return winningResult;
    }
}
