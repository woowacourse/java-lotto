package lotto.dto;

import java.util.List;

public class LottoDTO {
    private int round;
    private int number1;
    private int number2;
    private int number3;
    private int number4;
    private int number5;
    private int number6;

    public LottoDTO(int round, List<Integer> numbers) {
        this.round = round;
        this.number1 = numbers.get(0);
        this.number2 = numbers.get(1);
        this.number3 = numbers.get(2);
        this.number4 = numbers.get(3);
        this.number5 = numbers.get(4);
        this.number6 = numbers.get(5);
    }

    public int getRound() {
        return round;
    }

    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }

    public int getNumber3() {
        return number3;
    }

    public int getNumber4() {
        return number4;
    }

    public int getNumber5() {
        return number5;
    }

    public int getNumber6() {
        return number6;
    }
}
