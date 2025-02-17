package lotto.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public String purchasePriceInput() throws IOException {
        System.out.println("구입금액을 입력해 주세요.");
        return br.readLine();
    }

    public String winningNumberInput() throws IOException {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return br.readLine();
    }

    public String bonusNumberInput() throws IOException {
        System.out.println("보너스 볼을 입력해 주세요.");
        return br.readLine();
    }
}
