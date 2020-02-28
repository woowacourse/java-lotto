package lotto.domain;

import lotto.view.OutputView;
import lotto.view.errors.InvalidInputException;

class LottoService<V> {
    private LottoLogic<V> lottoLogic;

    LottoService(LottoLogic<V> lottoLogic) {
        this.lottoLogic = lottoLogic;
    }

    V executeOrRepeatWithException() {
        try {
            return lottoLogic.work();
        } catch (InvalidInputException e) {
            OutputView.printRetryRequestWithMessage(e.getMessage());
            return executeOrRepeatWithException();
        }
    }
}
