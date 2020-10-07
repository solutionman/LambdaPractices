import java.util.Comparator;

public class Main {
    public static void main(String[] args){

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

    }
}
