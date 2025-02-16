package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public String readPurchaseAmount() throws IOException {
        System.out.println("구입금액을 입력해 주세요.");
        return reader.readLine();
    }

    public String readWinningNumber() throws IOException {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return reader.readLine();
    }

    public String readBonusNumber() throws IOException {
        System.out.println("보너스 볼을 입력해 주세요.");
        return reader.readLine();
    }

    public void close() throws IOException {
        reader.close();
    }
}
