package lotto.domain;

import lotto.view.OutputView;

public class LottoService<V> {
    private LottoLogic<V> lottoLogic;

    LottoService(LottoLogic<V> lottoLogic) {
        this.lottoLogic = lottoLogic;
    }

    V executeOrRepeatWithException() {
        try {
            return lottoLogic.work();
        } catch (RuntimeException e) {
            OutputView.printRetryRequestWithMessage(e.getMessage());
            return executeOrRepeatWithException();
        }
    }
}
