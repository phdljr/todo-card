package com.phdljr.todocard.fixturemonkey;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;
import com.phdljr.todocard.entity.Card;
import com.phdljr.todocard.entity.User;
import com.phdljr.todocard.entity.UserRole;
import com.phdljr.todocard.repository.CardRepository;
import com.phdljr.todocard.repository.UserRepository;
import java.util.List;
import java.util.regex.Pattern;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class EntityFixtureMonkeyTest {

    FixtureMonkey fixtureMonkey;

    @Autowired
    UserRepository userRepository;
    @Autowired
    CardRepository cardRepository;

    @BeforeEach
    public void setup(){
        fixtureMonkey = FixtureMonkey.builder()
            .plugin(new JakartaValidationPlugin())
            .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
            .build();
    }

    @Test
    public void relationalTest(){
        List<User> users = fixtureMonkey.giveMeBuilder(User.class)
            .setNull("id")
            .setNotNull("*")
            .set("password", Arbitraries.strings().withCharRange('a', 'z').ofLength(10))
            .sampleList(10);
        userRepository.saveAll(users);

        List<Card> cards = fixtureMonkey.giveMeBuilder(Card.class)
            .setNotNull("*")
            .setNull("comments")
            .set("user", Arbitraries.of(users))
            .sampleList(30);
        cardRepository.saveAll(cards);

        cards.forEach(System.out::println);
    }

}
