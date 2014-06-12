package jigtor.matcher;

import jigtor.criteria.Knowledge;
import jigtor.criteria.SkillSet;
import jigtor.criteria.SkillSetBuilder;
import jigtor.people.Person;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PersonTest {

    private Double timeAtCompany = 1.0;
    private Integer employeeNumber = 1;
    private Person jake = new Person("jake", timeAtCompany, scalaSkill(Knowledge.WantToKnow), employeeNumber);
    private Person kirk = new Person("kirk", timeAtCompany, scalaSkill(Knowledge.Knows), employeeNumber);
    private Person linda = new Person("linda", timeAtCompany, scalaSkill(Knowledge.Moderate), employeeNumber);
    private Person marcus = new Person("marcus", timeAtCompany, scalaSkill(Knowledge.ALittle), employeeNumber);

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


        assertThat(linda.knowsWhatSeekerWantsToKnow(marcus), is(false));
        assertThat(marcus.knowsWhatSeekerWantsToKnow(linda), is(false));
    }

    private SkillSet scalaSkill(Knowledge knowledge) {
        return new SkillSetBuilder().withSkill("scala", knowledge).build();
    }

    @Test
    public void skillMatchIsHigh() {
        SkillSet wantsTwoSkills = new SkillSetBuilder().withSkill("scala", Knowledge.WantToKnow).withSkill("clojure", Knowledge.WantToKnow).build();
        Person a = new Person("A", timeAtCompany, wantsTwoSkills, employeeNumber);

        SkillSet hasOneSkill = new SkillSetBuilder().withSkill("scala", Knowledge.Knows).build();
        Person b = new Person("B", timeAtCompany, hasOneSkill, employeeNumber);
        int pointsForKnowDifferential = Knowledge.Knows.getKnowledgeAmount() - Knowledge.WantToKnow.getKnowledgeAmount();
        assertThat(a.canLearnFrom(b), is(pointsForKnowDifferential));

        SkillSet hasTwoSkills = new SkillSetBuilder().withSkill("scala", Knowledge.Knows).withSkill("clojure", Knowledge.Knows).build();
        Person c = new Person("C", timeAtCompany, hasTwoSkills, employeeNumber);
        assertThat(a.canLearnFrom(c), is(pointsForKnowDifferential * 2));
    }
}
