package lotto.view;

import lotto.domain.*;

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

    public static void printWinningResult(WinningRank winningRank, int count) {
        StringBuilder stringBuilder = new StringBuilder();
        printWinningResultGuide(winningRank);
        stringBuilder.append(winningRank.getWinningBallCount())
                .append("개 일치");
        printBonusBall(winningRank, stringBuilder);
        stringBuilder.append(" (")
                .append(winningRank.getWinningMoney())
                .append("원) - ")
                .append(count)
                .append("개");
        System.out.println(stringBuilder);
    }

    private static void printBonusBall(WinningRank winningRank, StringBuilder stringBuilder) {
        if (winningRank == WinningRank.SECOND_RANK) {
            stringBuilder.append(", 보너스 볼 일치");
        }
    }

    private static void printWinningResultGuide(WinningRank winningRank) {
        if (winningRank == WinningRank.FIRST_RANK) {
            System.out.println("당첨 통계\n---------");
        }
    }

    public static void printEarningRate(EarningRate earningRate) {
        System.out.println("\n총 수익률은 " + earningRate.getEarningRate() + "% 입니다.");
    }
}