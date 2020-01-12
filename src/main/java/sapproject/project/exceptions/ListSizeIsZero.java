package sapproject.project.exceptions;

public class ListSizeIsZero extends RuntimeException {
    private static final long serialVersionUID = -457286283079665562L;

    public ListSizeIsZero(String listName) {
        super("List size is zero for list: " + listName);
    }
}
