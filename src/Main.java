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

    }
}
