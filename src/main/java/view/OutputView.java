package view;

import domain.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static final String NEW_LINE = System.lineSeparator();
    public static final java.lang.String COMMA = ", ";
    public static final int LAST_COMMA_REMOVER = 2;

    public static void printInputPurchaseAmountMessage() {
        System.out.println("구매금액을 입력해 주세요");
    }

    public static void printInputManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해주세요.");
    }

    public static void printInputManualLottoNumbers() {
        System.out.println("수동으로 구매할 로또 번호를 입력해주세요.");
    }

    public static void printLottoTickets(LottoTickets lottoTickets, LottoCount lottoCount) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.", lottoCount.getManualCount(), lottoCount.getAutoCount());
        System.out.println();
        for (Lotto eachLotto : lottoTickets.getLottoTickets()){
            printEachLotto(eachLotto);
        }
    }

    private static void printEachLotto(Lotto eachLotto) {
        String eachLottoNumbers = eachLotto.getLotto().stream()
                    .map(o -> o.toString())
                    .collect(Collectors.joining(COMMA, "[", "]"));
        System.out.println(eachLottoNumbers);
    }

    public static void printInputWinningNumbersMessage() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void printInputBonusNumberMessage() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public static void printResult(LottoResult lottoResult) {
        System.out.println("당첨 통계" + NEW_LINE + "------------");

        List<LottoRank> keys = Arrays.asList(LottoRank.values());
        Collections.reverse(keys);
        for (LottoRank rank : keys){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(rank.getResultMessage())
                        .append(lottoResult.getCount(rank))
                        .append("개");
            System.out.println(stringBuilder);
        }
    }

    public static void printProfitRatio(int profitRatio) {
        System.out.printf("총 수익률은 %d%%입니다.", profitRatio);
    }

    public static void printExceptionMessage(RuntimeException e) {
        System.out.println(e.getMessage());
        System.out.println("다시 입력해 주세요.");
    }
}
