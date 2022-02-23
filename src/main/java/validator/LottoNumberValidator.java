package validator;

public class LottoNumberValidator {

    public static void validate(String number) {
        int parsedNumber;
        try {
            parsedNumber = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("지난 주 당첨 번호가 정수가 아닙니다.");
        }

        if (isNotCorrectNumber(parsedNumber)) {
            throw new IllegalArgumentException("지난 주 당첨 번호는 1~45 사이로 입력해주세요.");
        }
    }

    private static boolean isNotCorrectNumber(int number) {
        return !(number <= 45 && number > 0);
    }
}
