package converter;

import domain.Money;

public class StringToMoneyConverter implements Converter<String, Money> {
    @Override
    public Money convert(String source) {
        validateNotStringNumber(source);
        return new Money(source);
    }

    private void validateNotStringNumber(String value) {
        try {
            Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("허용되지 않는 입력입니다.", e);
        }
    }
}
