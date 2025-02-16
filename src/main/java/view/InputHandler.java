package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.stream.Stream;

public class InputHandler {

    private final BufferedReader inputReader;

    public InputHandler(BufferedReader inputReader) {
        this.inputReader = inputReader;
    }

    public String readMoney() throws IOException {
        System.out.println("구입금액을 입력해 주세요.");
        String money = inputReader.readLine();

        validateMoney(money);
        return money;
    }

    public String readWinningNumber() throws IOException {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String winningNumber = inputReader.readLine();

        validateWinningNumber(winningNumber);
        return winningNumber;
    }

    public String readBonusNumber() throws IOException {
        System.out.println("보너스 볼을 입력해 주세요.");
        String bonusNumber = inputReader.readLine();

        validateBonusNumber(bonusNumber);
        return bonusNumber;
    }

    private void validateMoney(String money) {
        BigInteger amount = parseNumberInput(money);

        validateMinAmount(amount);
        validateMaxAmount(amount);
    }

    private BigInteger parseNumberInput(String str) {
        validateNullAndEmpty(str);

        try {
            return new BigInteger(str.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만을 입력해야 합니다.");
        }
    }

    private static void validateNullAndEmpty(String str) {
        if (str == null || str.isBlank()) {
            throw new IllegalArgumentException("입력값이 비어 있을 수 없습니다.");
        }
    }

    private static void validateMinAmount(BigInteger amount) {
        if (amount.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("구입 금액은 음수가 될 수 없습니다.");
        }
    }

    private static void validateMaxAmount(BigInteger amount) {
        BigInteger maxAllowedMoney = BigInteger.valueOf(Integer.MAX_VALUE);

        if (amount.compareTo(maxAllowedMoney) > 0) {
            throw new IllegalArgumentException("구입 금액이 너무 큽니다. 최대 허용 값: " + maxAllowedMoney);
        }
    }

    private void validateWinningNumber(String winningNumber) {
        Stream.of(winningNumber.split(","))
                .map(String::trim)
                .forEach(this::parseNumberInput);
    }

    private void validateBonusNumber(String bonusNumber) {
        parseNumberInput(bonusNumber);
    }
}
