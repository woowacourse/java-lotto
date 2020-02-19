package lotto.view;

import lotto.model.AutoNumber;
import lotto.model.LottoResult;

import java.util.List;

public class OutputView {
    public static void printinput() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printLottoCount(int count) {
        System.out.println(count + "개를 구입했습니다.");
    }

    public static void printAutoNumbers(List<AutoNumber> autoNumbers) {
        for (AutoNumber autoNumber : autoNumbers) {
            System.out.println(autoNumber.getAutoNumber());
        }
    }

    public static void printInputWinNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void printInputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public static void printResult() {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public static void printCorrectResult(int count, int correct, int prize) {
        System.out.println(correct + "개 일치 (" + prize + "원) - " + count+"개");
    }

    public static void printBonusCorrectResult(int count) {
        System.out.println("5개 일치, 보너스 볼 일치(30000000원) - " + count + "개");
    }

    public static void printYield(int yield) {
        System.out.println("총 수익률은 " + yield + "%입니다.");
    }
}
