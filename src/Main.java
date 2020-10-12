import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args){

        Thread t = new Thread ( () -> System.out.println("Hello Lambda") );
        t.start();

        Runnable runnable = new Runnable(){
            @Override
            public void run(){
                System.out.println("anonymous class implementation");
            }
        };
        RunnableForLambdas.doSomething(runnable);

        // the same with lambda
        Runnable runnableLambda = () -> System.out.println("Runnable with lambda");
        RunnableForLambdas.doSomething(runnableLambda);

        // lambda short version
        RunnableForLambdas.doSomething(() -> System.out.println("Runnable with short version lambda"));


        // comparing Persons
        Person person1 = new Person(1L,"first");
        Person person2 = new Person(2L, "second");
        //
        Comparator<Person> compareById = new Comparator<Person>() {
            @Override
            public int compare(Person person1, Person person2) {
                return person1.getId().compareTo(person2.getId());
            }
        };
        int result = compareById.compare(person1, person2);
        System.out.println(result);

        Comparator<Person> compareByIdLambda = (person11, person21) -> person11.getId().compareTo(person21.getId());
        int result2 = compareByIdLambda.compare(person1, person2);
        System.out.println(result2);

        Comparator<Person> compareShort = Comparator.comparing(Person::getId);
        int result3 = compareShort.compare(person1, person2);
        System.out.println(result3);


        // Create anonymous class from interface and print message
        ForAnonymousClass anonymousClass = new ForAnonymousClass() {
            @Override
            public void doSomething() {
                System.out.println("Message from anonymous class");
            }
        };
        anonymousClass.doSomething();
        // do the same with lambda
        ForAnonymousClass anonymousClassLambda = () -> System.out.println("Message from anonymous class created with lambda");
        anonymousClassLambda.doSomething();

        
        List<Person> persons = new ArrayList<>();
        persons.add(person1);
        persons.add(person2);
        List<String> names = persons.stream()
                .map( s -> s.getName().toUpperCase() )
                .collect( Collectors.toList() );
        for( String name: names ){
            System.out.println( name );
        }

        
        // streams: count persons with even id and name equals "second"
        long count = 0;
        for(Person person : persons){
            if( person.getId() % 2 == 0 && person.getName().equals("second") ){
                count++;
            }
        }
        System.out.println( count );

        // the same, using streams
        Stream<Person> stream = persons.stream();
        stream = stream.filter( person -> person.getId() % 2 == 0 );
        stream = stream.filter( person -> person.getName().equals("second") );
        long countWithStream = stream.count();
        System.out.println( countWithStream );

        // short version with streams
        long countWithStreams = persons.stream().filter( person -> person.getId() % 2 == 0 )
                .filter( person -> person.getName().equals("second") ).count();
        System.out.println( countWithStreams );

        // Predicate - returns true or false
        Predicate<Integer> negative = i -> i < 0;
        System.out.println( "Is 1 negative? " + negative.test(1) );
        System.out.println( "Is -1 negative? " + negative.test(-1) );
        System.out.println( "Is 0 negative? " + negative.test(0) );
        // the same with string
        Predicate<String> containsF = s -> s.contains("f");
        System.out.println( "Does " + person1.getName() + " contains f ? " + containsF.test(person1.getName()));
        System.out.println( "Does " + person2.getName() + " contains f ? " + containsF.test(person2.getName()));

        // Consumer - so something with input, but do not return anything
        Consumer<String> printUpperCase = s -> System.out.println(s.toUpperCase());
        printUpperCase.accept( person1.getName() );

        Consumer<String> printLowerCase = s -> System.out.println(s.toLowerCase());
        printUpperCase.andThen( printLowerCase ).accept( person2.getName() );

        // Supplier return result without input
        Supplier<String> stringSupplier = () -> person1.getName().toUpperCase();
        System.out.println( stringSupplier.get() );

        MultipleFilterWithPredicates multipleFilterWithPredicates = new MultipleFilterWithPredicates();
        List<Person> filteredPersonsList = multipleFilterWithPredicates.get(1L, "first", persons);
        for( Person person : filteredPersonsList ){
            System.out.println( "id: " + person.getId() + " name: " + person.getName());
        }
    }
}
