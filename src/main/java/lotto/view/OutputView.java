package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;

public class OutputView {
    public void printAskMoneyInputMessage() {
        System.out.println("구입금액을 입력해주세요.");
    }

    public void printErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public void printLastWeekWinningMessage() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public void printLastWeekBonusMessage() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public void printPurchasedLotto(Lottos lottos) {
        System.out.println(String.format("%d개를 구매했습니다.", lottos.getLottosSize()));
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.getChoiceNumber());
        }
    }

    public void printResult(LottoResult result) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        for (LottoRank lottoRank : result.getResult().keySet()) {
            String message = String.format("%d개 일치%s(%d원) - %d개", lottoRank.getCorrectNumber(),
                    getBonusMessage(lottoRank.getIsBonused()), lottoRank.getPrizeAmount(),
                    result.getResult().get(lottoRank));
            System.out.println(message);
        }
    }

    private String getBonusMessage(boolean isBonused) {
        if (isBonused) {
            return ", 보너스 볼 일치";
        }
        return " ";
    }

    public void printYield(double yield) {
        System.out.println(String.format("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)", yield));

    }
}
