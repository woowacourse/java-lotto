package constans;

public enum OutputMessage {

    // input
    INPUT_PURCHASE_AMOUNT("구입금액을 입력해 주세요."),
    INPUT_WINNING_NUMBERS("지난 주 당첨 번호를 입력해 주세요."),
    INPUT_BONUS_NUMBER("보너스 볼을 입력해 주세요."),

    // output
    LOTTO_PURCHASE_COUNT("%d개를 구매했습니다."),
    LOTTO_NUMBERS("[%s]"),
    LOTTO_YIELD("총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)");


    private final String message;

    OutputMessage(final String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return message;
    }
}
