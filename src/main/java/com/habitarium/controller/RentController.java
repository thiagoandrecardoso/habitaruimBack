package com.habitarium.controller;

import com.habitarium.entity.MonthPaid;
import com.habitarium.entity.Rent;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RentController {

    public Rent copyRent(Rent rent) {
        return new Rent(rent.getId(), rent.getProperty(), rent.getLessor(), rent.getEntranceDate(), rent.getExitDate(),
                rent.getReadjustmentDate(), rent.getValue(), rent.getPayDay());
    }

    public boolean hasChangedDatesOrValue(Rent rent1, Rent rent2) {
        boolean hasDatesChanged = rent1.getEntranceDate().compareTo(rent2.getEntranceDate()) != 0 ||
                rent1.getExitDate().compareTo(rent2.getExitDate()) != 0;
        boolean hasPaydayChanged = rent1.getPayDay() != rent2.getPayDay();
        boolean hasValueChanged = rent1.getValue() != rent2.getValue();
        boolean hasReadjustmentChanged = rent1.getReadjustmentDate().compareTo(rent2.getReadjustmentDate()) != 0;

        return hasDatesChanged || hasPaydayChanged || hasValueChanged || hasReadjustmentChanged;
    }

    public List<MonthPaid> setMonthsToPay(Rent rent) {
        if (rent.getEntranceDate() == null || rent.getExitDate() == null) {
            throw new NullPointerException("Rent object has no entranceDate or exitDate attribute set!");
        }
        List<MonthPaid> oldMonthPaidList = rent.getMonthPaidList();
        List<MonthPaid> monthPaidList = initMonthPaidList(rent.getValue(), rent.getPayDay(), rent.getEntranceDate(),
                rent.getExitDate(), rent);

        if (oldMonthPaidList != null) {
            int size = Math.min(oldMonthPaidList.size(), monthPaidList.size());

            for (int i = 0; i < size; i++) {
                if (oldMonthPaidList.contains(monthPaidList.get(i))) {
                    int index = oldMonthPaidList.indexOf(monthPaidList.get(i));
                    monthPaidList.get(i).setPaid(oldMonthPaidList.get(index).isPaid());
                }
            }
        }

        return monthPaidList;
    }

    public List<MonthPaid> initMonthPaidList(float value, int day, Date start, Date end, Rent rent) {
        List<MonthPaid> monthsToBePaid = new ArrayList<>();
        YearMonth entranceDate = YearMonth.from(start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        YearMonth exitDate = YearMonth.from(end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        while (entranceDate.isBefore(exitDate.plusMonths(1L))) {
            int month = entranceDate.getMonthValue();
            int year = entranceDate.getYear();

            Date date = Date.from(LocalDate.of(year, month, day)
                    .atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            monthsToBePaid.add(new MonthPaid(date, value, false, rent));
            entranceDate = entranceDate.plusMonths(1L);
        }
        return monthsToBePaid;
    }
}
