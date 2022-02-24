package lotto.view;

import lotto.model.Money;
import lotto.model.Number;

public abstract class Parser<T> {

    private final StringFormatValidator validator;

    protected Parser(StringFormatValidator validator) {
        this.validator = validator;
    }

    public T parse(String text) {
        validator.validate(text);
        return convert(text);
    }

    protected abstract T convert(String text);

    public static Parser<Money> moneyParser() {
        return new Parser<>(StringFormatValidator.moneyValidator()) {
            @Override
            protected Money convert(String text) {
                return new Money(Integer.parseInt(text));
            }
        };
    }

    public static Parser<Number> numberParser() {
        return new Parser<>(StringFormatValidator.numberValidator()) {
            @Override
            protected Number convert(String text) {
                return new Number(Integer.parseInt(text));
            }
        };
    }
}
