package ru.netology.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ru.netology.repository.TicketRepository;


public class TicketManagerTest {
    TicketRepository repo = new TicketRepository();
    TicketManager manager = new TicketManager(repo);
    Ticket ticket1 = new Ticket(1, 2000, "DME", "LED", 45);
    Ticket ticket2 = new Ticket(2, 1200, "LED", "DME", 46);
    Ticket ticket3 = new Ticket(3, 15000, "SVO", "LED", 51);
    Ticket ticket4 = new Ticket(4, 900, "LED", "SVO", 52);
    Ticket ticket5 = new Ticket(5, 1600, "SVO", "GOJ", 39);
    Ticket ticket6 = new Ticket(6, 800, "LED", "SVO", 52);
    Ticket ticket7 = new Ticket(7, 2000, "DME", "LED", 45);
    Ticket ticket8 = new Ticket(8, 100, "DME", "LED", 45);
    Ticket ticket9 = new Ticket(9, 10000, "DME", "LED", 45);

    @Test
    public void shouldAddNewTickets() {
        manager.add(ticket1);
        manager.add(ticket2);

        assertArrayEquals(manager.getTickets(), new Ticket[]{ticket1, ticket2});
    }

    @Test
    public void shouldFindAllIfNoMatch() {
        repo.add(ticket1);
        repo.add(ticket2);
        repo.add(ticket3);
        repo.add(ticket4);
        repo.add(ticket5);
        repo.add(ticket6);
        Ticket[] expected = {};
        Ticket[] actual = manager.findAll("LED", "CDG");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindAllIfOneMatch() {
        repo.add(ticket1);
        repo.add(ticket2);
        repo.add(ticket3);
        repo.add(ticket4);
        repo.add(ticket5);
        repo.add(ticket6);
        Ticket[] expected = {ticket6, ticket4};
        Ticket[] actual = manager.findAll("LED", "SVO");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindAllAndSortIfMoreThanOneMatchesWithDifferentPrices() {
        repo.add(ticket2);
        repo.add(ticket3);
        repo.add(ticket4);
        repo.add(ticket5);
        repo.add(ticket6);
        repo.add(ticket7);
        repo.add(ticket8);
        repo.add(ticket9);
        Ticket[] expected = {ticket8, ticket7, ticket9};
        Ticket[] actual = manager.findAll("DME", "LED");

        assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldFindAllAndSortIfMoreThanOneMatchesAndPriceRepeats() {
        repo.add(ticket1);
        repo.add(ticket2);
        repo.add(ticket3);
        repo.add(ticket4);
        repo.add(ticket5);
        repo.add(ticket6);
        repo.add(ticket7);
        repo.add(ticket8);
        repo.add(ticket9);
        Ticket[] expected = {ticket8, ticket1, ticket7, ticket9};
        Ticket[] actual = manager.findAll("DME", "LED");

        assertArrayEquals(expected, actual);
    }
}

