package com.woowacourse.lotto.persistence.dto;

import java.time.LocalDateTime;

public class WinningLottoDto {
    private long id;
    private int winningNumber0;
    private int winningNumber1;
    private int winningNumber2;
    private int winningNumber3;
    private int winningNumber4;
    private int winningNumber5;
    private int winningBonusNumber;
    private LocalDateTime regDate;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getWinningNumber0() {
        return winningNumber0;
    }

    public void setWinningNumber0(int winningNumber0) {
        this.winningNumber0 = winningNumber0;
    }

    public int getWinningNumber1() {
        return winningNumber1;
    }

    public void setWinningNumber1(int winningNumber1) {
        this.winningNumber1 = winningNumber1;
    }

    public int getWinningNumber2() {
        return winningNumber2;
    }

    public void setWinningNumber2(int winningNumber2) {
        this.winningNumber2 = winningNumber2;
    }

    public int getWinningNumber3() {
        return winningNumber3;
    }

    public void setWinningNumber3(int winningNumber3) {
        this.winningNumber3 = winningNumber3;
    }

    public int getWinningNumber4() {
        return winningNumber4;
    }

    public void setWinningNumber4(int winningNumber4) {
        this.winningNumber4 = winningNumber4;
    }

    public int getWinningNumber5() {
        return winningNumber5;
    }

    public void setWinningNumber5(int winningNumber5) {
        this.winningNumber5 = winningNumber5;
    }

    public int getWinningBonusNumber() {
        return winningBonusNumber;
    }

    public void setWinningBonusNumber(int winningBonusNumber) {
        this.winningBonusNumber = winningBonusNumber;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }
}
