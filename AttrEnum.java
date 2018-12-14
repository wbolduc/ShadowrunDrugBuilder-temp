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
public enum AttrEnum {
    BODY(0,"Body"),
    AGILITY(1, "Agility"),
    REACTION(2, "Reaction"),
    STRENGTH(3, "Strength"),
    WILL(4, "Will"),
    LOGIC(5, "Logic"),
    INTUITION(6, "Intuition"),
    CHARISMA(7, "Charisma"),
    D6(8, "Initiative Dice"),
    MENTALLIMIT(9, "Mental Limit"),
    PHYSICALLIMIT(10, "Physical Limit"),
    SOCIALLIMIT(11, "Social Limit"),
    CRASHSTUN(12, "Crash Stun"),
    
    AVAILIBILITY(13, "Availability"),
    COST(14, "Cost"),
    ADDICTIONRATING(15, "Addiction Rating"),
    ADDICTIONTHRESHOLD(16, "Addiction Threshold");
    
    public final int Value;
    public final String Name;
    
    private AttrEnum(int value, String name)
    {
        Value = value;
        Name = name;
    }
}
