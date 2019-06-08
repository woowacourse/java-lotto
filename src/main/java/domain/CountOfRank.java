package domain;

class CountOfRank {
    private static final int INITIAL_COUNT = 0;

    private int count = INITIAL_COUNT;

    int getCount() {
        return count;
    }

    void countUp() {
        count++;
    }
}
