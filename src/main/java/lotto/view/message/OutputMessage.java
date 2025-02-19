package lotto.view.message;

public enum OutputMessage {
    PURCHASE_COMPLETE("%d개를 구매했습니다."),
    REGULAR_TIER("%d개 일치 (%d원) - %d개"),
    BONUS_TIER("%d개 일치, 보너스 볼 일치 (%d원) - %d개"),
    PROFIT("총 수익률은 %s입니다."),
    LOSS_DESCRIPTION("(기준이 1이기 때문에 결과적으로 손해라는 의미임)"),
    RESULTS_HEADER("당첨 통계\n---------");

    private final String content;

    OutputMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
