package main.java.controller;

import main.java.dao.MonthPaidDAO;
import main.java.entity.MonthPaid;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MonthPaidController {
    public List<MonthPaid> lateMonthsInRange(List<MonthPaid> monthsPaid, Date start, Date finish) {
        List<MonthPaid> out = new ArrayList<>();
        for (MonthPaid monthPaid : monthsPaid) {
            Date month = monthPaid.getDate();
            if (!monthPaid.isPaid() &&
                    (month.compareTo(start) >= 0 && month.compareTo(finish) <= 0)) {
                out.add(monthPaid);
            }
        }
        return out;
    }

    public void deleteAll(List<MonthPaid> monthsPaid) {
        MonthPaidDAO monthPaidDAO = new MonthPaidDAO();
        monthPaidDAO.deleteAll(monthsPaid.stream()
                .map(MonthPaid::getId)
                .collect(Collectors.toList()));
    }
}
