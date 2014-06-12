package jigtor.data;

import jigtor.criteria.Knowledge;
import jigtor.criteria.SkillSetBuilder;
import jigtor.people.ExperienceLevel;
import jigtor.people.Person;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Jigsaw {

    public List<Person> query(ExperienceLevel experience) {
        return fakeData();
    }

    private List<Person> fakeData() {
        String scala = "scala";
        String clojure = "clojure";
        String javascript = "javascript";

        Person alice = new Person("Alice", 0.2, new SkillSetBuilder().withSkill(scala, Knowledge.WantToKnow).build(), 5);
        Person betty = new Person("Betty", 0.2, new SkillSetBuilder().withSkill(clojure, Knowledge.WantToKnow).build(), 4);
        Person caroline = new Person("Caroline", 10, new SkillSetBuilder().withSkill(scala, Knowledge.Knows).withSkill(clojure, Knowledge.Knows).build(), 1);
        Person darina = new Person("Darina", 0.3, new SkillSetBuilder().withSkill(scala, Knowledge.Moderate).withSkill(clojure, Knowledge.WantToKnow).build(), 3);
        Person erin = new Person("Erin", 1.2, new SkillSetBuilder().withSkill(scala, Knowledge.Knows).withSkill(clojure, Knowledge.WantToKnow).build(), 2);
        Person xamara = new Person("Xamara", 1.2, new SkillSetBuilder().withSkill(javascript, Knowledge.Knows).withSkill(clojure, Knowledge.Knows).build(), 2);
        Person yasmin = new Person("Yasmin", 1.2, new SkillSetBuilder().withSkill(javascript, Knowledge.Knows).withSkill(scala, Knowledge.Knows).build(), 2);
        Person zara = new Person("Zara", 1.2, new SkillSetBuilder().withSkill(javascript, Knowledge.WantToKnow).withSkill(scala, Knowledge.WantToKnow).build(), 2);
        List<Person> fakeData = newArrayList(alice, betty, caroline, darina, erin, xamara, yasmin);
        return fakeData;
    }

//    Might hit API, parse from CSV, database sump, ???
}
