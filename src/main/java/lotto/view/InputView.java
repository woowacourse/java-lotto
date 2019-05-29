package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static Scanner sc = new Scanner(System.in);
    /**
     * - 사용자는 수동으로 구매할 로또 수를 입력한다. -> InputView
     *     - 로또 수는 0이상 구입 한 로또 수 이하이다. -> SelfLotto
     *
     * - 사용자는 수동으로 구매할 번호를 입력한다. -> InputView
     *     - 로또번호는 6개를 입력해야한다. -> Lotto
     *     - 로또번호는 1~45사이의 수를 입력해야 한다. -> Number
     *     - 로또번호는 중복을 허용하지 않는다.   -> Lotto
     *
     * - 수동으로 고른 번호는 쉼표로 분리된다. -> InputView
     *     - 구분자는 무조건 쉼표여야만 한다.    -> InputView
     */

    public int inputPrice() {
        System.out.println("구입금액을 입력해 주세요.");
        return Integer.parseInt(sc.nextLine());
    }

    public int inputSelfCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return Integer.parseInt(sc.nextLine());
    }

    public List<String> inputSelfNumbers(int selfCount) {
        List<String> selfLottos = new ArrayList<>();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        for(int i = 0; i< selfCount; i++) {
            selfLottos.add(sc.nextLine());
        }
        return selfLottos;
    }

}
