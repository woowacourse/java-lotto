package lotto.model.message;

public enum LottoCountExceptionMessage {
    UNIT_ERROR("[Error]: 금액은 천원 단위여야 합니다."),
    LOWER_THAN_THOUSAND("[Error]: 금액은 1000원 이상이어야 합니다.");

    private final String massage;

    LottoCountExceptionMessage(String massage) {
        this.massage = massage;
    }

    public String getMassage() {
        return massage;
    }
}
