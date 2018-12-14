/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shadowdruggie;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 *
 * @author wbolduc
 */
public class Incompatibilities {
    private final int AttributeCount = 8;
    private int max = 4;
    private List<DrugPart> foundations;
    private List<DrugPart> blocks;

    public Incompatibilities(List<DrugPart> foundations, List<DrugPart> blocks) {
        this.foundations = foundations;
        this.blocks = blocks;
    }

    Incompatibilities(DrugPart[] foundations, DrugPart[][] blocks) {
        this.foundations = Arrays.asList(foundations);
        
        //blocks should be a jagged array, flatten it
        this.blocks = new ArrayList();
        for(int i = 0; i < blocks.length; i++)
            this.blocks.addAll(Arrays.asList(blocks[i]));
    }
    
    public void generate(Drug template) //update incompatibilities
    {
        //wipe old incompatibilities
        foundations.forEach(f -> f.resetConflicts());
        blocks.forEach(b -> b.resetConflicts());
        
        //foundation block incompatibilities
        for(DrugPart f : foundations)
            for(DrugPart b : blocks)
                if(checkFoundationConflicts(f,b) || checkRangeConflict(f,b, template))
                    f.addConflict(b);
        
        //interBlock incompatibilities
        for(int i = 0; i < blocks.size(); i++)
           for(int j = i + 1; j < blocks.size(); j++)
           {
               DrugPart a = blocks.get(i);
               DrugPart b = blocks.get(j);
               
               if(checkConflictingModification(a,b) || checkRangeConflict(a,b, template))
                   a.addConflict(b); //does both
           }
    }
    
    private boolean checkConflictingModification(DrugPart a, DrugPart b)
    {
        return b.counters(a) || a.counters(b);
    }
    
    private boolean checkRangeConflict(DrugPart a, DrugPart b, Drug template)
    {
        //The total allowable bonus through all sources to any one Attribute is +4.
        //in this function we acheive this by providing a template, we assume the template has the limits allowed by the above rule. The benefit of this is we can reuse this to applyTo limits to drugs where the user doesn't want body to be touched for example
        
        //apply both parts to the template and see if it breaks
        Drug temp;
        return(
                (temp = b.applyTo(template.clone())) == null
                ||
                a.applyTo(temp) == null
              );
    }
    
    private boolean checkFoundationConflicts(DrugPart f, DrugPart b)
    {
        //The maximum level of a block that positively modifies an Attribute that the chosen foundation negatively modifies is Level 2.
        
        //if a block counters a foundation it's cool only if it's not level 3
        
        return b.counters(f) && b.level == 3;  
    }
    
    public void printConflicts()
    {
        List<DrugPart> parts = new ArrayList();
        parts.addAll(foundations);
        parts.addAll(blocks);
        
        for(DrugPart part : parts)
        {
            System.out.println("-------------------------\n" + part.name);
            for(DrugPart conflict : part.getConflicts())
                System.out.println("\t" + conflict.name);
        }
    }
}
