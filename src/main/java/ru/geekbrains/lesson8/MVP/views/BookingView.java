package ru.geekbrains.lesson8.MVP.views;

import ru.geekbrains.lesson8.MVP.models.Table;
import ru.geekbrains.lesson8.MVP.presenters.View;
import ru.geekbrains.lesson8.MVP.presenters.ViewObserver;

import java.util.Collection;
import java.util.Date;

public class BookingView implements View {

    private ViewObserver observer;
    public void showTables(Collection<Table> tables){
        for (Table table: tables) {
            System.out.println(table);
        }
    }

    @Override
    public void setObserver(ViewObserver observer) {
        this.observer = observer;
    }

    /**
     * Действие клиента, событие, клиент нажал на кнопку бронирования
     * @param reservationDate
     * @param tableNo
     * @param name
     */
    public void reservationTable(Date reservationDate, int tableNo, String name){
        observer.onReservationTable(reservationDate, tableNo, name);
    }
    public void deleteReservationTable(int reservationId){
        if (observer.offReservationTable(reservationId))
            System.out.printf("Бронь #%d успешно отменена!\n", reservationId);
        else {
            System.out.printf("Бронь #%d не найдена!\n", reservationId);
        }
    }

    /**
     * TODO: ДОМАШНЯЯ РАБОТА: Доработать метод changeReservationTable, протянуть возможность изменения
     * резерва столика на уровне компонент BookingPresenter и BookingModel
     * @param reservationDate
     * @param tableNo
     * @param name
     */

    public void changeReservationTable(int oldReservation, Date reservationDate, int tableNo, String name){
        //TODO: Доработать метод
        deleteReservationTable(oldReservation);
        observer.onReservationTable(reservationDate, tableNo, name);
    }

    /**
     * Распечатать номер брони, после заказа столика
     * @param reservationId
     */
    public void printReservationTableResult(int reservationId){
        System.out.printf("Столик успешно забронирован. Номер вашей брони: #%d\n", reservationId);
    }

}
