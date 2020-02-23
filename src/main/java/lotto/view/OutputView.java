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
        System.out.printf("%d개를 구매했습니다.%n", lottoPieces);
    }

    public static void printChangeMoney(int changeMoney) {
        System.out.printf("거스름돈은 %d원 입니다.%n", changeMoney);
    }

    public static void printLottoTicket(LottoTickets lottoTickets) {
        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
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
        printWinningResultGuide(winningRank);
        if (winningRank == WinningRank.SECOND_RANK) {
            System.out.printf("%d개 일치, 보너스 볼 일치 (%d원) - %d개 %n", winningRank.getWinningBallCount(),
                    winningRank.getWinningMoney(), count);
            return;
        }
        System.out.printf("%d개 일치 (%d원) - %d개 %n", winningRank.getWinningBallCount(),
                winningRank.getWinningMoney(), count);
    }

    private static void printWinningResultGuide(WinningRank winningRank) {
        if (winningRank == WinningRank.FIRST_RANK) {
            System.out.println("당첨 통계\n---------");
        }
    }

    public static void printEarningRate(EarningRate earningRate) {
        System.out.printf("%n총 수익률은 %d%% 입니다.", earningRate.getEarningRate());
    }
}