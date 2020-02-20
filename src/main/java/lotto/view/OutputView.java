package lotto.view;

import lotto.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static void printErrorMessage(String errorMessage) {
        System.err.println(errorMessage);
    }

    public static void printStartGuide() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printLottePieces(int lottoPieces) {
        System.out.println(lottoPieces + "개를 구매했습니다.");
    }

    public static void printChangeMoney(int changeMoney) {
        System.out.println("거스름돈은 " + changeMoney + "원 입니다.");
    }

    public static void printLottoTicket() {
        for (LottoTicket lottoTicket : LottoTickets.getLottoTickets()) {
            System.out.println(lottoTicket.getLottoTicket()
                    .stream()
                    .map(LottoBall::getLottoNumber)
                    .collect(Collectors.toList()));
        }
    }

    public static void printAnswerWinningBalls() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void printAnswerBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }


    public static void printEachWinningResult(WinningRank winningRank, int count) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(winningRank.getWinningBallCount()).append("개 일치(");

        if (winningRank.getWinningMoney() == WinningRank.SECOND_RANK.getWinningMoney()) {
            stringBuilder.append(", 보너스 볼 일치 (");
        }
        stringBuilder.append(+winningRank.getWinningMoney()).append("원) - ").append(count).append("개");

        System.out.println(stringBuilder);
    }

    public static void printResultAllOfRank(List<WinningRank> winningRanks, EarningRate earningRate) {
        for (WinningRank winningRank : WinningRank.values()) {
            OutputView.printEachWinningResult(winningRank, earningRate.countRankPeople(winningRanks, winningRank));
        }
    }

    public static void printRankPrintedConstant() {
        System.out.println("당첨통계");
        System.out.println("---------");
    }


    public static void printEarningRate(EarningRate earningRate) {
        System.out.println("총 수익률은 " + earningRate.getEarningRate() + "% 입니다.");
    }
}
