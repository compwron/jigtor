package jigtor;

import jigtor.criteria.Region;
import jigtor.data.Jigsaw;
import jigtor.matcher.ByIdMatcher;
import jigtor.matcher.ComparativeSkillMatcher;
import jigtor.notify.EmailEngine;
import jigtor.people.ExperienceLevel;
import jigtor.people.PeoplePair;
import jigtor.people.Person;

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
        Person sponsee = people.get(0);
        List<PeoplePair> byIdPeopleMatchedPairs = byIdMatcher.matchByClosestOffset(sponsee, people, 1.5);
        System.out.println("Matches from Id Matcher: " + byIdMatcher);

//        2
        ComparativeSkillMatcher comparativeSkillMatcher = new ComparativeSkillMatcher();
        List<PeoplePair> skillMatches = comparativeSkillMatcher.matchWithin(sponsee, people);
        System.out.println("Matches from Skill Matcher: " + skillMatches);

        EmailEngine emailEngine = new EmailEngine();
        emailEngine.sendCCEmailToPeoplePairs(skillMatches);
    }
}
