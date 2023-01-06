package ru.geekbrains.lesson8.MVP.models;

import ru.geekbrains.lesson8.MVP.presenters.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

public class BookingModel implements Model {

    private Collection<Table> tables;

    /**
     * Получить список столиков
     *
     * @return
     */
    public Collection<Table> loadTables() {

        if (tables == null) {
            tables = new ArrayList<>();

            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
        }

        return tables;

    }

    /**
     * Бронирование столика
     *
     * @param reservationDate
     * @param tableNo
     * @param name
     * @return
     */
    public int reservationTable(Date reservationDate, int tableNo, String name) {
        Optional<Table> table = loadTables().stream().filter(t -> t.getNo() == tableNo).findFirst();
        if (table.isPresent()) {
            Reservation reservation = new Reservation(name, reservationDate);
            table.get().getReservations().add(reservation);
            return reservation.getId();
        }
        throw new RuntimeException("Некорректный номер столика.");
    }

    public boolean deleteReservationTable(int reservationId) {
        for (Table t : tables) {
            if (t.getReservations().removeIf(e -> e.getId() == reservationId))
                return true;
            }
        return false;
    }


}
