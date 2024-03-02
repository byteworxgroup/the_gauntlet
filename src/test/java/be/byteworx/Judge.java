package be.byteworx;

import be.byteworx.challenge0.ChallengeZero;
import be.byteworx.challenge1.ChallengeOne;
import be.byteworx.challenge2.ChallengeTwo;
import be.byteworx.challenge3.ChallengeThree;
import be.byteworx.challenge4.ChallengeFour;
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
    @DisplayName("Challenge0: Can you say hello to the world?")
    void challenge0Test() {
        System.setOut(new PrintStream(outputStreamCaptor));
        ChallengeZero challengeZero = new ChallengeZero();
        challengeZero.sayHelloToTheWorld();

        assertEquals("SGVsbG8gV29ybGQh", Base64.getEncoder().encodeToString(outputStreamCaptor.toString().trim().getBytes()));
    }

    @Test
    @DisplayName("Challenge1: The first rule of bolognaise club is?")
    void challenge1Test() {
        String failMessage = "Oh ow your next door neighbour vincenzo is happy with your bolognaise";
        ChallengeOne challengeOne = new ChallengeOne();
        ReflectionUtils.findMethod(ChallengeOne.class, "theSecretSauce").ifPresent(method -> {
            try {
                method.invoke(challengeOne);
            } catch (IllegalAccessException e) {
                return;
            } catch (InvocationTargetException e) {
                fail(failMessage);
            }
            fail(failMessage);
        });
    }

    @Test
    @DisplayName("Challenge2: Poor little Johnny! Can you help him out?")
    void challenge2Test() {
        String failMessage = "You have to talk some sense into Johnny";
        ChallengeTwo challengeTwo = new ChallengeTwo();
        assertEquals("I was playing when suddenly I tripped and hit my head. Boy does my head feel funny.", challengeTwo.iHaveDrainBamage().trim(), failMessage);
    }

    @Test
    @DisplayName("Challenge3: To drink or not to drink? That is the question!")
    void challenge3Test() {
        String daySchedule = new ChallengeThree().drinkingSchedule(15);
        assertEquals("FIZZY BUZZED", daySchedule);
        daySchedule = new ChallengeThree().drinkingSchedule(3);
        assertEquals("BUZZED", daySchedule);
        daySchedule = new ChallengeThree().drinkingSchedule(5);
        assertEquals("FIZZY", daySchedule);
        daySchedule = new ChallengeThree().drinkingSchedule(1);
        assertEquals("STAYING IN", daySchedule);
    }

    @Test
    @DisplayName("Challenge4: Sing me your sweet song fibonacci!")
    void challenge4Test() {
        String failMessage = "You have to sing me the fibonacci song";
        ChallengeFour challengeFour = new ChallengeFour();
        assertEquals("[0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55]", challengeFour.fibonacci(60).toString(), failMessage);
        assertEquals("[]", challengeFour.fibonacci(0).toString(), failMessage);
        assertEquals("[0]", challengeFour.fibonacci(1).toString(), failMessage);
        assertEquals("[0, 1]", challengeFour.fibonacci(2).toString(), failMessage);
    }
}