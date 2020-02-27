package domain.numberscontainer;

public class BonusNumberDto {
    private static final String NUMBER_REGEX = "^[0-9]+$";

    private final LottoNumber bonusNumber;

    public BonusNumberDto(String bonusNumberInput) {
        this.bonusNumber = LottoNumber.getLottoNumber(parseInt(bonusNumberInput));
    }

    private static int parseInt(String input) {
        validateNumber(input);
        return Integer.parseInt(input);
    }

    private static void validateNumber(String input) {
        if (!input.matches(NUMBER_REGEX)) {
            throw new NumberFormatException("숫자를 입력해주세요.");
        }
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}