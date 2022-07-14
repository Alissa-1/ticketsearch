package ru.netology.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ru.netology.repository.AlreadyExistsException;
import ru.netology.repository.NotFoundException;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;

public class TicketRepositoryTest {
    TicketRepository repo = new TicketRepository();
    Ticket ticket1 = new Ticket(1, 1000, "DME", "LED", 45);
    Ticket ticket2 = new Ticket(2, 1200, "LED", "DME", 46);
    Ticket ticket3 = new Ticket(3, 15000, "SVO", "LED", 51);
    Ticket ticket4 = new Ticket(4, 900, "LED", "SVO", 52);
    Ticket ticket5 = new Ticket(5, 1600, "SVO", "GOJ", 39);
    Ticket ticket6 = new Ticket(6, 800, "LED", "SVO", 52);

    @Test
    public void shouldAddNewTickets() {
        repo.add(ticket1);
        repo.add(ticket2);
        Ticket[] actual = repo.findAll();
        Ticket[] expected = {ticket1, ticket2};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddTicketIfIdTheSame() {
        repo.add(ticket1);

        assertThrows(AlreadyExistsException.class, ()-> {
            repo.add(ticket1);
        });
    }

    @Test
    public void shouldFindAllTicketsIfNone() {
        Ticket[] actual = repo.findAll();
        Ticket[] expected = {};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldCheckIdIfIdExists() {
        repo.add(ticket1);
        repo.add(ticket2);
        boolean expected = true;
        boolean actual = repo.checkId(2);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCheckIdIfIdDoesNotExist() {
        repo.add(ticket1);
        repo.add(ticket2);
        boolean expected = false;
        boolean actual = repo.checkId(15);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByIdOneTicketIfIdContains() {
        repo.add(ticket1);
        repo.add(ticket2);

        repo.removeById(1);
        Ticket[] actual = repo.findAll();
        Ticket[] expected = {ticket2};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByIdOneTicketIfIdDoesntContain() {
        repo.add(ticket1);
        repo.add(ticket2);

        assertThrows(NotFoundException.class, ()-> {
            repo.removeById(15);
        });
    }

    @Test
    public void shouldRemoveAll() {
        repo.removeAll();
        Ticket[] actual = repo.findAll();
        Ticket[] expected = {};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortByAscendingPriceDefault() {
        Ticket[] tickets = {ticket1, ticket2, ticket3, ticket4, ticket5};
        Ticket[] expected = {ticket4, ticket1, ticket2, ticket5, ticket3};
        Arrays.sort(tickets);
        Ticket[] actual = tickets;
        assertArrayEquals(expected, actual);
    }
}
