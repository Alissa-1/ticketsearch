package ru.netology.domain;

import java.util.Comparator;

public class TicketByTimeInTravelAscComparator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket o1, Ticket o2) {
        return o1.getTravelTimeInMinutes() - o2.getTravelTimeInMinutes();
    }
}
