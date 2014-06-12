package jigtor.matcher;

import jigtor.criteria.Knowledge;
import jigtor.criteria.SkillSet;
import jigtor.criteria.SkillSetBuilder;
import jigtor.people.Person;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PersonTest {

    private Person jake = new Person("jake", 1.0, scalaSkill(Knowledge.WantToKnow), 503);
    private Person kirk = new Person("kirk", 1.0, scalaSkill(Knowledge.Knows), 504);

    @Test
    public void knowsWhenPersonDoesNotHaveKnowledgeThatSeekerIsLookingFor() {
        assertThat(jake.knowsWhatSeekerWantsToKnow(kirk), is(false));
    }

    @Test
    public void knowsWhenPersonHasKnowledgeThatSeekerIsLookingFor() {
        assertThat(kirk.knowsWhatSeekerWantsToKnow(jake), is(true));
    }

    @Test
    public void midlevelKnowledgeMatchDoesNotCount() {
        Person linda = new Person("linda", 1.0, scalaSkill(Knowledge.Moderate), 503);
        Person marcus = new Person("marcus", 1.0, scalaSkill(Knowledge.ALittle), 504);

        assertThat(linda.knowsWhatSeekerWantsToKnow(marcus), is(false));
        assertThat(marcus.knowsWhatSeekerWantsToKnow(linda), is(false));
    }

    private SkillSet scalaSkill(Knowledge knowledge) {
        return new SkillSetBuilder().withSkill("scala", knowledge).build();
    }

}
