package lotto.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    public static String inputMoney() {
        System.out.println("구입 금액을 입력해주세요.");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static List<Integer> inputWinningLotto() {
        System.out.println("지난 주 당첨 번호를 입력해주세요.");
        Scanner scanner = new Scanner(System.in);
        List<String> inputLottoNumbers = Arrays.asList((scanner.nextLine()).split(","));
        List<Integer> lottoNumbers = new ArrayList<>();
        for (String inputLottoNumber : inputLottoNumbers) {
            try {
                lottoNumbers.add(Integer.parseInt(inputLottoNumber.trim()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("로또 번호는 숫자를 입력해야 합니다.");
            }
        }
        return lottoNumbers;
    }


}
