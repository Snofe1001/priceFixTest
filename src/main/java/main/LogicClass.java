package main;

import entity.PriceEntity;

import java.util.ArrayList;
import java.util.List;

public class LogicClass {

    public List<PriceEntity> setNewPrices(List<PriceEntity> oldPrices, List<PriceEntity> newPrices) {
        List<PriceEntity> result = new ArrayList<>();
        List<String> products = getProductsList(oldPrices, newPrices);
        List<Integer> departs = getDepartsList(oldPrices, newPrices);

        for (PriceEntity newPrice : newPrices) {
            if (!products.contains(newPrice.getProductCode())) {
                result.add(newPrice);
            }
        }

        for (String product : products) {
            List<PriceEntity> newPricesByProduct = getPriceByProduct(product, newPrices);
            List<PriceEntity> oldPricesByProduct = getPriceByProduct(product, oldPrices);

            if (newPricesByProduct.isEmpty()) {
                result.addAll(oldPricesByProduct);
                continue;
            } else if (oldPricesByProduct.isEmpty()) {
                result.addAll(newPricesByProduct);
                continue;
            }

            for (int depart : departs) {

                List<PriceEntity> newPricesByDepart = getPriceByDepart(depart, newPricesByProduct);
                List<PriceEntity> oldPricesByDepart = getPriceByDepart(depart, oldPricesByProduct);

                if (newPricesByDepart.isEmpty()) {
                    result.addAll(oldPricesByDepart);
                    continue;
                } else if (oldPricesByDepart.isEmpty()) {
                    result.addAll(newPricesByDepart);
                    continue;
                }


                for (int number = 1; number < 5; number++) {
                    List<PriceEntity> newPricesByNumber = getPriceByNumber(number, newPricesByDepart);
                    List<PriceEntity> oldPricesByNumber = getPriceByNumber(number, oldPricesByDepart);
                    if (newPricesByNumber.isEmpty() && !oldPricesByNumber.isEmpty()) {
                        result.addAll(oldPricesByNumber);
                        continue;
                    } else if (!newPricesByNumber.isEmpty() && oldPricesByNumber.isEmpty()) {
                        result.addAll(newPricesByNumber);
                        continue;
                    } else if (newPricesByNumber.isEmpty() && oldPricesByNumber.isEmpty()) {
                        continue;
                    }
                    List<PriceEntity> resultPricesByNumber = new ArrayList<>();
                    for (PriceEntity oldPriceByNumber : oldPricesByNumber) {
                        resultPricesByNumber.add(oldPriceByNumber);
                        for (PriceEntity newPriceByNumber : newPricesByNumber) {
                            boolean flagChangedPrice = false;
                            for (PriceEntity resultPriceByNumber : resultPricesByNumber) {
                                if (resultPriceByNumber.getBegin().before(newPriceByNumber.getBegin()) && resultPriceByNumber.getEnd().after(newPriceByNumber.getEnd())) {
                                    if (!(resultPriceByNumber.getValue() == newPriceByNumber.getValue())) {
                                        PriceEntity newPrice1 = new PriceEntity(resultPriceByNumber);
                                        newPrice1.setEnd(newPriceByNumber.getBegin());
                                        PriceEntity newPrice2 = new PriceEntity(newPriceByNumber);
                                        PriceEntity newPrice3 = new PriceEntity(resultPriceByNumber);
                                        newPrice3.setBegin(newPriceByNumber.getEnd());
                                        resultPricesByNumber.remove(resultPriceByNumber);
                                        resultPricesByNumber.add(newPrice1);
                                        resultPricesByNumber.add(newPrice2);
                                        resultPricesByNumber.add(newPrice3);
                                        flagChangedPrice = true;
                                        break;
                                    } else {
                                        flagChangedPrice = true;
                                        break;
                                    }
                                } else if (resultPriceByNumber.getBegin().before(newPriceByNumber.getBegin()) && resultPriceByNumber.getEnd().before(newPriceByNumber.getEnd())
                                        && resultPriceByNumber.getEnd().after(newPriceByNumber.getBegin())) {
                                    if (!(resultPriceByNumber.getValue() == newPriceByNumber.getValue())) {
                                        PriceEntity newPrice1 = new PriceEntity(newPriceByNumber);
                                        PriceEntity newPrice2 = new PriceEntity(resultPriceByNumber);
                                        newPrice2.setEnd(newPriceByNumber.getBegin());
                                        resultPricesByNumber.remove(resultPriceByNumber);
                                        resultPricesByNumber.add(newPrice1);
                                        resultPricesByNumber.add(newPrice2);
                                        flagChangedPrice = true;
                                        break;
                                    } else {
                                        resultPriceByNumber.setEnd(newPriceByNumber.getEnd());
                                        flagChangedPrice = true;
                                        break;
                                    }
                                } else if (resultPriceByNumber.getBegin().after(newPriceByNumber.getBegin()) && resultPriceByNumber.getEnd().after(newPriceByNumber.getEnd())
                                        && resultPriceByNumber.getBegin().before(newPriceByNumber.getEnd())) {
                                    if (!(resultPriceByNumber.getValue() == newPriceByNumber.getValue())) {
                                        PriceEntity newPrice1 = new PriceEntity(newPriceByNumber);
                                        PriceEntity newPrice2 = new PriceEntity(resultPriceByNumber);
                                        newPrice2.setBegin(newPriceByNumber.getEnd());
                                        resultPricesByNumber.remove(resultPriceByNumber);
                                        resultPricesByNumber.add(newPrice1);
                                        resultPricesByNumber.add(newPrice2);
                                        flagChangedPrice = true;
                                        break;
                                    } else {
                                        resultPriceByNumber.setBegin(newPriceByNumber.getBegin());
                                        flagChangedPrice = true;
                                        break;
                                    }
                                } else if (resultPriceByNumber.getBegin().after(newPriceByNumber.getBegin()) && resultPriceByNumber.getEnd().before(newPriceByNumber.getEnd())) {
                                    resultPricesByNumber.remove(resultPriceByNumber);
                                    resultPricesByNumber.add(newPriceByNumber);
                                    flagChangedPrice = true;
                                    break;
                                } else if (resultPriceByNumber.equals(newPriceByNumber)) {
                                    flagChangedPrice = true;
                                }
                            }
                            if (!flagChangedPrice) {
                                resultPricesByNumber.add(newPriceByNumber);
                            }
                        }
                    }
                    result.addAll(resultPricesByNumber);
                }
            }
        }
        return result;
    }

    private List<String> getProductsList(List<PriceEntity> oldPrices, List<PriceEntity> newPrices) {
        List<String> result = new ArrayList<>();
        for (PriceEntity oldPrice : oldPrices) {
            if (!result.contains(oldPrice.getProductCode())) {
                result.add(oldPrice.getProductCode());
            }
        }
        for (PriceEntity newPrice : newPrices) {
            if (!result.contains(newPrice.getProductCode())) {
                result.add(newPrice.getProductCode());
            }
        }
        return result;
    }

    private List<Integer> getDepartsList(List<PriceEntity> oldPrices, List<PriceEntity> newPrices) {
        List<Integer> result = new ArrayList<>();
        for (PriceEntity oldPrice : oldPrices) {
            if (!result.contains(oldPrice.getDepart())) {
                result.add(oldPrice.getDepart());
            }
        }
        for (PriceEntity newPrice : newPrices) {
            if (!result.contains(newPrice.getDepart())) {
                result.add(newPrice.getDepart());
            }
        }
        return result;
    }

    private List<PriceEntity> getPriceByProduct(String productCode, List<PriceEntity> prices) {
        List<PriceEntity> result = new ArrayList<>();
        for (PriceEntity price : prices) {
            if (productCode.equals(price.getProductCode())) {
                result.add(price);
            }
        }
        return result;
    }

    private List<PriceEntity> getPriceByNumber(int number, List<PriceEntity> prices) {
        List<PriceEntity> result = new ArrayList<>();
        for (PriceEntity price : prices) {
            if (number == price.getNumber()) {
                result.add(price);
            }
        }
        return result;
    }


    private List<PriceEntity> getPriceByDepart(int depart, List<PriceEntity> prices) {
        List<PriceEntity> result = new ArrayList<>();
        for (PriceEntity price : prices) {
            if (depart == price.getDepart()) {
                result.add(price);
            }
        }
        return result;
    }

    /*private List<PriceEntity> checkForEmpty(List<PriceEntity> newPrices, List<PriceEntity> oldPrices) {
        if (newPrices.isEmpty() && !oldPrices.isEmpty()) {
            return oldPrices;
        } else if (!newPrices.isEmpty() && oldPrices.isEmpty()) {
            return newPrices;
        } else if (newPrices.isEmpty() && oldPrices.isEmpty()) {
            return null;
        }
        return new ArrayList<>();
    }*/
}
