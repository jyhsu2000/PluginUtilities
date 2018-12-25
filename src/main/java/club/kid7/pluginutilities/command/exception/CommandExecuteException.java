package club.kid7.pluginutilities.command.exception;

/**
 * Thrown when an executed command raises an error or when execution of
 * the command failed.
 */
public class CommandExecuteException extends Exception {
    public CommandExecuteException(String message) {
        super(message);
    }
}
