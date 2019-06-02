package lotto.domain.machine;

public class Money {
    private final int insertedMoney;
    private Money(int insertedMoney) {
        this.insertedMoney = insertedMoney;
    }
    public static Money of(int insertedMoney){
        return new Money(insertedMoney);
    }
}
