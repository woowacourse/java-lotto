package view;

import domain.LottoNumbers;
import java.util.List;

public class OutputView {
    public static void printMoneyInstruction() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printAutoLotto(final List<LottoNumbers> issuedLotto) {
        System.out.println(issuedLotto.size() + "개를 구매했습니다.");
        for (LottoNumbers lottoNumbers : issuedLotto) {
            System.out.println(lottoNumbers.getLottoNumbers());
        }
    }

    public static void printWinNumbersInstruction() {
        System.out.println();
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void printBonusInstruction() {
        System.out.println();
        System.out.println("보너스 볼을 입력해 주세요.");
    }


}
