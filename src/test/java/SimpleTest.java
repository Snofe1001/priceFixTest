import entity.PriceEntity;
import main.LogicClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class SimpleTest {

    private List<PriceEntity> oldPricesFirst = new ArrayList<>();
    private List<PriceEntity> newPricesFirst = new ArrayList<>();

    private List<PriceEntity> oldPricesSecond = new ArrayList<>();
    private List<PriceEntity> newPricesSecond = new ArrayList<>();

    @Before
    public void setUpFirstTest() {
        GregorianCalendar dateBegin = new GregorianCalendar();
        GregorianCalendar dateEnd = new GregorianCalendar();
        dateBegin.set(Calendar.MILLISECOND, 0);
        dateEnd.set(Calendar.MILLISECOND, 0);

        dateBegin.set(2019, Calendar.JANUARY, 1, 0, 0, 0);
        dateEnd.set(2019, Calendar.JANUARY, 31, 23, 59, 59);
        oldPricesFirst.add(new PriceEntity("122856", 1, 1, dateBegin.getTime(), dateEnd.getTime(), 11000));

        dateBegin.set(2019, Calendar.JANUARY, 10, 0, 0, 0);
        dateEnd.set(2019, Calendar.JANUARY, 20, 23, 59, 59);
        oldPricesFirst.add(new PriceEntity("122856", 2, 1, dateBegin.getTime(), dateEnd.getTime(), 99000));

        dateBegin.set(2019, Calendar.JANUARY, 1, 0, 0, 0);
        dateEnd.set(2019, Calendar.JANUARY, 31, 0, 0, 0);
        oldPricesFirst.add(new PriceEntity("6654", 1, 2, dateBegin.getTime(), dateEnd.getTime(), 5000));


        dateBegin.set(2019, Calendar.JANUARY, 20, 0, 0, 0);
        dateEnd.set(2019, Calendar.FEBRUARY, 20, 23, 59, 59);
        newPricesFirst.add(new PriceEntity("122856", 1, 1, dateBegin.getTime(), dateEnd.getTime(), 11000));

        dateBegin.set(2019, Calendar.JANUARY, 15, 0, 0, 0);
        dateEnd.set(2019, Calendar.JANUARY, 25, 23, 59, 59);
        newPricesFirst.add(new PriceEntity("122856", 2, 1, dateBegin.getTime(), dateEnd.getTime(), 92000));

        dateBegin.set(2019, Calendar.JANUARY, 12, 0, 0, 0);
        dateEnd.set(2019, Calendar.JANUARY, 13, 0, 0, 0);
        newPricesFirst.add(new PriceEntity("6654", 1, 2, dateBegin.getTime(), dateEnd.getTime(), 4000));
    }

    @Test
    public void fixPricesFirstTest() {
        LogicClass logicClass = new LogicClass();

        List<PriceEntity> expectedResult = new ArrayList<>();

        GregorianCalendar dateBegin = new GregorianCalendar();
        GregorianCalendar dateEnd = new GregorianCalendar();
        dateBegin.set(Calendar.MILLISECOND, 0);
        dateEnd.set(Calendar.MILLISECOND, 0);

        dateBegin.set(2019, Calendar.JANUARY, 1, 0, 0, 0);
        dateEnd.set(2019, Calendar.FEBRUARY, 20, 23, 59, 59);
        expectedResult.add(new PriceEntity("122856", 1, 1, dateBegin.getTime(), dateEnd.getTime(), 11000));

        dateBegin.set(2019, Calendar.JANUARY, 15, 0, 0, 0);
        dateEnd.set(2019, Calendar.JANUARY, 25, 23, 59, 59);
        expectedResult.add(new PriceEntity("122856", 2, 1, dateBegin.getTime(), dateEnd.getTime(), 92000));

        dateBegin.set(2019, Calendar.JANUARY, 10, 0, 0, 0);
        dateEnd.set(2019, Calendar.JANUARY, 15, 0, 0, 0);
        expectedResult.add(new PriceEntity("122856", 2, 1, dateBegin.getTime(), dateEnd.getTime(), 99000));

        dateBegin.set(2019, Calendar.JANUARY, 1, 0, 0, 0);
        dateEnd.set(2019, Calendar.JANUARY, 12, 0, 0, 0);
        expectedResult.add(new PriceEntity("6654", 1, 2, dateBegin.getTime(), dateEnd.getTime(), 5000));

        dateBegin.set(2019, Calendar.JANUARY, 12, 0, 0, 0);
        dateEnd.set(2019, Calendar.JANUARY, 13, 0, 0, 0);
        expectedResult.add(new PriceEntity("6654", 1, 2, dateBegin.getTime(), dateEnd.getTime(), 4000));

        dateBegin.set(2019, Calendar.JANUARY, 13, 0, 0, 0);
        dateEnd.set(2019, Calendar.JANUARY, 31, 0, 0, 0);
        expectedResult.add(new PriceEntity("6654", 1, 2, dateBegin.getTime(), dateEnd.getTime(), 5000));

        List<PriceEntity> actualResult = logicClass.setNewPrices(oldPricesFirst, newPricesFirst);

        Assert.assertEquals(expectedResult, actualResult);

    }

    @Before
    public void setUpSecondTest() {
        GregorianCalendar dateBegin = new GregorianCalendar();
        GregorianCalendar dateEnd = new GregorianCalendar();
        dateBegin.set(Calendar.MILLISECOND, 0);
        dateEnd.set(Calendar.MILLISECOND, 0);

        dateBegin.set(2019, Calendar.JANUARY, 1, 0, 0, 0);
        dateEnd.set(2019, Calendar.JANUARY, 31, 23, 59, 59);
        oldPricesSecond.add(new PriceEntity("122856", 1, 1, dateBegin.getTime(), dateEnd.getTime(), 11000));

        dateBegin.set(2019, Calendar.JANUARY, 5, 0, 0, 0);
        dateEnd.set(2019, Calendar.JANUARY, 6, 23, 59, 59);
        newPricesSecond.add(new PriceEntity("122856", 1, 1, dateBegin.getTime(), dateEnd.getTime(), 99999));

        dateBegin.set(2019, Calendar.JANUARY, 12, 0, 0, 0);
        dateEnd.set(2019, Calendar.JANUARY, 13, 23, 59, 59);
        newPricesSecond.add(new PriceEntity("122856", 1, 1, dateBegin.getTime(), dateEnd.getTime(), 99999));

        dateBegin.set(2019, Calendar.JANUARY, 21, 0, 0, 0);
        dateEnd.set(2019, Calendar.JANUARY, 23, 23, 59, 59);
        newPricesSecond.add(new PriceEntity("122856", 1, 1, dateBegin.getTime(), dateEnd.getTime(), 99000));

    }

    @Test
    public void fixPricesSecondTest() {
        LogicClass logicClass = new LogicClass();

        List<PriceEntity> expectedResult = new ArrayList<>();

        GregorianCalendar dateBegin = new GregorianCalendar();
        GregorianCalendar dateEnd = new GregorianCalendar();
        dateBegin.set(Calendar.MILLISECOND, 0);
        dateEnd.set(Calendar.MILLISECOND, 0);

        dateBegin.set(2019, Calendar.JANUARY, 1, 0, 0, 0);
        dateEnd.set(2019, Calendar.JANUARY, 5, 0, 0, 0);
        expectedResult.add(new PriceEntity("122856", 1, 1, dateBegin.getTime(), dateEnd.getTime(), 11000));

        dateBegin.set(2019, Calendar.JANUARY, 5, 0, 0, 0);
        dateEnd.set(2019, Calendar.JANUARY, 6, 23, 59, 59);
        expectedResult.add(new PriceEntity("122856", 1, 1, dateBegin.getTime(), dateEnd.getTime(), 99999));

        dateBegin.set(2019, Calendar.JANUARY, 6, 23, 59, 59);
        dateEnd.set(2019, Calendar.JANUARY, 12, 0, 0, 0);
        expectedResult.add(new PriceEntity("122856", 1, 1, dateBegin.getTime(), dateEnd.getTime(), 11000));

        dateBegin.set(2019, Calendar.JANUARY, 12, 0, 0, 0);
        dateEnd.set(2019, Calendar.JANUARY, 13, 23, 59, 59);
        expectedResult.add(new PriceEntity("122856", 1, 1, dateBegin.getTime(), dateEnd.getTime(), 99999));

        dateBegin.set(2019, Calendar.JANUARY, 13, 23, 59, 59);
        dateEnd.set(2019, Calendar.JANUARY, 21, 0, 0, 0);
        expectedResult.add(new PriceEntity("122856", 1, 1, dateBegin.getTime(), dateEnd.getTime(), 11000));

        dateBegin.set(2019, Calendar.JANUARY, 21, 0, 0, 0);
        dateEnd.set(2019, Calendar.JANUARY, 23, 23, 59, 59);
        expectedResult.add(new PriceEntity("122856", 1, 1, dateBegin.getTime(), dateEnd.getTime(), 99000));

        dateBegin.set(2019, Calendar.JANUARY, 23, 23, 59, 59);
        dateEnd.set(2019, Calendar.JANUARY, 31, 23, 59, 59);
        expectedResult.add(new PriceEntity("122856", 1, 1, dateBegin.getTime(), dateEnd.getTime(), 11000));

        List<PriceEntity> actualResult = logicClass.setNewPrices(oldPricesSecond, newPricesSecond);

        Assert.assertEquals(expectedResult, actualResult);

    }
}
