package be.byteworx.challenge7;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ChallengeSeven {
    public Set<String> imSpecial(List<String> duplicates) {
        //TODO: can you make sure we only return unique values?
        return new HashSet<>(duplicates);
    }
}
