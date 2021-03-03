package view;

import domain.*;
import domain.lottoGame.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static Money inputPurchaseMoney() {
        System.out.println("구입 금액을 입력해 주세요.");
        long money = Long.parseLong(SCANNER.nextLine());
        return new Money(money);
    }

    public static Lottos inputManualPurchaseLottos() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요");
        int count = Integer.parseInt(SCANNER.nextLine());

        System.out.println("수동으로 구매할 로또 번호를 입력해 주세요");
        List<Lotto> manualPurchaseLottos = IntStream.range(0, count)
                .mapToObj(i -> inputLotto())
                .collect(Collectors.toList());

        return new Lottos(manualPurchaseLottos);
    }

    public static WinningLotto inputWinningLotto() {
        return new WinningLotto(inputLastWinningLotto(), inputBonusBall());
    }

    private static Lotto inputLastWinningLotto() {
        System.out.println("지난 주 당첨 로또를 입력해주세요.");
        return inputLotto();
    }

    private static LottoNumber inputBonusBall() {
        System.out.println("보너스 볼을 입력해주세요.");
        return LottoNumber.from(SCANNER.nextInt());
    }

    private static Lotto inputLotto() {
        String input = SCANNER.nextLine();
        List<LottoNumber> lottoNumbers = Arrays.stream(input.split(", "))
                .map(Integer::parseInt)
                .map(LottoNumber::from)
                .collect(Collectors.toList());

        return new Lotto(new LottoNumbers(lottoNumbers));
    }
}
