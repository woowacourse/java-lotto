package view;

import domain.Money;
import domain.LottoGameRepeat;
import domain.lottonumber.LottoNumber;
import domain.lottonumber.LottoTicket;
import domain.lottoresult.LottoRank;
import domain.lottoresult.LottoResult;
import domain.lottoresult.ResultCount;

import java.util.stream.Collectors;

public class OutputView {

    private OutputView() {
        throw new AssertionError();
    }

    public static void printMoneyFormat() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printWinnerNumbersFormat() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void printBonusNumberFormat() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public static void printUserLottoGameCountFormat(){
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
    }

    public static void printUserLottoNumbersFormat(){
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
    }

    public static void printRepeat(LottoGameRepeat repeat) {
        System.out.println(repeat.toString() + "개를 구매했습니다.");
    }

    public static void printRepeat(LottoGameRepeat userRepeat, LottoGameRepeat autoRepeat) {
        System.out.println("수동으로 " + userRepeat.toString() + "장, 자동으로 " + autoRepeat.toString() + "개를 구매했습니다.");
    }

    public static void printLottoNumbers(LottoTicket lottoTicket) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(lottoTicket.getLottoNumbers()
                .stream()
                .map(LottoNumber::toString)
                .collect(Collectors.joining(", "))
        );
        sb.append("]");
        System.out.println(sb.toString());
    }

    private static void printEachResult(LottoRank rank, ResultCount resultCount) {
        StringBuilder sb = new StringBuilder();
        if (rank == LottoRank.NOTHING) {
            return;
        }
        sb.append(rank.getHitCount());
        sb.append("개 일치");
        if (rank.hasBonus()) {
            sb.append(", 보너스 볼 일치");
        }
        sb.append("(");
        sb.append(rank.getWinning());
        sb.append("원)- ");
        sb.append(resultCount.toString());
        sb.append("개");
        System.out.println(sb.toString());
    }

    public static void printResult(Money money, LottoResult result) {
        System.out.println("\n당첨 통계");
        System.out.println("--------");
        for (LottoRank rank : LottoRank.values()) {
            printEachResult(rank, result.get(rank));
        }
        System.out.println("총 수익률은 " + money.calculateEarnings(result.calculateGamesEarning()) + "%입니다.");
    }
}
