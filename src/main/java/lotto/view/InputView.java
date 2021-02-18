package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lotto.domain.lotto.LottoNumber;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static String getMoney() {
        try {
            System.out.println(InputViewMessages.REQUEST_PURCHASE_MONEY.getMessage());
            return scanner.nextLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getMoney();
        }
    }

    public static List<LottoNumber> getLottoLine() {
        try {
            System.out.println(InputViewMessages.REQUEST_LAST_WIN_LOTTO_NUMBERS.getMessage());
            String lottoNumbersInput = scanner.nextLine();
            String[] splitLottoNumbersInput = lottoNumbersInput.replace(" ", "").split(",");
            ArrayList<LottoNumber> lottoNumberList = new ArrayList();

            System.out.println(splitLottoNumbersInput.length);
            for (int i = 0; i < splitLottoNumbersInput.length; i++) {
                lottoNumberList.add(new LottoNumber(splitLottoNumbersInput[i]));
            }
            return lottoNumberList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getLottoLine();
        }
    }

    public static int getBonusLottoNumber() {
        try {
            System.out.println(InputViewMessages.REQUEST_LAST_WIN_BONUS_BALL.getMessage());
            int number = Integer.parseInt(scanner.nextLine());
            return number;
        } catch (Exception e) {
            System.out.printf(e.getMessage());
            return getBonusLottoNumber();
        }
    }

}