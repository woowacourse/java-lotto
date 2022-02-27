package lotterymachine.domain;

import java.util.Objects;

public class LotteryNumber implements Comparable<LotteryNumber>{
    private final int number;

    public LotteryNumber(int number) {
        this.number = number;
    }


    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(LotteryNumber o) {
        return this.number - o.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LotteryNumber that = (LotteryNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
