package view;

import static exception.ErrorMessage.SYSTEM_ERROR;

import exception.LottoException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import util.InputConverter;

public class InputView {
    public static int askMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            return InputConverter.convertNumber(br.readLine());
        } catch (IOException IOE) {
            throw LottoException.from(SYSTEM_ERROR);
        }
    }

    public static List<Integer> askWinningLotto() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            return InputConverter.convertWinningLotto(br.readLine());
        } catch (IOException IOE) {
            throw LottoException.from(SYSTEM_ERROR);
        }
    }

    public static Integer askBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            return InputConverter.convertNumber(br.readLine());
        } catch (IOException IOE) {
            throw LottoException.from(SYSTEM_ERROR);
        }
    }


}
