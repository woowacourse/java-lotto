package view;

import com.sun.org.apache.xpath.internal.operations.String;
import domain.LottoNumber;
import domain.LottoNumbers;
import domain.LottoRank;
import domain.ResultCount;

import java.util.stream.Collectors;

public class OutputView {

    public static void printMoneyFormat() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printWinnerNumbersFormat() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void printBonusNumberFormat() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public static void printLottoNumbers(LottoNumbers lottoNumbers) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(lottoNumbers.getLottoNumbers()
                .stream()
                .map(LottoNumber::toString)
                .collect(Collectors.joining(", "))
        );
        sb.append("]");
        System.out.println(sb.toString());
    }

    public static void printResultTitle() {
        System.out.println("\n당첨 통계");
        System.out.println("--------");
    }

    public static void printResult(LottoRank rank, ResultCount resultCount) {
        StringBuilder sb = new StringBuilder();
        sb.append(rank.getHitCount());
        sb.append("개 일치 (");
        sb.append(rank.getWinning());
        sb.append("원)- ");
        sb.append(resultCount.toString());
        sb.append("개");
        System.out.println(sb.toString());
    }

    public static void printEarning(long rating) {
        System.out.println("총 수익률은 " + rating + "%입니다.");
    }
}
