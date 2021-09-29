package seedu.address.model.assignment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents an Assignment in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Assignment {

    private final Description description;
    private final DueDate dueDate;
    private final Status status;

    /**
     * Every field must be present and not null.
     */
    public Assignment(Description description, DueDate dueDate, Status status) {
        requireAllNonNull(description, dueDate, status);
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }

    public Description getDescription() {
        return description;
    }

    public DueDate getDueDate() {
        return dueDate;
    }

    public Status getStatus() {
        return status;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameAssignment(Assignment otherAssignment) {
        if (otherAssignment == this) {
            return true;
        }

        return otherAssignment != null
                && otherAssignment.getDescription().equals(getDescription());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Assignment)) {
            return false;
        }

        Assignment otherAssignment = (Assignment) other;
        return otherAssignment.getDescription().equals(getDescription())
                && otherAssignment.getDueDate().equals(getDueDate())
                && otherAssignment.getStatus().equals(getStatus());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, dueDate, status);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
                .append("; DueDate: ")
                .append(getDueDate())
                .append("; Status: ")
                .append(getStatus());
        return builder.toString();
    }
}
