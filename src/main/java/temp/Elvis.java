package temp;

import java.io.Serializable;
import java.util.function.Supplier;

public class Elvis implements Serializable {


    public static Elvis getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private LazyHolder() {
            System.out.println("LazyHolder 객체 생성");
        }

        private static final Elvis INSTANCE = new Elvis();
    }

    public static void main(String[] args) {
        System.out.println("11111111111");
        Elvis.getInstance();
        System.out.println("222222222222");

    }

    Elvis create() {
        return new Elvis();
    }

    Elvis create(Supplier<? extends Elvis> tileFactory) {
        return tileFactory.get().create();
    }


}
