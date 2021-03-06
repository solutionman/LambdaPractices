public class CommandPattern {
    public interface Command {
        void execute(Object data);
    }

    public static class PrintCommand implements Command {
        public void execute(Object data) {
            System.out.println(data.toString());
        }
    }

    public static void callCommand(Command command, Object data) {
        command.execute(data);
    }

    public static void main(String... args) {
        callCommand(new PrintCommand(), "This is command pattern example");
    }
}
