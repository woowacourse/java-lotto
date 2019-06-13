package lotto.domain.lotto;

public enum LottoType {
    MANUAL("MANUAL"),
    AUTOMATIC("AUTO"),
    WINNING("WINNING");

    private String type;

    LottoType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
