package model.exception;

public enum LottoCountException {
    BLANK_ERROR("[Error]: 금액을 입력해주세요."),
    NUMBER_ERROR("[Error]: 금액은 숫자를 입력해주세요."),
    UNIT_ERROR("[Error]: 금액은 천원 단위여야 합니다."),
    LOWER_THAN_THOUSAND("[Error]: 금액은 1000원 이상이어야 합니다.");

    private final String massage;

    LottoCountException(String massage) {
        this.massage = massage;
    }

    public String getMassage() {
        return massage;
    }
}
