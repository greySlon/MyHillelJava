package com.slon.p2;

import java.math.BigDecimal;

/**
 * Created by Sergii on 02.02.2017.
 */
public class Deposit {
    private int deposit = 0;
    private double rate;
    private int period;

    public void setDeposit(int deposit) throws DepositException{
        if(deposit>0) {
            this.deposit = deposit;
        }else{
            throw new DepositException("Deposit should be more than 0");
        }
    }

    public void setRate(double rate) throws RateException{
        if(rate>=0){
        this.rate = rate / 100;
        }else{
            throw new RateException("Rate shouldn't be negativ. We are not in Europe");
        }

    }

    public void setPeriod(int period) throws PeriodException{
        if(period>0) {
            this.period = period;
        }else{
            throw new PeriodException("Period should to be more than 0 year");
        }
    }

    private void report() {
        double tmpDeposit = deposit;

        for (int i = 0; i < period; ) {
            double rise=tmpDeposit * rate;
            tmpDeposit += tmpDeposit * rate;
            System.out.printf("%d year(s) - saved: %.2f , profit %.2f", ++i, tmpDeposit, rise);
            System.out.println();
        }
    }

    public static void main(String[]a){
        Deposit deposit=new Deposit();
        try {
            deposit.setDeposit(1000);
            deposit.setRate(17);
            deposit.setPeriod(10);
            deposit.report();
        } catch (BankException e) {
            System.out.println(e.getMessage());
        }
    }
}
class BankException extends Exception{
    public BankException(String message) {
        super(message);
    }
}
class DepositException extends BankException{
    public DepositException(String message) {
        super(message);
    }
}
class RateException extends BankException{
    public RateException(String message) {
        super(message);
    }
}
class PeriodException extends BankException{
    public PeriodException(String message) {
        super(message);
    }
}