package seedu.address.logic.commands.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.contact.ContactIsTaggedPredicate;
import seedu.address.model.tag.Tag;

/**
 * Filters contacts in JobFestGo by {@code Tag}.
 */
public class FilterByTagCommand extends Command {
    public static final String COMMAND_WORD = "filter_by_tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": filters and displays all contacts who "

            + " are tagged with the input tag.\n"
            + "Parameters: "
            + PREFIX_TAG + "TAG\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "vendor ";

    private final List<Tag> tags;
    private final ContactIsTaggedPredicate predicate;

    /**
     * Creates a FilterCommand to filter Contacts in JobFestGo.
     */
    public FilterByTagCommand(List<Tag> tags, ContactIsTaggedPredicate predicate) {

        this.tags = tags;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        // Update the filtered lists accordingly.
        // Flow of command returns back to the main dashboard.
        model.updateFilteredContactList(predicate);
        model.updateFilteredTaskList(Model.PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(
                String.format(Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW, model.getFilteredContactList().size()),
                false);

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterByTagCommand)) {
            return false;
        }

        FilterByTagCommand otherFilterCommand = (FilterByTagCommand) other;
        return tags.equals(otherFilterCommand.tags) && predicate.equals(otherFilterCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
