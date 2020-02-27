package domain.numberscontainer;

/*
 * BonusNumberDto 생성 담당 클래스
 */
public class BonusNumberDtoAssembler {
    private static final String NUMBER_REGEX = "^[0-9]+$";

    public static BonusNumberDto assemble(String number) {
        return new BonusNumberDto(parseInt(number));
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
}