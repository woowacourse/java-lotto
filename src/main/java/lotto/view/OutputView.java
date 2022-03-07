package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;

public class OutputView {
    public void printAskMoneyInputMessage() {
        System.out.println("구입금액을 입력해주세요.");
    }

    public void printErrorMessage(String e) {
        System.out.println(e);
    }

    public void printLastWeekWinningMessage() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public void printLastWeekBonusMessage() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public void printPurchasedLotto(Lottos lottos) {
        int manualLottoCount = lottos.getManualLottoCount();
        System.out.printf("수동으로 %d개, 자동으로 %d개를 구매했습니다.\n", manualLottoCount, lottos.getLottosSize() - manualLottoCount);
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.getPickedNumbers());
        }
    }

    public void printResult(LottoResult result) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        result.getResult().remove(LottoRank.RANK_NOTHING);
        for (LottoRank lottoRank : result.getResult().keySet()) {
            System.out.printf("%d개 일치%s(%d원) - %d개\n", lottoRank.getCorrectNumber(),
                    getBonusMessage(lottoRank.isBonused()), lottoRank.getPrizeAmount(),
                    result.getResult().get(lottoRank));
        }
    }

    private String getBonusMessage(boolean isBonused) {
        if (isBonused) {
            return ", 보너스 볼 일치";
        }
        return " ";
    }

    public void printYield(double yield) {
        System.out.printf("총 수익률은 %.2f입니다.\n", yield);

    }
  
    public void printAskManualPurchaseCountInputMessage() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
    }

    public void printManualPurchaseInputMessage() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }
}
