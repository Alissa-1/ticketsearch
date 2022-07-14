package ru.netology.domain;

import ru.netology.repository.TicketRepository;

import java.util.Arrays;
import java.util.Comparator;

public class TicketManager {
    private TicketRepository repo;

    public TicketManager(TicketRepository repo) {
        this.repo = repo;
    }

    public Ticket[] getTickets() {
        return repo.getTickets();
    }

    public void add(Ticket ticket) {
        repo.add(ticket);
    }

    public Ticket[] findAllRepo() {
        return repo.findAll();
    }

    public boolean matchesDepartureAirport(Ticket ticket, String departureAirport) {
        if (ticket.getDepartureAirport().equals(departureAirport)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean matchesArrivalAirport(Ticket ticket, String arrivalAirport) {
        if (ticket.getArrivalAirport().equals(arrivalAirport)) {
            return true;
        } else {
            return false;
        }
    }

    public Ticket[] findAll(String from, String to) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repo.findAll()) {
            Ticket[] tmp = new Ticket[result.length + 1];

            if (matchesDepartureAirport(ticket, from)) {
                if (matchesArrivalAirport(ticket, to)) {
                    for (int i = 0; i < result.length; i++) {
                        tmp[i] = result[i];
                    }
                    tmp[tmp.length - 1] = ticket;
                    result = tmp;
                }
            }
        }
        Arrays.sort(result);
        return result;
    }

    public Ticket[] findAllFast(String from, String to, Comparator<Ticket> comparator) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repo.findAll()) {
            Ticket[] tmp = new Ticket[result.length + 1];

            if (matchesDepartureAirport(ticket, from)) {
                if (matchesArrivalAirport(ticket, to)) {
                    for (int i = 0; i < result.length; i++) {
                        tmp[i] = result[i];
                    }
                    tmp[tmp.length - 1] = ticket;
                    result = tmp;
                }
            }
        }
        Arrays.sort(result); // без этого среди двух билетов с одинаковой скоростью не будет естественной сортировки
        Arrays.sort(result, comparator);
        return result;
    }
}
