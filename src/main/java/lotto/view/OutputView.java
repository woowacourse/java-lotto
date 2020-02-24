package lotto.view;

import lotto.model.LottoRank;
import lotto.model.LottoTicket;
import lotto.model.LottoTickets;

public class OutputView {

    public static final String INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    public static final String LOTTO_COUNT_MESSAGE = "개를 구입했습니다.";
    public static final String INPUT_WIN_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    public static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
    public static final String RESULT_MESSAGE = "당첨 통계\n---------";
    public static final String CORRECT_MESSAGE = "개 일치 (";
    public static final String PRIZE_MESSAGE = "원) - ";
    public static final String COUNT_MESSAGE = "개";
    public static final String FIVE_BONUS_CORRECT_RESULT_MESSAGE = "5개 일치, 보너스 볼 일치(30000000원) - ";
    public static final String YIELD_MESSAGE = "총 수익률은 ";
    public static final String YIELD_PERCENT_MESSAGE = "%입니다.";

    public static void printinput() {
        System.out.println(INPUT_MESSAGE);
    }

    public static void printLottoCount(int count) {
        System.out.println(count + LOTTO_COUNT_MESSAGE);
    }

    public static void printAutoNumbers(LottoTickets lottoTickets) {
        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            System.out.println(lottoTicket.getLottoTicket());
        }
    }

    public static void printInputWinNumber() {
        System.out.println(INPUT_WIN_NUMBER_MESSAGE);
    }

    public static void printInputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
    }

    public static void printResult() {
        System.out.println(RESULT_MESSAGE);
    }

    public static void printCorrectResult(int count, LottoRank lottoRank) {
        if (lottoRank == LottoRank.SECOND) {
            System.out.println(FIVE_BONUS_CORRECT_RESULT_MESSAGE + count + COUNT_MESSAGE);
            return;
        }
        System.out.println(lottoRank.getRank() + CORRECT_MESSAGE + lottoRank.getPrize() + PRIZE_MESSAGE + count + COUNT_MESSAGE);
    }

    public static void printYield(int yield) {
        System.out.println(YIELD_MESSAGE + yield + YIELD_PERCENT_MESSAGE);
    }
}
