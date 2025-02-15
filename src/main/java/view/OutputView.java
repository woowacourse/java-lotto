package view;

import static model.WinLottoInfo.FIFTH;
import static model.WinLottoInfo.FIRST;
import static model.WinLottoInfo.FOURTH;
import static model.WinLottoInfo.SECOND;
import static model.WinLottoInfo.THIRD;

import model.LottoNumber;
import model.LottoNumbers;
import model.LottoStatistics;
import model.PurchasedLottos;

public class OutputView {
    public void printPurchaseResult(Integer purchaseCount) {
        System.out.printf("%d개를 구매했습니다.%n", purchaseCount);
    }

    public void printPurchasedLottos(PurchasedLottos purchasedLottos) {
        purchasedLottos.getLottos().forEach(this::printLottoNumbers);
    }

    private void printLottoNumbers(LottoNumbers lottoNumbers) {
        System.out.println(lottoNumbers.getNumbers().stream().map(LottoNumber::getNumber).toList());
    }

    public void printWinNumberGuide() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public void printBonusNumberGuide() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public void printResult(LottoStatistics lottoStatistics) {
        System.out.printf("%d개 일치 (%d원)- %d개%n", FIFTH.getMatchNumberCount(), FIFTH.getPrice(),
                nullToZero(lottoStatistics.getCount(FIFTH)));
        System.out.printf("%d개 일치 (%d원)- %d개%n", FOURTH.getMatchNumberCount(), FOURTH.getPrice(),
                nullToZero(lottoStatistics.getCount(FOURTH)));
        System.out.printf("%d개 일치 (%d원)- %d개%n", THIRD.getMatchNumberCount(), THIRD.getPrice(),
                nullToZero(lottoStatistics.getCount(THIRD)));
        System.out.printf("%d개 일치, 보너스 볼 일치(%d원) - %d개%n", SECOND.getMatchNumberCount(), SECOND.getPrice(),
                nullToZero(lottoStatistics.getCount(SECOND)));
        System.out.printf("%d개 일치 (%d원)- %d개%n", FIRST.getMatchNumberCount(), FIRST.getPrice(),
                nullToZero(lottoStatistics.getCount(FIRST)));
    }

    public void printTotalReturn(LottoStatistics lottoStatistics, PurchasedLottos purchasedLottos) {
        System.out.printf("총 수익률은 %.2f입니다.%n", lottoStatistics.totalReturn(purchasedLottos.getPurchaseAmount()));
    }

    private Integer nullToZero(Integer number) {
        if (number == null) {
            return 0;
        }
        return number;
    }
}
