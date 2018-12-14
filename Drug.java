/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shadowdruggie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author wbolduc
 */
public class Drug {
    protected BoundedNumber[] mod;
    protected ArrayList<String> qualities = new ArrayList();
    protected Set<DrugPart> parts = new HashSet();
    
    
    Drug()
    {
        mod = new BoundedNumber[AttrEnum.values().length];
        for(int i = 0; i < AttrEnum.values().length; i++)
            mod[i] = new BoundedNumber(-10,4);   //4 is for maximums, -10 is arbitrary
        
        
        int bigValue = 10000;
        mod[AttrEnum.CRASHSTUN.Value].setRange(0,bigValue);
        mod[AttrEnum.AVAILIBILITY.Value].setRange(0,bigValue);
        mod[AttrEnum.COST.Value].setRange(0,bigValue);
        mod[AttrEnum.ADDICTIONRATING.Value].setRange(0,bigValue);
        mod[AttrEnum.ADDICTIONTHRESHOLD.Value].setRange(0,bigValue);
    }   
    
    Drug(BoundedNumber[] mod)
    {
        this.mod = mod;
    }
    
    
    public Drug clone()
    {
        Drug d = new Drug(mod);
        
        //copy mod arrays
        d.mod = new BoundedNumber[mod.length];
        for(int i = mod.length - 1; i>=0; i--)
            d.mod[i] = this.mod[i].clone();
        
        //copy qualities
        d.qualities = new ArrayList(this.qualities);
        //copy parts
        d.parts = new HashSet(parts);
        
        return d;
    }
    
    public void printValidRanges()
    {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Valid Ranges");
        
        for(int i = 0; i < mod.length; i++)
            System.out.println(String.format("%15s: [%2d, %2d]", AttrEnum.values()[i].Name, mod[i].getMin(), mod[i].getMax()));
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-----------------------------------------\n");
        sb.append("Components: ");
        parts.forEach(part -> sb.append(part.name + ' '));
        sb.append('\n');
        
        for(int i = 0; i < mod.length; i++)
        {
            if(i != AttrEnum.AVAILIBILITY.Value)
               sb.append(String.format("%15s: %2d\n", AttrEnum.values()[i].Name, mod[i].getValue()));
            else
               sb.append(String.format("%15s: %2dR\n", AttrEnum.values()[i].Name, mod[i].getValue())); 
        }
        sb.append("Qualities: ");
        if(!qualities.isEmpty())
        {
            int i;
            for(i = 0; i < qualities.size() - 1; i++)
            {
                sb.append(qualities.get(i));
                sb.append(", ");
            }
            sb.append(qualities.get(i));
        }
        sb.append("\n-----------------------------------------");
        return sb.toString();
    }
    
    public int getEffect(AttrEnum a)
    {
        return mod[a.Value].getValue();
    }
    
}
