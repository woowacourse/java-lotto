package lotto.model.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lotto.dto.LottoResponse;
import lotto.dto.WinningLottoResponse;
import lotto.model.lotto.result.WinningResult;

public class LottoStorage {
    private final List<Lotto> lottoNumbers;

    public LottoStorage(LottoCount lottoCount, List<Lotto> lottos) {
        this.lottoNumbers = store(lottoCount, lottos);
    }

    private List<Lotto> store(LottoCount lottoCount, List<Lotto> manualLottos) {
        List<Lotto> lottos = new ArrayList<>(manualLottos);
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

    WinningResult calcWinningNumber(WinningLottoResponse winningLottoResponse) {
        WinningResult winningResult = new WinningResult();
        lottoNumbers.forEach(lotto -> lotto.calcWinningNumber(winningResult, winningLottoResponse));
        return winningResult;
    }
}
