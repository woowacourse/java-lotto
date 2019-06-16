package lotto.dto;

public class WinningResultDTO {
    private int lottoRoundId;
    private long first;
    private long second;
    private long third;
    private long fourth;
    private long fifth;
    private long miss;
    private long roi;

    public WinningResultDTO() {

    }

    public WinningResultDTO(int lottoRoundId, long first, long second, long third, long fourth, long fifth, long miss, long roi) {
        this.lottoRoundId = lottoRoundId;
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
        this.miss = miss;
        this.roi = roi;
    }

    public long getLottoRoundId() {
        return lottoRoundId;
    }

    public void setLottoRoundId(int lottoRoundId) {
        this.lottoRoundId = lottoRoundId;
    }

    public long getFirst() {
        return first;
    }

    public void setFirst(long first) {
        this.first = first;
    }

    public long getSecond() {
        return second;
    }

    public void setSecond(long second) {
        this.second = second;
    }

    public long getThird() {
        return third;
    }

    public void setThird(long third) {
        this.third = third;
    }

    public long getFourth() {
        return fourth;
    }

    public void setFourth(long fourth) {
        this.fourth = fourth;
    }

    public long getFifth() {
        return fifth;
    }

    public void setFifth(long fifth) {
        this.fifth = fifth;
    }

    public long getMiss() {
        return miss;
    }

    public void setMiss(long miss) {
        this.miss = miss;
    }

    public long getRoi() {
        return roi;
    }

    public void setRoi(long roi) {
        this.roi = roi;
    }
}
