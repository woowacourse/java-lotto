package lotto.view;

import static lotto.view.messages.InputMessages.BONUS_BALL_INPUT_REQUEST;
import static lotto.view.messages.InputMessages.PURCHASE_MONEY_INPUT_REQUEST;
import static lotto.view.messages.InputMessages.WINNING_LOTTO_LINE_INPUT_REQUEST;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lotto.domain.lotto.LottoLine;
import lotto.domain.lotto.LottoNumber;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static int getMoney() {
        try {
            System.out.println(PURCHASE_MONEY_INPUT_REQUEST.getMessage());
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getMoney();
        }
    }

    public static LottoLine getLottoLine() {
        try {
            System.out.println(WINNING_LOTTO_LINE_INPUT_REQUEST.getMessage());
            String inputValue = scanner.nextLine();
            List<LottoNumber> lottoNumbers = parseLottoNumber(inputValue);
            return new LottoLine(lottoNumbers);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getLottoLine();
        }
    }

    private static List<LottoNumber> parseLottoNumber(String inputValue) {
        String[] parsedInputValue = inputValue.replace(" ", "").split(",");
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (String s : parsedInputValue) {
            lottoNumbers.add(new LottoNumber(Integer.parseInt(s)));
        }
        return lottoNumbers;
    }

    public static LottoNumber getBonusLottoNumber() {
        try {
            System.out.println(BONUS_BALL_INPUT_REQUEST.getMessage());
            int inputValue = Integer.parseInt(scanner.nextLine());
            return new LottoNumber(inputValue);
        } catch (Exception e) {
            System.out.printf(e.getMessage());
            return getBonusLottoNumber();
        }
    }

}