package view;

import static model.WinLottoInfo.FIFTH;
import static model.WinLottoInfo.FIRST;
import static model.WinLottoInfo.FOURTH;
import static model.WinLottoInfo.SECOND;
import static model.WinLottoInfo.THIRD;

import model.Purchase;
import model.Result;

public class ResultView {

    public static final String NUMBER_COUNT_EXCEPTION = "6개의 숫자를 입력해주세요.";
    public static final String NUMBER_BOUND_EXCEPTION = "1~45 사이의 숫자를 입력해주세요.";
    public static final String NUMBER_DUPLICATE_EXCEPTION = "중복이 아닌 숫자를 입력해주세요";
    public static final String BONUS_NUMBER_DUPLICATE_EXCEPTION = "당첨 번호와 중복이 아닌 숫자를 입력해주세요";
    public static final String PURCHASE_RESULT = "%d개를 구매했습니다.%n";
    public static final String FIRST_RESULT = "%d개 일치 (%d원)- %d개%n";
    public static final String SECOND_RESULT = "%d개 일치, 보너스 볼 일치(%d원) - %d개%n";
    public static final String THIRD_RESULT = "%d개 일치 (%d원)- %d개%n";
    public static final String FOURTH_RESULT = "%d개 일치 (%d원)- %d개%n";
    public static final String FIFTH_RESULT = "%d개 일치 (%d원)- %d개%n";
    public static final String TOTAL_Result = "총 수익률은 %.2f입니다.%n";

    public void printResult(Result result) {
        System.out.printf(FIFTH_RESULT, FIFTH.getMatchNumberCount(), FIFTH.getPrice(),
                nullToZero(result.getCount(FIFTH)));
        System.out.printf(FOURTH_RESULT, FOURTH.getMatchNumberCount(), FOURTH.getPrice(),
                nullToZero(result.getCount(FOURTH)));
        System.out.printf(THIRD_RESULT, THIRD.getMatchNumberCount(), THIRD.getPrice(),
                nullToZero(result.getCount(THIRD)));
        System.out.printf(SECOND_RESULT, SECOND.getMatchNumberCount(), SECOND.getPrice(),
                nullToZero(result.getCount(SECOND)));
        System.out.printf(FIRST_RESULT, FIRST.getMatchNumberCount(), FIRST.getPrice(),
                nullToZero(result.getCount(FIRST)));
    }

    public void printTotalResult(Result result, Purchase purchase) {
        System.out.printf(TOTAL_Result, result.totalReturn(purchase.getPurchaseAmount()));
    }

    private Integer nullToZero(Integer number) {
        if (number == null) {
            return 0;
        }
        return number;
    }
}
