package seedu.address.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Person;
import seedu.address.model.person.Module;
import seedu.address.testutil.AssignmentBuilder;
import seedu.address.testutil.PersonBuilder;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

class AddAssignmentToAllCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newAssignmentToAll_success() {
        Assignment validAssignment = new AssignmentBuilder().withDescription("Assignment 1").build();

        Person amy = new PersonBuilder().withModule("CS2106").build();
        Person alice = new PersonBuilder().withName("Alice").withModule("CS2106").build();
        Module validModule = amy.getModule();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(amy);
        expectedModel.addPerson(alice);
        expectedModel.addAssignment(amy, validAssignment);
        expectedModel.addAssignment(alice, validAssignment);
        model.addPerson(amy);
        model.addPerson(alice);

        assertCommandSuccess(new AddAssignmentToAllCommand(validModule, validAssignment), model,
                String.format(AddAssignmentToAllCommand.MESSAGE_SUCCESS, validModule, validAssignment), expectedModel);
    }

    @Test
    public void execute_duplicateNotAddedForExisting_success() {
        Assignment validAssignment = new AssignmentBuilder().withDescription("Assignment 1").build();

        Person amy = new PersonBuilder().withModule("CS2106")
                .withAssignmentList(new String[] {"Assignment 1", "28 Sep 2021, 06:00 PM", "PENDING"}).build();
        Person alice = new PersonBuilder().withName("Alice").withModule("CS2106").build();
        Module validModule = amy.getModule();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(amy);
        expectedModel.addPerson(alice);
        expectedModel.addAssignment(alice, validAssignment);
        model.addPerson(amy);
        model.addPerson(alice);

        assertCommandSuccess(new AddAssignmentToAllCommand(validModule, validAssignment), model,
                String.format(AddAssignmentToAllCommand.MESSAGE_SUCCESS, validModule, validAssignment), expectedModel);
    }
}