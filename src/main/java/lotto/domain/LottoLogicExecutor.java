package lotto.domain;

import lotto.view.OutputView;

class LottoLogicExecutor {

    static <T> T executeOrRepeatWithException(LottoLogic<T> lottoLogic) {
        try {
            return lottoLogic.work();
        } catch (IllegalArgumentException e) {
            OutputView.printRetryRequestWithMessage(e.getMessage());
            return executeOrRepeatWithException(lottoLogic);
        }
    }
}
