package com.phdljr.todocard.fixturemonkey;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.phdljr.todocard.entity.User;
import com.phdljr.todocard.fixturemonkey.entity.Animal;
import com.phdljr.todocard.fixturemonkey.entity.Sample;
import com.phdljr.todocard.fixturemonkey.entity.TestEntity;
import java.util.List;
import net.jqwik.api.Arbitraries;
import org.junit.jupiter.api.Test;

public class FixtureMonkeyTest {

    @Test
    void test() {
        // given
        FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
            .build();

        // when
        Animal actual = fixtureMonkey.giveMeOne(Animal.class);

        // then
        System.out.println(actual);
    }

    @Test
    void test2() {
        FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
            .build();

        // 임의 Integer 생성
        Integer number = fixtureMonkey.giveMeOne(Integer.class);
        System.out.println(number);

        // 100 이상 임의 Integer 생성
        Integer number2 = fixtureMonkey.giveMeBuilder(Integer.class)
            .set(Arbitraries.integers().greaterOrEqual(100)).sample();
        System.out.println(number2);

        // 임의 객체 생성
        List<Sample> sample = fixtureMonkey.giveMe(Sample.class, 100);
        sample.forEach(System.out::println);
        System.out.println("===============================");

        // 임의 객체 생성
        char[] chars = {'가', '나', '다', '라', '마'};

        List<Sample> sample2 = fixtureMonkey.giveMeBuilder(Sample.class)
            .set("age", Arbitraries.integers().between(1, 100))
            .set("title", Arbitraries.strings().ofLength(10).withChars(chars))
//            .set("title", Arbitraries.strings().ofLength(10).withCharRange((char) 0xAC00,
//                (char) 0xD7A3))
            .sampleList(10);
        sample2.forEach(System.out::println);

        Sample actual = fixtureMonkey.giveMeBuilder(Sample.class)
            .set("age", 1000)
            .set("title", "Book")
            .sample();
        System.out.println(sample);
    }

    @Test
    public void createTest() {
        FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
            .build();

        List<TestEntity> result = fixtureMonkey.giveMeBuilder(TestEntity.class)
            .sampleList(3);

        result.forEach(System.out::println);
    }

    @Test
    public void createUserTest() {
        FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
            .build();

        List<User> result = fixtureMonkey.giveMeBuilder(User.class)
            .set("id", Arbitraries.longs().between(1, 30))
            .sampleList(10);

        result.forEach(System.out::println);
    }
}
