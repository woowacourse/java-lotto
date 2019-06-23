package lotto.dto;

import java.util.List;

public class LottoGameDto {
    private int money;
    private int lottoCount;
    private List<List<Integer>> lottoList;
    private List<Integer> winningLotto;
    private int bonusNo;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getLottoCount() {
        return lottoCount;
    }

    public void setLottoCount(int lottoCount) {
        this.lottoCount = lottoCount;
    }

    public List<List<Integer>> getLottoList() {
        return lottoList;
    }

    public void setLottoList(List<List<Integer>> lottoList) {
        this.lottoList = lottoList;
    }

    public List<Integer> getWinningLotto() {
        return winningLotto;
    }

    public void setWinningLotto(List<Integer> winningLotto) {
        this.winningLotto = winningLotto;
    }

    public int getBonusNo() {
        return bonusNo;
    }

    public void setBonusNo(int bonusNo) {
        this.bonusNo = bonusNo;
    }

    @Override
    public String toString() {
        return "LottoGameDto{" +
                "money=" + money +
                ", lottoCount=" + lottoCount +
                ", lottoList=" + lottoList +
                ", winningLotto=" + winningLotto +
                ", bonusNo=" + bonusNo +
                '}';
    }
}
