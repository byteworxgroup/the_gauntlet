package be.byteworx;

import be.byteworx.round0.RoundZero;
import be.byteworx.round1.RoundOne;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class Judge {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("Round0: Can you say hello to the world?")
    void round0Test() {
        RoundZero roundZero = new RoundZero();
        roundZero.sayHelloToTheWorld();

        assertEquals("SGVsbG8gV29ybGQh", Base64.getEncoder().encodeToString(outputStreamCaptor.toString().trim().getBytes()));
    }

    @Test
    @DisplayName("Round1: The first rule of bolognaise club is?")
    void round1Test() {
        String failMessage = "Oh ow your next door neighbour vincenzo is happy with your bolognaise";
        RoundOne roundOne = new RoundOne();
        ReflectionUtils.findMethod(RoundOne.class, "theSecretSauce").ifPresent(method -> {
            try {
                method.invoke(roundOne);
            } catch (IllegalAccessException e) {
                return;
            } catch (InvocationTargetException e) {
                fail(failMessage);
            }
            fail(failMessage);
        });
    }
}