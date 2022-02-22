package validator;

public class LottoNumberValidators {

    public static int validateAndParseNumber(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            throw new IllegalArgumentException("숫자를 입력해야 합니다.");
        }
    }
}
