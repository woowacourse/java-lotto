package lotto.view;

import java.util.Map.Entry;
import java.util.stream.Collectors;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoRank;
import lotto.domain.lotto.LottoResult;
import lotto.domain.lotto.Lottos;

public class OutputView {

    private String messageSkeleton;
    public static final String RESULT_MESSAGE_SKELETON = "%d개 일치%s(%d원) - %d개";

    public static void numPurchasedLotto(Integer numLotto) {
        System.out.println(numLotto + "개를 구매했습니다.");
    }

    public static void inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void lottosPrint(Lottos purchasedLottos) {
        for (Lotto lotto : purchasedLottos.getLottos()) {
            System.out.println(lotto.getNumbers()
                .stream()
                .map(LottoNumber::getValue)
                .collect(Collectors.toList()));
        }
    }

    public static void inputWinningNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void inputBonus() {
        System.out.println("보너스 번호를 입력해 주세요.");
    }

    public static void totalWinning() {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public static void numMatchPrint(LottoResult lottoResult) {
        for (Entry<LottoRank, Integer> entrySet : lottoResult.getRank().entrySet()) {
            printResult(entrySet);
        }

    }

    private static void printResult(Entry<LottoRank, Integer> entrySet) {

        if (entrySet.getKey().getWinningMoney() == 0) {
            return;
        }
        if (entrySet.getKey() == LottoRank.SECOND) {
            bonusPrint(entrySet);
            return;
        }
        System.out.println(String.format(RESULT_MESSAGE_SKELETON
            , entrySet.getKey().getNumMatch()
            , ""
            , entrySet.getKey().getWinningMoney()
            , entrySet.getValue()));
    }

    private static void bonusPrint(Entry<LottoRank, Integer> entrySet) {
        System.out.println(String.format(RESULT_MESSAGE_SKELETON
            , entrySet.getKey().getNumMatch()
            , ", 보너스 볼 일치"
            , entrySet.getKey().getWinningMoney()
            , entrySet.getValue()));
    }

    public static void profitRatePrint(LottoResult lottoResult) {
        final String profitRateMessageSkeleton = "총 수익률은 %.2f 입니다.";
        System.out.println(String.format(profitRateMessageSkeleton, lottoResult.getProfitRate()));
    }

    public static void errorPrint(Exception error) {
        System.out.println(error.getMessage());
    }
}
