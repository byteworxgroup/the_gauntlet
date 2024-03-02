package be.byteworx.round1;

import java.util.List;

public class RoundOne {

    //TODO: make a change to this method to hide the secret sauce from other pesky classes
    public String theSecretSauce() {
        List<String> ingredients = List.of("sugar", "spice", "everything nice");
        return String.join(", ", ingredients);
    }
}
