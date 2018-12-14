/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shadowdruggie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 *
 * @author wbolduc
 */
public class DrugPart{
    public final String name;
    public final byte level;
    
    private byte[] attr;    //effects in no particular order
    private byte[] mod;     
    
    private ArrayList<String> qualities;
    
    private Set<DrugPart> conflicts = new HashSet();
    
    
    DrugPart(String name){
        this.name = name;
        level = 0;
    }
    DrugPart(String name, int level)
    {
        this.name = name + " " + level;
        this.level = (byte) level;
    }
    
    Drug applyTo(Drug d)
    {
        if(checkConflicts(d))
            return null;
        
        
        d = d.clone();
        
        for(int i = attr.length - 1; i >= 0; i--)
            if(!d.mod[attr[i]].add(mod[i]))  //out of range
                return null;
        if(qualities != null)
            d.qualities.addAll(qualities);
        
        //add itself to the ingredient list
        d.parts.add(this);
        return d;
    }
    
    void addEffect(AttrEnum attribute, byte effect)
    {
        //check if attribute arrays are null, if so create them
        if(attr == null)
        {
            attr = new byte[1];
            mod = new byte[1];
            attr[0] = (byte) attribute.Value;
            mod[0] = effect;
            return;
        }
        
        //check if the attribute is already being modified
        for(int i = 0; i < attr.length; i++)
            if(attr[i] == attribute.Value)
            {
                mod[i] = effect;
                return;
            }
        
        //add the effect
        byte[] newAttr = new byte[attr.length+1];
        byte[] newMod = new byte[attr.length+1];
        
        newAttr[0] = (byte) attribute.Value;
        newMod[0] = effect;
        for(int i = 0; i < attr.length; i++)
        {
            newAttr[i+1] = attr[i];
            newMod[i+1] = mod[i];
        }
        attr = newAttr;
        mod = newMod;
    }
    
    void addEffect(String effect)
    {
        if(qualities == null)
            qualities = new ArrayList();
        qualities.add(effect);
    }    
    
        
    public boolean conflictsWith(DrugPart d)
    {
        return conflicts.contains(d);
    }
    public void addConflict(DrugPart d)
    {
        d.conflicts.add(this);
        this.conflicts.add(d);
    }
    public List<DrugPart> getConflicts()
    {
        return new ArrayList(conflicts);
    }
    public boolean checkConflicts(Drug drug)  //false if no conflicts
    {
        return !Collections.disjoint(conflicts, drug.parts);
    }
    public void resetConflicts()
    {
        conflicts.clear();
    }
    
    public boolean counters(DrugPart d) // this drug posively effects an attribute drug d negates
    {
        for (int i = 0; i < d.mod.length; i++) //iterate through modifications
        {
            if(d.mod[i] < 0) //is this mod is negative then target drug negatively modifies something
            {
                int attributeBeingModified = d.attr[i]; //save whatever attribute is being negatively modified in the other drug
                
                for(int j = 0; j < this.attr.length; j++) //iterate through this drug trying to find if it also modifies the found attribute
                {
                    if(this.attr[j] == attributeBeingModified && this.mod[j] > 0) //if found check if this drug modifies it positively
                        return true;
                }
            }
        }
        return false;       
    }
    
    public void printStats()
    {
        System.out.println("//////////////////////////////////////////////////////");
        System.out.println(name);
        
        for(int i = 0; i < mod.length; i++)
            System.out.println(String.format("%15s: %2d", AttrEnum.values()[attr[i]].Name, mod[i]));
        
        System.out.print("Qualities: ");
        if(qualities != null && !qualities.isEmpty())
        {
            int i;
            for(i = 0; i < qualities.size() - 1; i++)
            {
                System.out.print(qualities.get(i) + ", ");
            }
            System.out.print(qualities.get(i));
        }
        System.out.println("\n//////////////////////////////////////////////////////");
    }
}
