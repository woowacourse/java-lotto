package lotto.domain.lottoticket.dto;

import lotto.domain.lottoticket.LottoTicket;

public class LottoTicketDTO {
    private int[] numbers = new int[LottoTicket.SIZE_OF_LOTTO];

    public int getFirstNum() {
        return numbers[0];
    }

    public void setFirstNum(int firstNum) {
        this.numbers[0] = firstNum;
    }

    public int getSecondNum() {
        return numbers[1];
    }

    public void setSecondNum(int secondNum) {
        this.numbers[1] = secondNum;
    }

    public int getThirdNum() {
        return numbers[2];
    }

    public void setThirdNum(int thirdNum) {
        this.numbers[2] = thirdNum;
    }

    public int getFourthNum() {
        return numbers[3];
    }

    public void setFourthNum(int fourthNum) {
        this.numbers[3] = fourthNum;
    }

    public int getFifthNum() {
        return numbers[4];
    }

    public void setFifthNum(int fifthNum) {
        this.numbers[4] = fifthNum;
    }

    public int getSixthNum() {
        return numbers[5];
    }

    public void setSixthNum(int sixthNum) {
        this.numbers[5] = sixthNum;
    }
}
