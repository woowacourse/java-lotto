package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {
    public static int askMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            return Integer.parseInt(br.readLine());
        } catch (IOException IOE) {
            throw new IllegalArgumentException("구입 금액 입력 오류");
        } catch (NumberFormatException NE) {
            throw new NumberFormatException("구입 금액 타입 오류");
        }
    }

}
