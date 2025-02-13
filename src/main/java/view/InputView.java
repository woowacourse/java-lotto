package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import util.InputConverter;

public class InputView {
    public static int askMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            return InputConverter.convertNumber(br.readLine());
        }catch (IOException IOE) {
            throw new IllegalArgumentException("구입 금액 입력 오류");
        }
    }

}
