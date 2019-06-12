package lotto;

public enum ServiceMessage {
    MONEY("money"),
    MANUAL_AMOUNT("manualAmount"),
    MANUAL_NUMBER("manualNumber"),
    LOTTO_TICKETS("lottoTickets"),
    WINNING_LOTTO("winningLotto"),
    BONUS_BALL("bonusBall"),
    LOTTO_RESULTS("lottoResults"),
    REWARD_MONEY("rewardMoney"),
    ROUND("round");

    private final String type;

    ServiceMessage(String type) {
        this.type = type;
    }

    public String type() {
        return this.type;
    }
}
