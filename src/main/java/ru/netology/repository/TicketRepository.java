package ru.netology.repository;

import ru.netology.domain.Ticket;

public class TicketRepository {
    private Ticket[] tickets = new Ticket[0];

    public Ticket[] getTickets() {
        return tickets;
    }

    public void setTickets(Ticket[] tickets) {
        this.tickets = tickets;
    }

    public Ticket[] findAll() {
        return tickets;
    }

    public boolean checkId(int id) {
        Ticket[] tmp = new Ticket[0];
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i].getId() == id) {
                return true;
            }
        }
        return false;
    }


    public void add(Ticket ticket) {
        if (checkId(ticket.getId()) == true) {
            throw new AlreadyExistsException("Id " + ticket.getId() + " already exists.");
        }

        Ticket[] tmp = new Ticket[tickets.length + 1];
        for (int i = 0; i < tickets.length; i++) {
            tmp[i] = tickets[i];
        }
        tmp[tmp.length - 1] = ticket;
        tickets = tmp;
    }

    public void removeById(int id) {
        if (checkId(id) == false) {
            throw new NotFoundException("Element with id: " + id + " not found");
        }

        Ticket[] tmp = new Ticket[tickets.length - 1];
        int copyToIndex = 0;
        for (Ticket ticket : tickets) {
            if (ticket.getId() != id) {
                tmp[copyToIndex] = ticket;
                copyToIndex++;
            }
        }
        tickets = tmp;
    }

    public void removeAll() {
        Ticket[] tmp = {};
        tickets = tmp;
    }
}
