package view;

public class BonusNumberParser extends Parser<Integer> {

    static final String INVALID_BONUS_NUMBER_FORMAT_MESSAGE = "보너스 번호는 반드시 두자리 양수여야 합니다.";

    public BonusNumberParser() {
        super(lottoNumberWithSpacesRegex(), INVALID_BONUS_NUMBER_FORMAT_MESSAGE);
    }

    @Override
    protected Integer convert(String text) {
        return Integer.parseInt(text.trim());
    }
}
