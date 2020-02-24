package view;

import domain.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();
    private static final java.lang.String COMMA = ", ";
    private static final String PREFIX = "[";
    private static final String SUFFIX = "]";
    private static final String COUNT_UNIT = "개";
    private static final String DASHES = "------------";

    public static void printInputPurchaseAmountMessage() {
        System.out.println("구매금액을 입력해 주세요");
    }

    public static void printInputManualCountMessage() {
        System.out.println(NEW_LINE + "수동으로 구매할 로또 수를 입력해 주세요.");
    }

    public static void printInputManualLottoNumbersMessage() {
        System.out.println(NEW_LINE + "수동으로 구매할 번호를 입력해 주세요.");
    }

    public static void printPurchaseCountMessage(int lottoCount) {
        System.out.println(NEW_LINE + String.format("수동으로 %d장 자동으로 %d개를 구매했습니다.",
                ManualCount.getManualCount(), lottoCount - ManualCount.getManualCount()));
    }

    public static void printLottos() {
        for (Lotto eachLotto : Lottos.getLottos()){
            printEachLotto(eachLotto);
        }
    }

    private static void printEachLotto(final Lotto eachLotto) {
        String lotto = eachLotto.getLotto()
                .stream()
                .map(LottoNumber::toString)
                .collect(Collectors.joining(COMMA, PREFIX, SUFFIX));
        System.out.println(lotto);
    }

    public static void printInputWinningNumbersMessage() {
        System.out.println(NEW_LINE + "지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void printInputBonusNumberMessage() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public static void printResult() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(NEW_LINE)
                    .append("당첨 통계")
                    .append(NEW_LINE)
                    .append(DASHES)
                    .append(NEW_LINE);
        printResultDetail(stringBuilder);
    }

    private static void printResultDetail(StringBuilder stringBuilder) {
        List<LottoRank> keys = Arrays.asList(LottoRank.values());
        Collections.reverse(keys);
        for (LottoRank rank : keys){
            stringBuilder.append(rank.getResultMessage())
                    .append(LottoResult.getRankCount(rank))
                    .append(COUNT_UNIT)
                    .append(NEW_LINE);
        }
        System.out.println(stringBuilder);
    }

    public static void printProfitRatio(final int profitRatio) {
        System.out.printf("총 수익률은 %d%%입니다.", profitRatio);
    }

    public static void printExceptionMessage(RuntimeException e) {
        System.out.println(e.getMessage());
        System.out.println("다시 입력해 주세요.");
    }
}
