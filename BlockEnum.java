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
public enum BlockEnum {
    CRUSH(0),
    BRUTE(1),
    STRIKE(2),
    LIGHTNING(3),
    EINSTEIN(4),
    GUTCHECK(5),
    STONEWALL(6),
    SMOOTHTALK(7),
    SHOCKANDAWE(8),
    RAZORMIND(9),
    THEGENERAL(10),
    RESIST(11),
    SPEEDDEMON(12);
        
    public final int Value;
    
    private BlockEnum(int value)
    {
        Value = value;
    }
}
