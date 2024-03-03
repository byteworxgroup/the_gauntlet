package be.byteworx.challenge2;

import java.util.List;

public class ChallengeTwo {

    //TODO: make a change to this method to hide the secret sauce from other pesky classes
    public String theSecretSauce() {
        List<String> ingredients = List.of("sugar", "spice", "everything nice");
        return String.join(", ", ingredients);
    }
}
