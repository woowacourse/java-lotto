package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lotto.domain.Money;
import lotto.domain.lotto.LottoLine;
import lotto.domain.lotto.LottoNumber;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static Money getMoney() {
        try {
            System.out.println(InputViewMessages.REQUEST_PURCHASE_MONEY.getMessage());
            int inputValue = Integer.parseInt(scanner.nextLine());
            return new Money(inputValue);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getMoney();
        }
    }

    public static LottoLine getLottoLine() {
        try {
            System.out.println(InputViewMessages.REQUEST_LAST_WIN_LOTTO_NUMBERS.getMessage());
            String inputValue = scanner.nextLine();
            List<LottoNumber> lottoNumbers = parseLottoNumber(inputValue);
            return new LottoLine(lottoNumbers);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getLottoLine();
        }
    }

    private static List<LottoNumber> parseLottoNumber(String inputValue){
        String[] parsedInputValue = inputValue.replace(" ", "").split(",");
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (String s : parsedInputValue) {
            lottoNumbers.add(new LottoNumber(Integer.parseInt(s)));
        }
        return lottoNumbers;
    }

    public static LottoNumber getBonusLottoNumber() {
        try {
            System.out.println(InputViewMessages.REQUEST_LAST_WIN_BONUS_BALL.getMessage());
            int inputValue = Integer.parseInt(scanner.nextLine());
            return new LottoNumber(inputValue);
        } catch (Exception e) {
            System.out.printf(e.getMessage());
            return getBonusLottoNumber();
        }
    }

}