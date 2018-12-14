/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shadowdruggie;

/**
 *
 * @author wbolduc
 */
public class BoundedNumber {
    private int value;
    private int max;
    private int min;

    public BoundedNumber(int min, int max) { //TODO: RAISE EXEPTION IF BAD RANGES
        this.value = 0;
        this.max = max;
        this.min = min;
    }
    
    public BoundedNumber(int value, int min, int max) { //TODO: RAISE EXEPTION IF BAD RANGES
        this.value = value;
        this.max = max;
        this.min = min;
    }

    public void setRange(int min, int max)
    {
        this.min = min;
        this.max = max;
    }
    
    public boolean add(int i)
    {
        if (value + i > max)
            return false;
        value += i;
        return true;
    }
    public boolean sub(int i)
    {
        if(value - i < min)
            return false;
        value -= i;
        return true;
    }
    
    public int getValue() {
        return value;
    }

    public Boolean setValue(int i) {
        if(i < max && i > min)
        {
            value = i;
            return true;
        } 
        return false;
    }
    
    public BoundedNumber clone()
    {
        return new BoundedNumber(value,min,max);
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }
    
    
}
