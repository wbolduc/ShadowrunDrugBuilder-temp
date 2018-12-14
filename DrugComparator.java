/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shadowdruggie;

import java.util.Comparator;

/**
 *
 * @author wbolduc
 */
public class DrugComparator implements Comparator{
    private byte[] attr;        //attributes to sort on - in order
    private boolean[] order;    //asc or desc     

    public DrugComparator(AttrEnum[] attr, boolean[] order) {
        this.attr = new byte[attr.length];
        for(int i = 0; i < attr.length; i++)
            this.attr[i] = (byte)attr[i].Value;
        this.order = order;
    }
    
    

    
    
    
    @Override
    public int compare(Object o1, Object o2) {
        Drug d1 = (Drug)o1;
        Drug d2 = (Drug)o2;
        int a,b;
        for(int i = 0; i < attr.length; i++)
        {
            a = d1.mod[attr[i]].getValue();
            b = d2.mod[attr[i]].getValue();
            
            if(a != b)
                if (a < b)
                    if(order[i])
                        return 1;   //asc
                    else
                        return -1;  //desc
                else
                    if(order[i])
                        return -1;  //asc
                    else
                        return 1;   //desc
        }           
        return 0; //same
    }
    
}
