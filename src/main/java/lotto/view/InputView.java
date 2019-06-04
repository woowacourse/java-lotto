package lotto.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String SEPERATOR = ",";
    private static Scanner reader = new Scanner(System.in);

    public static int askMoney() {
        System.out.println("구입 금액을 입력해 주세요!");
        return Integer.parseInt(reader.nextLine());
    }

    public static List<Integer> askWinningLottoNumbers() {
        System.out.println("우승 당첨 번호를 입력하세요");
        List<String> inputs = Arrays.asList(reader.nextLine().split(SEPERATOR));
        List<Integer> lottoNumbers = new ArrayList<>();

        inputs.forEach(input -> lottoNumbers.add(Integer.parseInt(input)));
        return lottoNumbers;
    }


    public static int askBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String userInput = reader.nextLine();
        return Integer.parseInt(userInput);
    }

    public static int askManualLottoCount() {
        System.out.println("몇개를 수동구매 하시겠습니까?");
        String userInput = reader.nextLine();
        return Integer.parseInt(userInput);
    }

    public static List<Integer> askManualLottoNumbers() {
        System.out.println("수동구매 로또 번호를 입력하세요");
        List<String> inputs = Arrays.asList(reader.nextLine().split(SEPERATOR));
        List<Integer> lottoNumbers = new ArrayList<>();

        inputs.forEach(input -> lottoNumbers.add(Integer.parseInt(input)));
        return lottoNumbers;
    }
}
