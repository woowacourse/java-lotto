package view.parser;

public class BonusNumberParser extends Parser<Integer> {
    public static final String INVALID_BONUS_NUMBER_FORMAT_MESSAGE = "보너스 번호의 형식이 잘못 되었습니다. 예) 35";

    @Override
    protected Integer convert(String text) {
        return Integer.parseInt(text.trim());
    }

    @Override
    protected String regex() {
        return numberWithSpacesRegex();
    }

    @Override
    protected String errorMessage() {
        return INVALID_BONUS_NUMBER_FORMAT_MESSAGE;
    }
}
