package com.woowacourse.lotto.domain.dto;

import java.time.LocalDateTime;

public class LottoDto {
    private long id;
    private int number0;
    private int number1;
    private int number2;
    private int number3;
    private int number4;
    private int number5;
    private int number6;
    private int price;
    private LocalDateTime regDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber0() {
        return number0;
    }

    public void setNumber0(int number0) {
        this.number0 = number0;
    }

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public int getNumber3() {
        return number3;
    }

    public void setNumber3(int number3) {
        this.number3 = number3;
    }

    public int getNumber4() {
        return number4;
    }

    public void setNumber4(int number4) {
        this.number4 = number4;
    }

    public int getNumber5() {
        return number5;
    }

    public void setNumber5(int number5) {
        this.number5 = number5;
    }

    public int getNumber6() {
        return number6;
    }

    public void setNumber6(int number6) {
        this.number6 = number6;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }
}
