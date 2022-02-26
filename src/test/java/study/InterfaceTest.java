package study;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

public class InterfaceTest{

    @Test
    void canAccessConstantsInInterface_asGetterAndAsPublicClassField() {
        ClassWithConstantsT objectT = new ClassWithConstantsT();

        assertThat(objectT.getConstantInInterface()).isEqualTo(1);

        assertThat(ClassWithConstantsT.MIN_NUMBER).isEqualTo(1);
        assertThat(ClassWithConstantsT.MAX_NUMBER).isEqualTo(10);
        assertThat(ClassWithConstantsT.numList.size()).isEqualTo(10);
    }

    @Test
    void canUseGetter() {
        ClassWithConstantsT objectT = new ClassWithConstantsT();
        ClassWithConstantsF objectF = new ClassWithConstantsF();

        assertThat(objectT.getBooleanVal()).isEqualTo(true);
        assertThat(objectF.getBooleanVal()).isEqualTo(false);
    }
}

interface iClassWithConstants {
    int MIN_NUMBER = 1;
    int MAX_NUMBER = 10;
    List<Integer> numList = IntStream.range(MIN_NUMBER, MAX_NUMBER + 1)
            .boxed()
            .collect(Collectors.toList());

    boolean getBooleanVal();
}

class ClassWithConstantsT implements iClassWithConstants {

   public int getConstantInInterface() {
       return MIN_NUMBER;
   }

    @Override
    public boolean getBooleanVal() {
        return true;
    }
}

class ClassWithConstantsF implements iClassWithConstants {

    public boolean getBooleanVal() {
        return false;
    }
}


