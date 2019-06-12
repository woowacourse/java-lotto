package lotto.dto;

import lotto.domain.LottoTicket;

public class LottoDTO {
    private int lottoId;
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;
    private int num6;

    public LottoDTO(LottoTicket lottoTicket) {
        String lottoNumbers = lottoTicket.toString().replaceAll("[|]", "");
        String[] numbers = lottoNumbers.split(",");

        this.num1 = Integer.parseInt(numbers[0]);
        this.num2 = Integer.parseInt(numbers[1]);
        this.num3 = Integer.parseInt(numbers[2]);
        this.num4 = Integer.parseInt(numbers[3]);
        this.num5 = Integer.parseInt(numbers[4]);
        this.num6 = Integer.parseInt(numbers[5]);
    }

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public int getNum3() {
        return num3;
    }

    public int getNum4() {
        return num4;
    }

    public int getNum5() {
        return num5;
    }

    public int getNum6() {
        return num6;
    }
}
