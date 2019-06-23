package lotto.domain.dto;

import lotto.domain.model.Number;

public class LottoDTO {
    private int round;
    private Number firstNum;
    private Number secondNum;
    private Number thirdNum;
    private Number forthNum;
    private Number fifthNum;
    private Number sixthNum;

    public int getRound() {
        return round;
    }

    public void setRound(final int round) {
        this.round = round;
    }

    public Number getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(final Number firstNum) {
        this.firstNum = firstNum;
    }

    public Number getSecondNum() {
        return secondNum;
    }

    public void setSecondNum(final Number secondNum) {
        this.secondNum = secondNum;
    }

    public Number getThirdNum() {
        return thirdNum;
    }

    public void setThirdNum(final Number thirdNum) {
        this.thirdNum = thirdNum;
    }

    public Number getForthNum() {
        return forthNum;
    }

    public void setForthNum(final Number forthNum) {
        this.forthNum = forthNum;
    }

    public Number getFifthNum() {
        return fifthNum;
    }

    public void setFifthNum(final Number fifthNum) {
        this.fifthNum = fifthNum;
    }

    public Number getSixthNum() {
        return sixthNum;
    }

    public void setSixthNum(final Number sixthNum) {
        this.sixthNum = sixthNum;
    }
}
