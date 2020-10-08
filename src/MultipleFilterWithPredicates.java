import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MultipleFilterWithPredicates {

    public List<Person> get( Long id, String name, List<Person> personList ) {
        Predicate<Person> ccypairPredicate = d -> id.equals(d.getId());
        Predicate<Person> dateTimePredicate = d -> name.equals(d.getName());
        List<Person> persons = personList.stream().filter( ccypairPredicate.and(dateTimePredicate) )
                .collect(Collectors.toList());
        return persons;
    }

}
