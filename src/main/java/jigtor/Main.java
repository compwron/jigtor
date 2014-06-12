package jigtor;

import com.google.common.base.Joiner;
import jigtor.criteria.Region;
import jigtor.data.Jigsaw;
import jigtor.matcher.ByIdMatcher;
import jigtor.matcher.ComparativeSkillMatcher;
import jigtor.notify.EmailEngine;
import jigtor.people.ExperienceLevel;
import jigtor.people.PeoplePair;
import jigtor.people.Person;
import jigtor.people.SponsorMatches;

import java.util.List;

public class Main {

    public static void main(String... args) {
//        get from jigsaw
//        pass to algorithm
//        send emails / put to UI

        Jigsaw jigsaw = new Jigsaw();
        List<Person> people = jigsaw.query(new ExperienceLevel(Region.Chicago));


//        For A/B testing

//        1
        ByIdMatcher byIdMatcher = new ByIdMatcher();
        List<PeoplePair> byIdPeopleMatchedPairs = byIdMatcher.matchByClosestOffset(people.get(0), people, 1.5);
        System.out.println("Matches from Id Matcher: \n" + Joiner.on("\n").join(byIdPeopleMatchedPairs));

//        2
        for (Person sponsee : people) {
            ComparativeSkillMatcher comparativeSkillMatcher = new ComparativeSkillMatcher();
            SponsorMatches sponsorMatches = comparativeSkillMatcher.matchWithin(sponsee, people);
            List<PeoplePair> skillMatches = sponsorMatches.allMatches();
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            System.out.println("\n\nTop match from Skill Matcher for " + sponsee.getName() + ": \n" + sponsorMatches.topMatch());
            System.out.println("\nMatches from Skill Matcher for " + sponsee.getName() + ": \n" + Joiner.on("\n").join(skillMatches));

            EmailEngine emailEngine = new EmailEngine();
            emailEngine.sendCCEmailToPeoplePairs(skillMatches);
        }
    }
}
