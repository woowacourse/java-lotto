package converter;

import domain.Number;

public class StringToNumberConverter implements Converter<String, Number> {
    @Override
    public Number convert(String source) {
        validateNotStringNumber(source);
        return new Number(Integer.parseInt(source));
    }

    public void validateNotStringNumber(String value) {
        try {
            Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("허용되지 않는 입력입니다.", e);
        }
    }
}
