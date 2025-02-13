package lotto.costant;

public enum Prompt {
    PURCHASE("구입금액을 입력해 주세요."),
    WINNING_NUMBERS("지난 주 당첨 번호를 입력해 주세요."),
    BONUS_NUMBER("보너스 볼을 입력해 주세요.");

    private final String content;

    Prompt(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
