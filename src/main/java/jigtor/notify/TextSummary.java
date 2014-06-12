package notify;

import jigtor.people.PeoplePair;
import lombok.Getter;

import java.util.List;

@Getter
public class TextSummary {
    private final String summary;

    public TextSummary(List<PeoplePair> pairs) {
        this.summary = summary(pairs);
    }

    private String summary(List<PeoplePair> pairs) {
        String summary = "";
        for (PeoplePair peoplePair : pairs) {
            summary += "Sponsee: " + peoplePair.getSponsee() + "\n";
            summary += "Sponsor: " + peoplePair.getSponsor() + "\n";
            summary += "\n";
        }
        return summary;
    }
}
