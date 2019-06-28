package lotto;

import java.util.List;

public class PreviousWinLottoResultDTO {
    private int winLottoID;
    private int winLottoBonus;
    private int first;
    private int second;
    private int third;
    private int fourth;
    private int fifth;
    private int sixth;
    private List<PreviousPurchaseDTO> previousPurchases;

    public int getWinLottoID() {
        return winLottoID;
    }

    public PreviousWinLottoResultDTO setWinLottoID(int winLottoID) {
        this.winLottoID = winLottoID;
        return this;
    }

    public int getWinLottoBonus() {
        return winLottoBonus;
    }

    public PreviousWinLottoResultDTO setWinLottoBonus(int winLottoBonus) {
        this.winLottoBonus = winLottoBonus;
        return this;
    }

    public int getFirst() {
        return first;
    }

    public PreviousWinLottoResultDTO setFirst(int first) {
        this.first = first;
        return this;
    }

    public int getSecond() {
        return second;
    }

    public PreviousWinLottoResultDTO setSecond(int second) {
        this.second = second;
        return this;
    }

    public int getThird() {
        return third;
    }

    public PreviousWinLottoResultDTO setThird(int third) {
        this.third = third;
        return this;
    }

    public int getFourth() {
        return fourth;
    }

    public PreviousWinLottoResultDTO setFourth(int fourth) {
        this.fourth = fourth;
        return this;
    }

    public int getFifth() {
        return fifth;
    }

    public PreviousWinLottoResultDTO setFifth(int fifth) {
        this.fifth = fifth;
        return this;
    }

    public int getSixth() {
        return sixth;
    }

    public PreviousWinLottoResultDTO setSixth(int sixth) {
        this.sixth = sixth;
        return this;
    }

    public List<PreviousPurchaseDTO> getPreviousPurchases() {
        return previousPurchases;
    }

    public PreviousWinLottoResultDTO setPreviousPurchases(List<PreviousPurchaseDTO> previousPurchases) {
        this.previousPurchases = previousPurchases;
        return this;
    }
}
