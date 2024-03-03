package be.byteworx;

import be.byteworx.challenge0.ChallengeZero;
import be.byteworx.challenge1.ChallengeOne;
import be.byteworx.challenge2.ChallengeTwo;
import be.byteworx.challenge3.ChallengeThree;
import be.byteworx.challenge4.ChallengeFour;
import be.byteworx.challenge5.ChallengeFive;
import be.byteworx.challenge6.ChallengeSix;
import be.byteworx.challenge7.ChallengeSeven;
import be.byteworx.challenge8.ChallengeEight;
import be.byteworx.challenge9.ChallengeNine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;
import org.junit.platform.commons.util.StringUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import static be.byteworx.challenge9.ChallengeNine.readFromInputStream;
import static org.junit.jupiter.api.Assertions.*;

class ChallengeTests {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    @DisplayName("Challenge0: Can you fix the copier?")
    void challenge0Test() throws IOException, InterruptedException {
        ChallengeZero challengeZero = new ChallengeZero();
        String branch = challengeZero.branchName();
        if(branch == null || branch.trim().equals("")) {
            fail("We need to name our copy something");
        }
        if(branch.length()<5 || !branch.contains("-")) {
            fail("Our branch names have to be longer than 5 and be in the format FIRSTNAME-LASTNAME");
        }
        ProcessBuilder processBuilder = new ProcessBuilder("git", "branch", "-a");
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();

        List<String> results = readOutput(process.getInputStream());

        process.waitFor();

        boolean found = false;
        for (String result : results) {
            if(result.contains(branch)) {
                found = true;
                break;
            }
        }
        assertTrue(found ,"Oh ow we can't seem to find your copy. Did we drop it somewhere?");

    }
    @Test
    @DisplayName("Challenge1: Can you say hello to the world?")
    void challenge1Test() {
        System.setOut(new PrintStream(outputStreamCaptor));
        ChallengeOne challengeOne = new ChallengeOne();
        challengeOne.sayHelloToTheWorld();

        assertEquals("SGVsbG8gV29ybGQh", Base64.getEncoder().encodeToString(outputStreamCaptor.toString().trim().getBytes()));
    }

    @Test
    @DisplayName("Challenge2: The first rule of bolognaise club is?")
    void challenge2Test() {
        String failMessage = "Oh ow your next door neighbour vincenzo is happy with your bolognaise";
        ChallengeTwo challengeTwo = new ChallengeTwo();
        ReflectionUtils.findMethod(ChallengeTwo.class, "theSecretSauce").ifPresent(method -> {
            try {
                method.invoke(challengeTwo);
            } catch (IllegalAccessException e) {
                return;
            } catch (InvocationTargetException e) {
                fail(failMessage);
            }
            fail(failMessage);
        });
    }

    @Test
    @DisplayName("Challenge3: Poor little Johnny! Can you help him out?")
    void challenge3Test() {
        String failMessage = "You have to talk some sense into Johnny";
        ChallengeThree challengeThree = new ChallengeThree();
        assertEquals("I was playing when suddenly I tripped and hit my head. Boy does my head feel funny.", challengeThree.iHaveDrainBamage().trim(), failMessage);
    }

    @Test
    @DisplayName("Challenge4: To drink or not to drink? That is the question!")
    void challenge4Test() {
        String daySchedule = new ChallengeFour().drinkingSchedule(15);
        assertEquals("FIZZY BUZZED", daySchedule);
        daySchedule = new ChallengeFour().drinkingSchedule(3);
        assertEquals("BUZZED", daySchedule);
        daySchedule = new ChallengeFour().drinkingSchedule(5);
        assertEquals("FIZZY", daySchedule);
        daySchedule = new ChallengeFour().drinkingSchedule(1);
        assertEquals("STAYING IN", daySchedule);
    }

    @Test
    @DisplayName("Challenge5: Sing me your sweet song fibonacci!")
    void challenge5Test() {
        String failMessage = "You have to sing me the fibonacci song";
        ChallengeFive challengeFive = new ChallengeFive();
        assertEquals("[0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55]", challengeFive.fibonacci(60).toString(), failMessage);
        assertEquals("[]", challengeFive.fibonacci(0).toString(), failMessage);
        assertEquals("[0]", challengeFive.fibonacci(1).toString(), failMessage);
        assertEquals("[0, 1]", challengeFive.fibonacci(2).toString(), failMessage);
    }

    @Test
    @DisplayName("Challenge6: Can little Johnny trust this number?")
    void challenge6Test() {
        String failMessage = "That number is not trustworthy";
        ChallengeSix challengeSix = new ChallengeSix();
        assertTrue(challengeSix.palindrome(1000001), failMessage);
        assertFalse(challengeSix.palindrome(1000002), failMessage);
    }

    @Test
    @DisplayName("Challenge7: Can you make sure the gifts are unique?")
    void challenge7Test() {
        List<String> gifts = List.of("Pony", "Playstation", "Usb stick", "Pony");
        String failMessage = "Looks like you missed one";
        ChallengeSeven challengeSeven = new ChallengeSeven();
        assertLinesMatch(List.of("Playstation", "Pony", "Usb stick"),new ArrayList<>(challengeSeven.imSpecial(gifts)), failMessage);
    }

    @Test
    @DisplayName("Challenge8: What was the count again?")
    void challenge8Test() {
        String failMessage = "2+1 is 4?";
        ChallengeEight challengeEight = new ChallengeEight();
        assertEquals(6,challengeEight.longestContinuingNumberChain(List.of(1,5,8,3,4,1,2,3,4,5,6,8)), failMessage);
        assertEquals(3,challengeEight.longestContinuingNumberChain(List.of(1,2,3,1,4,2,4,2,4,2)), failMessage);
        assertEquals(3,challengeEight.longestContinuingNumberChain(List.of(1,4,2,4,2,4,2,1,2,3)), failMessage);
    }

    @Test
    @DisplayName("Challenge9: most efficient sort")
    void challenge9Test() throws IOException {
        String failMessage = "Thats not quit right";
        ChallengeNine challengeNine = new ChallengeNine();
        Integer[] expected = readFromInputStream(getClass().getResourceAsStream("/expected.txt"));
        assertEquals(Arrays.toString(expected), Arrays.toString(challengeNine.sortingTime()), failMessage);
    }

    private List<String> readOutput(InputStream inputStream) throws IOException {
        try (BufferedReader output = new BufferedReader(new InputStreamReader(inputStream))) {
            return output.lines()
                    .collect(Collectors.toList());
        }
    }
}