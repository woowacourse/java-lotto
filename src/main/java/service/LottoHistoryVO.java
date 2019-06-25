package service;

public class LottoHistoryVO {
    private final int round;
    private final String encodedLottos;

    public LottoHistoryVO(int round, String encodedLottos) {
        this.round = round;
        this.encodedLottos = encodedLottos;
    }

    public int round() {
        return this.round;
    }

    public String encodedLottos() {
        return this.encodedLottos;
    }
}