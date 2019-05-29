package lotto.domain;

public enum LottoType {
    MANUAL(1),
    AUTOMATIC(2);

    private int code;

    LottoType(int code) {
        this.code = code;
    }
}
