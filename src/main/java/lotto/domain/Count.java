package lotto.domain;

public class Count {

    private final int count;

    public Count(final int count){
        this.count = count;
    }

    public boolean exists(){
        return count > 0;
    }

    public int counts(){
        return count;
    }

    public Count remains(int value) {
        return new Count(value - counts());
    }
}
