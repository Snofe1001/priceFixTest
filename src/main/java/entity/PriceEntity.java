package entity;

import java.util.Date;

public class PriceEntity {

    private long id; // идентификатор в БД
    private String productCode; // код товара
    private int number; // номер цены
    private int depart; // номер отдела
    private Date begin; // начало действия
    private Date end; // конец действия
    private long value; // значение цены в копейках

    public PriceEntity(PriceEntity priceEntity) {
        this.productCode = priceEntity.getProductCode();
        this.number = priceEntity.getNumber();
        this.depart = priceEntity.getDepart();
        this.begin = priceEntity.getBegin();
        this.end = priceEntity.getEnd();
        this.value = priceEntity.getValue();
    }

    public PriceEntity(String productCode, int number, int depart, Date begin, Date end, long value) {
        this.productCode = productCode;
        this.number = number;
        this.depart = depart;
        this.begin = begin;
        this.end = end;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDepart() {
        return depart;
    }

    public void setDepart(int depart) {
        this.depart = depart;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        PriceEntity priceEntity = (PriceEntity) obj;
        return (priceEntity.getProductCode().equals(this.getProductCode()) &&
                priceEntity.getBegin().equals(this.getBegin()) &&
                priceEntity.getEnd().equals(this.getEnd()) &&
                priceEntity.getNumber() == this.getNumber() &&
                priceEntity.getDepart() == this.getDepart() &&
                priceEntity.getValue() == this.getValue());
    }

    @Override
    public int hashCode() {
        return (int) this.getId() * 31;
    }

    @Override
    public String toString() {
        return "Product = " + this.getProductCode() +
                "Depart = " + this.getDepart() +
                "Number = " + this.getNumber() +
                "Value = " + this.getValue() +
                "Date begin = " + this.getBegin() +
                "Date end = " + this.getEnd();
    }
}
