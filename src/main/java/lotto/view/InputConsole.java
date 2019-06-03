package lotto.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputConsole {

    public static int inputMoney() {
        System.out.println("구입 금액을 입력해주세요.");
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException("숫자가 아닙니다. 금액은 숫자를 입력해야 합니다.");
        }
    }

    public static int inputNumberOfManualLotto() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException("숫자가 아닙니다.");
        }
    }

    public static List<Integer> inputLotto() {
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

    public static int inputBonusNumber() {
        System.out.println("\n보너스 볼을 입력해 주세요.");
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException("숫자가 아닙니다.");
        }
    }
}
