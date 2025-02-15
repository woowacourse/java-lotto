package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {
    private static final String ERROR_IOException = "입출력 오류 발생: ";

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public String readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return readLine();
    }

    public String readWinningNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return readLine();
    }

    public String readBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return readLine();
    }

    private String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.out.println(ERROR_IOException + e.getMessage());
        } finally {
            close();
        }
        return null;
    }

    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println(ERROR_IOException + e.getMessage());
        }
    }
}
