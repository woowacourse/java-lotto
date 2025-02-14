package view;

import static constant.WinLottoInfo.FIFTH;
import static constant.WinLottoInfo.FIRST;
import static constant.WinLottoInfo.FOURTH;
import static constant.WinLottoInfo.SECOND;
import static constant.WinLottoInfo.THIRD;

import constant.OutputMessage;
import model.LottoStatistics;
import model.PurchasedLottos;

public class ResultView {
    public void printResult(LottoStatistics lottoStatistics) {
        System.out.printf(OutputMessage.FIFTH_RESULT, FIFTH.getMatchNumberCount(), FIFTH.getPrice(),
                nullToZero(lottoStatistics.getCount(FIFTH)));
        System.out.printf(OutputMessage.FOURTH_RESULT, FOURTH.getMatchNumberCount(), FOURTH.getPrice(),
                nullToZero(lottoStatistics.getCount(FOURTH)));
        System.out.printf(OutputMessage.THIRD_RESULT, THIRD.getMatchNumberCount(), THIRD.getPrice(),
                nullToZero(lottoStatistics.getCount(THIRD)));
        System.out.printf(OutputMessage.SECOND_RESULT, SECOND.getMatchNumberCount(), SECOND.getPrice(),
                nullToZero(lottoStatistics.getCount(SECOND)));
        System.out.printf(OutputMessage.FIRST_RESULT, FIRST.getMatchNumberCount(), FIRST.getPrice(),
                nullToZero(lottoStatistics.getCount(FIRST)));
    }

    public void printTotalReturn(LottoStatistics lottoStatistics, PurchasedLottos purchasedLottos) {
        System.out.printf(OutputMessage.TOTAL_RETURN, lottoStatistics.totalReturn(purchasedLottos.getPurchaseAmount()));
    }

    private Integer nullToZero(Integer number) {
        if (number == null) {
            return 0;
        }
        return number;
    }
}
