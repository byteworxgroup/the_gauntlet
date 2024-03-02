package be.byteworx;

import be.byteworx.round0.RoundZero;
import be.byteworx.round1.RoundOne;
import be.byteworx.round2.RoundTwo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class Judge {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    @DisplayName("Round0: Can you say hello to the world?")
    void round0Test() {
        System.setOut(new PrintStream(outputStreamCaptor));
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

    @Test
    @DisplayName("Round2: Poor little Johnny! Can you help him out?")
    void round2Test() {
        String failMessage = "You have to talk some sense into Johnny";
        RoundTwo roundTwo = new RoundTwo();
        assertEquals("I was playing in the garden when suddenly I tripped and landed on my head. Boy does my head feel funny.", roundTwo.iHaveDrainBamage().trim(), failMessage);
    }
}