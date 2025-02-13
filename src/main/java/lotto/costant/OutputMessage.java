package lotto.costant;

public enum OutputMessage {
    PURCHASE_COMPLETE("%d개를 구매했습니다.");

    private final String content;

    OutputMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
