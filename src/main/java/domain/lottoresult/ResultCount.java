package domain.lottoresult;

import java.util.Objects;

public class ResultCount {
    int count;

    public ResultCount() {
        count = 0;
    }

    public void increase() {
        count++;
    }

    /**
     * multiply는 ResultCount 인스턴스 값에 아떤 정수를 곱할 때 사용되는 메서드이다.
     * 실제 프로젝트에서는, 수익금을 계산할 때 사용된다.
     *
     * @param operand 곱셈 연산을 수행할 정수이다.
     * @return long으로 곱셈을 수행한 결과을 반환한다.
     */
    public long multiply(long operand) {
        return (long) count * operand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (Objects.isNull(o) || getClass() != o.getClass()) {
            return false;
        }
        ResultCount that = (ResultCount) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    @Override
    public String toString() {
        return String.valueOf(count);
    }
}
