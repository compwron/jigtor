package jigtor.people;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Getter
public class PeoplePair {
    private final Person sponsee;
    private final Person sponsor;
}
