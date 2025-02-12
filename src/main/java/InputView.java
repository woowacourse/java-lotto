import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {
    private BufferedReader bufferedReader;

    public InputView() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public Money inputMoney() throws IOException {
        System.out.println("구입금액을 입력해 주세요.");
        return new Money(bufferedReader.readLine());
    }

}
