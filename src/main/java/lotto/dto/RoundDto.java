package lotto.dto;

public class RoundDto {
    private final int round;
    private final int manualRound;
    private final int autoRound;
    private final int lottoRound;

    public RoundDto(int round, int manualRound, int autoRound, int lottoRound) {
        this.round = round;
        this.manualRound = manualRound;
        this.autoRound = autoRound;
        this.lottoRound = lottoRound;
    }

    public int getRound() {
        return round;
    }

    public int getManualRound() {
        return manualRound;
    }

    public int getAutoRound() {
        return autoRound;
    }

    public int getLottoRound() {
        return lottoRound;
    }
}
