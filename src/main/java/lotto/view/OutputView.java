package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lotto.Money;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;

public class OutputView {

    public static void numPurchasedLotto(Integer numLotto) {
        System.out.println(numLotto + "개를 구매했습니다.");
    }

    public static void inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void lottosPrint(Lottos purchasedLottos) {
        for (Lotto lotto : purchasedLottos.getLottos()) {
            System.out.println(lotto.getNumbers()
                .stream()
                .map(LottoNumber::getValue)
                .collect(Collectors.toList()));
        }
    }

    public static void inputWinningNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void inputBonus() {
        System.out.println("보너스 번호를 입력해 주세요.");
    }
}
