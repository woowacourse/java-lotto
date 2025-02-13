package view;

public enum DisplayConstants {
    PURCHASE_PRICE_PROMPT("구입금액을 입력해 주세요."),
    WINNING_NUMBER_PROMPT("지난 주 당첨 번호를 입력해 주세요."),
    BONUS_NUMBER_PROMPT("보너스 볼을 입력해 주세요.");

    private final String format;

    DisplayConstants(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public String format(Object... args) {
        return String.format(format, args);
    }
}