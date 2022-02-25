package sample;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import org.junit.jupiter.api.Test;

public class TestSomething {

    @Test
    void test4() {
    	// elementData = Arrays.copyOf(elementData, size, Object[].class);
        int[] ints = {1, 2, 3};
        ints[0] = 100;
        int[] copyInts = Arrays.copyOf(ints, 3);
        System.out.println("Arrays.toString(ints) = " + Arrays.toString(ints));
        System.out.println("Arrays.toString(copyInts) = " + Arrays.toString(copyInts));
    }

    @Test
    void test3() {
        if (true) {
            throw new AssertionError(1_222);
        }
    }

    @Test
    void test2() {
        Objects.requireNonNull("aa", "nono");
    }

    @Test
    void test1() {
        Date start = new Date(1000);
        Date end = new Date(2000);
        Period period = new Period(start, end);
//        Period period = new Period(end, start);
        System.out.println("period = " + period);
        end.setTime(3000);
        System.out.println("period = " + period);
        LocalDateTime now = LocalDateTime.now();
    }

    private static final class Period {

        private final Date start;
        private final Date end;

        public Period(Date start, Date end) {
            if (start.compareTo(end) > 0) {
                throw new IllegalArgumentException("start가 end보다 늦으면 안돼");
            }
            this.start = new Date(start.getTime());
            this.end = new Date(end.getTime());
        }

        @Override
        public String toString() {
            return "Period{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
