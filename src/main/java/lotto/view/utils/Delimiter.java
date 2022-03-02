package lotto.view.utils;

import java.util.List;

public enum Delimiter {

    COMMA(","),
    SPACE(" ");

    private final String unit;

    Delimiter(final String unit) {
        this.unit = unit;
    }

    public List<String> splitWith(final String targetString) {
        final int limitForSplitAllElement = -1;
        return List.of(targetString.split(this.unit, limitForSplitAllElement));
    }

    public String joinWith(final List<String> strings) {
        return String.join(this.unit + SPACE.unit, strings);
    }

}
