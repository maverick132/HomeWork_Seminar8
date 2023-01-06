package ru.geekbrains.lesson8.MVP.presenters;

import ru.geekbrains.lesson8.MVP.models.Table;

import java.util.Collection;
import java.util.Date;

public class BookingPresenter implements ViewObserver {

    private final Model model;
    private final View view;
    private Collection<Table> tables;

    public BookingPresenter(Model model, View view) {
        this.model = model;
        this.view = view;
        this.view.setObserver(this);
    }


    /**
     * Получение списка столиков
     */
    public void loadTables() {
        tables = model.loadTables();
    }

    /**
     * Попросим View отобразить все столики
     */
    public void updateView() {
        view.showTables(tables);
    }

    protected void printReservationTableResult(int reservationNo) {
        view.printReservationTableResult(reservationNo);
    }

    @Override
    public void onReservationTable(Date orderDate, int tableNo, String name) {
        int reservationNo = model.reservationTable(orderDate, tableNo, name);
        printReservationTableResult(reservationNo);
    }

    @Override
    public boolean offReservationTable(int reservationId) {
        return (model.deleteReservationTable(reservationId)) ;
    }
}
