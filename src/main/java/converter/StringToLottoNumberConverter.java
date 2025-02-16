package converter;

import domain.LottoNumber;

public class StringToLottoNumberConverter implements Converter<String, LottoNumber> {
    @Override
    public LottoNumber convert(String source) {
        validateNotStringNumber(source);
        return new LottoNumber(Integer.parseInt(source));
    }

    private void validateNotStringNumber(String value) {
        try {
            Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("허용되지 않는 입력입니다.", e);
        }
    }
}
