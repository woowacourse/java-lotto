package domain;

public class ManualNumberGenerator implements Generator {
    int number = 1;

    @Override
    public int generate(int number) {
        return this.number++;
    }
}
