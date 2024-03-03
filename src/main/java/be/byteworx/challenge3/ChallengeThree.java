package be.byteworx.challenge3;
public class ChallengeThree {

    public static String JOHNNY_SAYS = """
            .ynnuf leef daeh ym seod yoB .daeh ym tih dna deppirt I ylneddus nehw gniyalp saw I
            """;
    public String iHaveDrainBamage() {
        //TODO: talk some sense into Johnny
        StringBuilder stringBuilder = new StringBuilder(JOHNNY_SAYS);
        return stringBuilder.reverse().toString();
    }
}
