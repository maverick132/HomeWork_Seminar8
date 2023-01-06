package ru.geekbrains.lesson8.MVP.presenters;

import java.util.Date;

public interface ViewObserver {
    void onReservationTable(Date orderDate, int tableNo, String name);

    boolean offReservationTable(int reservationId);
}
