package domain;

public class CountOfRank {
    private static final int INITIAL_COUNT = 0;

    private int count;

    CountOfRank() {
        count = INITIAL_COUNT;
    }

    int getCount() {
        return count;
    }

    void countUp() {
        count++;
    }
}
