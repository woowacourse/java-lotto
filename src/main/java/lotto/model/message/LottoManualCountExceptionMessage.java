package lotto.model.message;

public enum LottoManualCountExceptionMessage {
    BLANK_ERROR("[Error]:  로또 수를 입력해주세요."),
    NUMBER_ERROR("[Error]: 로또 수는 숫자를 입력해주세요.");

    private final String massage;

    LottoManualCountExceptionMessage(String massage) {
        this.massage = massage;
    }

    public String getMassage() {
        return massage;
    }
}
