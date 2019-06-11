package com.woowacourse.lotto.persistence.dto;

import java.time.LocalDateTime;
import java.util.List;

public class AggregationDto {
    private long id;
    private int lottoRound;
    private int cntFirst;
    private int cntSecond;
    private int cntThird;
    private int cntFourth;
    private int cntFifth;
    private int cntNone;
    private long prizeMoneySum;
    private LocalDateTime regDate;
    private List<LottoDto> lottos;
    private WinningLottoDto winningLotto;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLottoRound() {
        return lottoRound;
    }

    public void setLottoRound(int lottoRound) {
        this.lottoRound = lottoRound;
    }

    public int getCntFirst() {
        return cntFirst;
    }

    public void setCntFirst(int cntFirst) {
        this.cntFirst = cntFirst;
    }

    public int getCntSecond() {
        return cntSecond;
    }

    public void setCntSecond(int cntSecond) {
        this.cntSecond = cntSecond;
    }

    public int getCntThird() {
        return cntThird;
    }

    public void setCntThird(int cntThird) {
        this.cntThird = cntThird;
    }

    public int getCntFourth() {
        return cntFourth;
    }

    public void setCntFourth(int cntFourth) {
        this.cntFourth = cntFourth;
    }

    public int getCntFifth() {
        return cntFifth;
    }

    public void setCntFifth(int cntFifth) {
        this.cntFifth = cntFifth;
    }

    public int getCntNone() {
        return cntNone;
    }

    public void setCntNone(int cntNone) {
        this.cntNone = cntNone;
    }

    public long getPrizeMoneySum() {
        return prizeMoneySum;
    }

    public void setPrizeMoneySum(long prizeMoneySum) {
        this.prizeMoneySum = prizeMoneySum;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public List<LottoDto> getLottos() {
        return lottos;
    }

    public void setLottos(List<LottoDto> lottos) {
        this.lottos = lottos;
    }

    public WinningLottoDto getWinningLotto() {
        return winningLotto;
    }

    public void setWinningLotto(WinningLottoDto winningLotto) {
        this.winningLotto = winningLotto;
    }
}
