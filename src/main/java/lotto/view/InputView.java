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
}
