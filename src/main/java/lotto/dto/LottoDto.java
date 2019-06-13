package lotto.dto;

public class LottoDto {
    private int round;
    private String lottoNo;
    private String lottoType;

    public LottoDto(int round, String lottoNo) {
        this.round = round;
        this.lottoNo = lottoNo;
    }

    public LottoDto(int round, String lottoNo, String lottoType) {
        this.round = round;
        this.lottoNo = lottoNo;
        this.lottoType = lottoType;
    }
    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getLottoNo() {
        return lottoNo;
    }

    public void setLottoNo(String lottoNo) {
        this.lottoNo = lottoNo;
    }

    public String getLottoType() {
        return lottoType;
    }

    public void setLottoType(String lottoType) {
        this.lottoType = lottoType;
    }
}