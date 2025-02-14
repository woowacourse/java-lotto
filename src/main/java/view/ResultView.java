package view;

import static constant.WinLottoInfo.FIFTH;
import static constant.WinLottoInfo.FIRST;
import static constant.WinLottoInfo.FOURTH;
import static constant.WinLottoInfo.SECOND;
import static constant.WinLottoInfo.THIRD;

import constant.OutputMessage;
import model.PurchasedLottos;
import model.Result;

public class ResultView {
    public void printResult(Result result) {
        System.out.printf(OutputMessage.FIFTH_RESULT, FIFTH.getMatchNumberCount(), FIFTH.getPrice(),
                nullToZero(result.getCount(FIFTH)));
        System.out.printf(OutputMessage.FOURTH_RESULT, FOURTH.getMatchNumberCount(), FOURTH.getPrice(),
                nullToZero(result.getCount(FOURTH)));
        System.out.printf(OutputMessage.THIRD_RESULT, THIRD.getMatchNumberCount(), THIRD.getPrice(),
                nullToZero(result.getCount(THIRD)));
        System.out.printf(OutputMessage.SECOND_RESULT, SECOND.getMatchNumberCount(), SECOND.getPrice(),
                nullToZero(result.getCount(SECOND)));
        System.out.printf(OutputMessage.FIRST_RESULT, FIRST.getMatchNumberCount(), FIRST.getPrice(),
                nullToZero(result.getCount(FIRST)));
    }

    public void printTotalReturn(Result result, PurchasedLottos purchasedLottos) {
        System.out.printf(OutputMessage.TOTAL_RETURN, result.totalReturn(purchasedLottos.getPurchaseAmount()));
    }

    private Integer nullToZero(Integer number) {
        if (number == null) {
            return 0;
        }
        return number;
    }
}
