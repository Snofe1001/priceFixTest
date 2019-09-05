package main;

import entity.PriceEntity;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        GregorianCalendar dateBegin = new GregorianCalendar();
        GregorianCalendar dateEnd = new GregorianCalendar();
        dateBegin.set(2019, Calendar.JANUARY, 1, 0, 0, 0);
        dateEnd.set(2019, Calendar.JANUARY, 31, 23, 59, 59);

        List<PriceEntity> oldPrices = new ArrayList<>();
        List<PriceEntity> newPrices = new ArrayList<>();

        PriceEntity oldPrice1 = new PriceEntity("122856", 1, 1, dateBegin.getTime(), dateEnd.getTime(), 11000);

        dateBegin.set(2019, Calendar.JANUARY, 10, 0, 0, 0);
        dateEnd.set(2019, Calendar.JANUARY, 20, 23, 59, 59);

        PriceEntity oldPrice2 = new PriceEntity("122856", 2, 1, dateBegin.getTime(), dateEnd.getTime(), 99000);

        dateBegin.set(2019, Calendar.JANUARY, 1, 0, 0, 0);
        dateEnd.set(2019, Calendar.JANUARY, 31, 0, 0, 0);

        PriceEntity oldPrice3 = new PriceEntity("6654", 1, 2, dateBegin.getTime(), dateEnd.getTime(), 5000);

        oldPrices.add(oldPrice1);
        oldPrices.add(oldPrice2);
        oldPrices.add(oldPrice3);

        dateBegin.set(2019, Calendar.JANUARY, 20, 0, 0, 0);
        dateEnd.set(2019, Calendar.FEBRUARY, 20, 23, 59, 59);

        PriceEntity newPrice1 = new PriceEntity("122856", 1, 1, dateBegin.getTime(), dateEnd.getTime(), 11000);

        dateBegin.set(2019, Calendar.JANUARY, 15, 0, 0, 0);
        dateEnd.set(2019, Calendar.JANUARY, 25, 23, 59, 59);

        PriceEntity newPrice2 = new PriceEntity("122856", 2, 1, dateBegin.getTime(), dateEnd.getTime(), 92000);

        dateBegin.set(2019, Calendar.JANUARY, 12, 0, 0, 0);
        dateEnd.set(2019, Calendar.JANUARY, 13, 0, 0, 0);

        PriceEntity newPrice3 = new PriceEntity("6654", 1, 2, dateBegin.getTime(), dateEnd.getTime(), 4000);

        newPrices.add(newPrice1);
        newPrices.add(newPrice2);
        newPrices.add(newPrice3);

        LogicClass logicClass = new LogicClass();
        List<PriceEntity> result = logicClass.setNewPrices(oldPrices, newPrices);
        System.out.println(result.get(0).getValue());

    }


}
