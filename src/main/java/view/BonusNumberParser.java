package view;

public class BonusNumberParser extends Parser<Integer> {
    private static final String REGEX_BONUS_NUMBER_FORMAT = "^\\s*([1-3][0-9]|[1-9]|4[0-5])\\s*$";
    static final String INVALID_BONUS_NUMBER_FORMAT_MESSAGE = "보너스 번호는 반드시 두자리 양수여야 합니다.";

    public BonusNumberParser() {
        super(REGEX_BONUS_NUMBER_FORMAT, INVALID_BONUS_NUMBER_FORMAT_MESSAGE);
    }

    @Override
    protected Integer convert(String text) {
        return Integer.parseInt(text.trim());
    }
}
