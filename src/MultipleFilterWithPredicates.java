import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MultipleFilterWithPredicates {

    public List<Person> get( Long id, String name, List<Person> personList ) {
        Predicate<Person> idPredicate = d -> id.equals(d.getId());
        Predicate<Person> namePredicate = d -> name.equals(d.getName());
        List<Person> persons = personList.stream().filter( idPredicate.and(namePredicate) )
                .collect(Collectors.toList());
        return persons;
    }

}
