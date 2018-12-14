/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shadowdruggie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author wbolduc
 */
public class ShadowDruggie {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DrugPart[][] blocks = getBlockArray();
        DrugPart[] foundations = getFoundationArray();
        
        printDrugParts(foundations);
        printBlockArrayStats(blocks);
        
        
        Incompatibilities ic = new Incompatibilities(foundations,blocks);
        
        Drug template = new Drug();
        template.printValidRanges();
        
        ic.generate(template);
        
        Stream<Drug> drugs = Arrays.asList(foundations).stream().map(f -> f.applyTo(template));

        drugs = drugs.flatMap(d -> applyAllBlocks(d,blocks[0]));
        drugs = drugs.flatMap(d -> applyAllBlocks(d,blocks[1]));
        drugs = drugs.flatMap(d -> applyAllBlocks(d,blocks[2]));
        drugs = drugs.flatMap(d -> applyAllBlocks(d,blocks[3]));
        drugs = drugs.flatMap(d -> applyAllBlocks(d,blocks[4]));
        drugs = drugs.flatMap(d -> applyAllBlocks(d,blocks[5]));
        drugs = drugs.flatMap(d -> applyAllBlocks(d,blocks[6]));
        drugs = drugs.flatMap(d -> applyAllBlocks(d,blocks[7]));
        drugs = drugs.flatMap(d -> applyAllBlocks(d,blocks[8]));
        drugs = drugs.flatMap(d -> applyAllBlocks(d,blocks[9]));
        drugs = drugs.flatMap(d -> applyAllBlocks(d,blocks[10]));
        drugs = drugs.flatMap(d -> applyAllBlocks(d,blocks[11]));
        drugs = drugs.flatMap(d -> applyAllBlocks(d,blocks[12]));
        
        DrugComparator dc = new DrugComparator( new AttrEnum[]{AttrEnum.AVAILIBILITY},
                                                new boolean[]{true});
        drugs = drugs.sorted(dc);
        System.out.println(drugs.count());
        System.exit(0);
        drugs = drugs.limit(1);
        drugs.forEach(System.out::println);
    }
    
    public static Stream<Drug> applyAllBlocks(Drug d, DrugPart[] block)
    {
        ArrayList<Drug> drugs = new ArrayList();
        drugs.add(d);   //don't applyTo this block
        Drug newDrug;
        for(byte i = (byte) (block.length - 1); i >= 0; i--)
            if((newDrug = block[i].applyTo(d)) != null)
                drugs.add(newDrug);
        return drugs.stream();
    }
    
    public static DrugPart[][] getBlockArray()
    { 
        //crush
        DrugPart crush1 = new DrugPart("Crush", 1);
        crush1.addEffect(AttrEnum.STRENGTH, (byte)1);
        crush1.addEffect(AttrEnum.INTUITION, (byte)-1);
        
        DrugPart crush2 = new DrugPart("Crush", 2);
        crush2.addEffect(AttrEnum.STRENGTH, (byte)2);
        crush2.addEffect(AttrEnum.INTUITION, (byte)-1);
        crush2.addEffect(AttrEnum.CRASHSTUN, (byte)2);
        
        DrugPart crush3 = new DrugPart("Crush", 3);
        crush3.addEffect(AttrEnum.STRENGTH, (byte)3);
        crush3.addEffect(AttrEnum.INTUITION, (byte)-1);
        crush3.addEffect(AttrEnum.CRASHSTUN, (byte)2);
        crush3.addEffect("Low Pain Tolerance quality (p. 82, SR5)");
        
        DrugPart[] crushArray = new DrugPart[3];
        crushArray[0] = crush1;
        crushArray[1] = crush2;
        crushArray[2] = crush3;
        
        //brute
        DrugPart brute1 = new DrugPart("Brute", 1);
        brute1.addEffect(AttrEnum.BODY, (byte)1);
        brute1.addEffect(AttrEnum.LOGIC, (byte)-1);
        
        DrugPart brute2 = new DrugPart("Brute", 2);
        brute2.addEffect(AttrEnum.BODY, (byte)2);
        brute2.addEffect(AttrEnum.LOGIC, (byte)-1);
        brute2.addEffect(AttrEnum.CRASHSTUN, (byte)2);
        
        DrugPart brute3 = new DrugPart("Brute", 3);
        brute3.addEffect(AttrEnum.BODY, (byte)3);
        brute3.addEffect(AttrEnum.LOGIC, (byte)-1);
        brute3.addEffect(AttrEnum.INTUITION, (byte)-1);
        brute3.addEffect(AttrEnum.CRASHSTUN, (byte)2);

        DrugPart[] bruteArray = new DrugPart[3];
        bruteArray[0] = brute1;
        bruteArray[1] = brute2;
        bruteArray[2] = brute3;        
        
        //strike
        DrugPart strike1 = new DrugPart("Strike", 1);
        strike1.addEffect(AttrEnum.AGILITY, (byte)1);
        strike1.addEffect(AttrEnum.STRENGTH, (byte)-1);
        
        DrugPart strike2 = new DrugPart("Strike", 2);
        strike2.addEffect(AttrEnum.AGILITY, (byte)2);
        strike2.addEffect(AttrEnum.STRENGTH, (byte)-1);
        strike2.addEffect(AttrEnum.CRASHSTUN, (byte)2);
        
        DrugPart strike3 = new DrugPart("Strike", 3);
        strike3.addEffect(AttrEnum.AGILITY, (byte)3);
        strike3.addEffect(AttrEnum.STRENGTH, (byte)-1);
        strike3.addEffect(AttrEnum.CRASHSTUN, (byte)2);
        strike3.addEffect("Unsteady Hands quality (p. 87, SR5)");

        DrugPart[] strikeArray = new DrugPart[3];
        strikeArray[0] = strike1;
        strikeArray[1] = strike2;
        strikeArray[2] = strike3;   
        
        
        //lightning
        DrugPart lightning1 = new DrugPart("Lightning", 1);
        lightning1.addEffect(AttrEnum.REACTION, (byte)1);
        lightning1.addEffect(AttrEnum.LOGIC, (byte)-1);
        
        DrugPart lightning2 = new DrugPart("Lightning", 2);
        lightning2.addEffect(AttrEnum.REACTION, (byte)2);
        lightning2.addEffect(AttrEnum.LOGIC, (byte)-1);
        lightning2.addEffect(AttrEnum.WILL, (byte)-1);
        
        DrugPart lightning3 = new DrugPart("Lightning", 3);
        lightning3.addEffect(AttrEnum.REACTION, (byte)3);
        lightning3.addEffect(AttrEnum.LOGIC, (byte)-1);
        lightning3.addEffect(AttrEnum.WILL, (byte)-1);
        lightning3.addEffect(AttrEnum.CRASHSTUN, (byte)2);

        DrugPart[] lightningArray = new DrugPart[3];
        lightningArray[0] = lightning1;
        lightningArray[1] = lightning2;
        lightningArray[2] = lightning3; 
        
        //einstein
        DrugPart einstein1 = new DrugPart("Einstein", 1);
        einstein1.addEffect(AttrEnum.LOGIC, (byte)1);
        einstein1.addEffect(AttrEnum.WILL, (byte)-1);
        
        DrugPart einstein2 = new DrugPart("Einstein", 2);
        einstein2.addEffect(AttrEnum.LOGIC, (byte)2);
        einstein2.addEffect(AttrEnum.WILL, (byte)-1);
        einstein2.addEffect(AttrEnum.INTUITION, (byte)-1);
        
        DrugPart einstein3 = new DrugPart("Einstein", 3);
        einstein3.addEffect(AttrEnum.LOGIC, (byte)3);
        einstein3.addEffect(AttrEnum.WILL, (byte)-1);
        einstein3.addEffect(AttrEnum.INTUITION, (byte)-1);
        einstein3.addEffect("crash effect: –1D6 Initiative Dice");

        DrugPart[] einsteinArray = new DrugPart[3];
        einsteinArray[0] = einstein1;
        einsteinArray[1] = einstein2;
        einsteinArray[2] = einstein3; 
        
        //gutCheck
        DrugPart gutCheck1 = new DrugPart("GutCheck", 1);
        gutCheck1.addEffect(AttrEnum.INTUITION, (byte)1);
        gutCheck1.addEffect(AttrEnum.STRENGTH, (byte)-1);
        
        DrugPart gutCheck2 = new DrugPart("GutCheck", 2);
        gutCheck2.addEffect(AttrEnum.INTUITION, (byte)2);
        gutCheck2.addEffect(AttrEnum.STRENGTH, (byte)-1);
        gutCheck2.addEffect(AttrEnum.REACTION, (byte)-1);
        
        DrugPart gutCheck3 = new DrugPart("GutCheck", 3);
        gutCheck3.addEffect(AttrEnum.INTUITION, (byte)3);
        gutCheck3.addEffect(AttrEnum.STRENGTH, (byte)-1);
        gutCheck3.addEffect(AttrEnum.REACTION, (byte)-1);
        gutCheck3.addEffect(AttrEnum.CRASHSTUN, (byte)2);

        DrugPart[] gutCheckArray = new DrugPart[3];
        gutCheckArray[0] = gutCheck1;
        gutCheckArray[1] = gutCheck2;
        gutCheckArray[2] = gutCheck3; 
        
        //stoneWall
        DrugPart stoneWall1 = new DrugPart("StoneWall", 1);
        stoneWall1.addEffect(AttrEnum.WILL, (byte)1);
        stoneWall1.addEffect(AttrEnum.BODY, (byte)-1);
        
        DrugPart stoneWall2 = new DrugPart("StoneWall", 2);
        stoneWall2.addEffect(AttrEnum.WILL, (byte)2);
        stoneWall2.addEffect(AttrEnum.BODY, (byte)-1);
        stoneWall2.addEffect(AttrEnum.AGILITY, (byte)-1);
        
        DrugPart stoneWall3 = new DrugPart("StoneWall", 3);
        stoneWall3.addEffect(AttrEnum.WILL, (byte)3);
        stoneWall3.addEffect(AttrEnum.BODY, (byte)-1);
        stoneWall3.addEffect(AttrEnum.AGILITY, (byte)-1);
        stoneWall3.addEffect(AttrEnum.STRENGTH, (byte)-1);

        DrugPart[] stoneWallArray = new DrugPart[3];
        stoneWallArray[0] = stoneWall1;
        stoneWallArray[1] = stoneWall2;
        stoneWallArray[2] = stoneWall3; 
        
        //smoothTalk
        DrugPart smoothTalk1 = new DrugPart("SmoothTalk", 1);
        smoothTalk1.addEffect(AttrEnum.CHARISMA, (byte)1);
        smoothTalk1.addEffect(AttrEnum.STRENGTH, (byte)-1);
        
        DrugPart smoothTalk2 = new DrugPart("SmoothTalk", 2);
        smoothTalk2.addEffect(AttrEnum.CHARISMA, (byte)2);
        smoothTalk2.addEffect(AttrEnum.STRENGTH, (byte)-1);
        smoothTalk2.addEffect(AttrEnum.CRASHSTUN, (byte)2);
        
        DrugPart smoothTalk3 = new DrugPart("SmoothTalk", 3);
        smoothTalk3.addEffect(AttrEnum.CHARISMA, (byte)3);
        smoothTalk3.addEffect(AttrEnum.STRENGTH, (byte)-1);
        smoothTalk3.addEffect(AttrEnum.CRASHSTUN, (byte)2);
        smoothTalk3.addEffect("crash effect: Uncouth quality (p. 85, SR5)");

        DrugPart[] smoothTalkArray = new DrugPart[3];
        smoothTalkArray[0] = smoothTalk1;
        smoothTalkArray[1] = smoothTalk2;
        smoothTalkArray[2] = smoothTalk3; 
        
        //shockAndAwe
        DrugPart shockAndAwe1 = new DrugPart("ShockAndAwe", 1);
        shockAndAwe1.addEffect(AttrEnum.D6, (byte)1);
        shockAndAwe1.addEffect(AttrEnum.CRASHSTUN, (byte)4);
        
        DrugPart shockAndAwe2 = new DrugPart("ShockAndAwe", 2);
        shockAndAwe2.addEffect(AttrEnum.D6, (byte)2);
        shockAndAwe2.addEffect(AttrEnum.MENTALLIMIT, (byte)-1);
        shockAndAwe2.addEffect(AttrEnum.PHYSICALLIMIT, (byte)-1);
        shockAndAwe2.addEffect(AttrEnum.SOCIALLIMIT, (byte)-1);        
        shockAndAwe2.addEffect(AttrEnum.CRASHSTUN, (byte)4);
        
        DrugPart shockAndAwe3 = new DrugPart("ShockAndAwe", 3);
        shockAndAwe3.addEffect(AttrEnum.D6, (byte)3);
        shockAndAwe3.addEffect(AttrEnum.MENTALLIMIT, (byte)-2);
        shockAndAwe3.addEffect(AttrEnum.PHYSICALLIMIT, (byte)-2);
        shockAndAwe3.addEffect(AttrEnum.SOCIALLIMIT, (byte)-2); 
        shockAndAwe3.addEffect(AttrEnum.CRASHSTUN, (byte)8);

        DrugPart[] shockAndAweArray = new DrugPart[3];
        shockAndAweArray[0] = shockAndAwe1;
        shockAndAweArray[1] = shockAndAwe2;
        shockAndAweArray[2] = shockAndAwe3; 
        
        //razorMind
        DrugPart razorMind1 = new DrugPart("RazorMind", 1);
        razorMind1.addEffect(AttrEnum.INTUITION, (byte)1);
        razorMind1.addEffect(AttrEnum.LOGIC, (byte)1);
        razorMind1.addEffect(AttrEnum.CHARISMA, (byte)-1);
        razorMind1.addEffect(AttrEnum.CRASHSTUN, (byte)1);
        
        DrugPart razorMind2 = new DrugPart("RazorMind", 2);
        razorMind2.addEffect(AttrEnum.INTUITION, (byte)2);
        razorMind2.addEffect(AttrEnum.LOGIC, (byte)2);
        razorMind2.addEffect(AttrEnum.CHARISMA, (byte)-2);
        razorMind2.addEffect(AttrEnum.CRASHSTUN, (byte)2);

        DrugPart[] razorMindArray = new DrugPart[2];
        razorMindArray[0] = razorMind1;
        razorMindArray[1] = razorMind2;
        
        
        //theGeneral
        DrugPart theGeneral1 = new DrugPart("TheGeneral", 1);
        theGeneral1.addEffect(AttrEnum.CHARISMA, (byte)1);
        theGeneral1.addEffect(AttrEnum.WILL, (byte)1);
        theGeneral1.addEffect(AttrEnum.STRENGTH, (byte)-1);
        theGeneral1.addEffect(AttrEnum.CRASHSTUN, (byte)2);
        
        DrugPart theGeneral2 = new DrugPart("TheGeneral", 2);
        theGeneral2.addEffect(AttrEnum.CHARISMA, (byte)2);
        theGeneral2.addEffect(AttrEnum.WILL, (byte)2);
        theGeneral2.addEffect(AttrEnum.STRENGTH, (byte)-1);
        theGeneral2.addEffect(AttrEnum.AGILITY, (byte)-1);
        theGeneral2.addEffect(AttrEnum.CRASHSTUN, (byte)2);

        DrugPart[] theGeneralArray = new DrugPart[2];
        theGeneralArray[0] = theGeneral1;
        theGeneralArray[1] = theGeneral2;
        
        //resist
        DrugPart resist1 = new DrugPart("Resist", 1);
        resist1.addEffect(AttrEnum.BODY, (byte)1);
        resist1.addEffect(AttrEnum.WILL, (byte)1);
        resist1.addEffect(AttrEnum.LOGIC, (byte)-1);
        resist1.addEffect(AttrEnum.CRASHSTUN, (byte)1);
        
        DrugPart resist2 = new DrugPart("Resist", 2);
        resist2.addEffect(AttrEnum.BODY, (byte)2);
        resist2.addEffect(AttrEnum.WILL, (byte)2);
        resist2.addEffect(AttrEnum.LOGIC, (byte)-1);
        resist2.addEffect(AttrEnum.REACTION, (byte)-1);
        resist2.addEffect(AttrEnum.CRASHSTUN, (byte)2);

        DrugPart[] resistArray = new DrugPart[2];
        resistArray[0] = resist1;
        resistArray[1] = resist2;
        
        //speedDemon
        DrugPart speedDemon1 = new DrugPart("SpeedDemon", 1);
        speedDemon1.addEffect(AttrEnum.AGILITY, (byte)1);
        speedDemon1.addEffect(AttrEnum.REACTION, (byte)1);
        speedDemon1.addEffect(AttrEnum.STRENGTH, (byte)-1);
        speedDemon1.addEffect(AttrEnum.CRASHSTUN, (byte)1);
        speedDemon1.addEffect(AttrEnum.CRASHSTUN, (byte)1); //TODO REMOVE THIS TEMP ASS 
        
        DrugPart speedDemon2 = new DrugPart("SpeedDemon", 2);
        speedDemon2.addEffect(AttrEnum.AGILITY, (byte)2);
        speedDemon2.addEffect(AttrEnum.REACTION, (byte)2);
        speedDemon2.addEffect(AttrEnum.STRENGTH, (byte)-1);
        speedDemon2.addEffect(AttrEnum.INTUITION, (byte)-1);
        speedDemon2.addEffect(AttrEnum.CRASHSTUN, (byte)2);

        DrugPart[] speedDemonArray = new DrugPart[2];
        speedDemonArray[0] = speedDemon1;
        speedDemonArray[1] = speedDemon2;
        
        DrugPart[][] blockArray = new DrugPart[13][];
        blockArray[0] = crushArray;
        blockArray[1] = bruteArray;
        blockArray[2] = strikeArray;
        blockArray[3] = lightningArray;
        blockArray[4] = einsteinArray;
        blockArray[5] = gutCheckArray;
        blockArray[6] = stoneWallArray;
        blockArray[7] = smoothTalkArray;
        blockArray[8] = shockAndAweArray;
        blockArray[9] = razorMindArray;
        blockArray[10] = theGeneralArray;
        blockArray[11] = resistArray;
        blockArray[12] = speedDemonArray;
        
        //apply block group attributes
        int i;
        DrugPart[] blockGroup;
        //blocks 1-8 (0,7)
        for(i = 0; i < 8; i++)
        {
            blockGroup = blockArray[i];
            for(int j = 0; j < blockGroup.length; j++)
            {
                DrugPart block = blockGroup[j];
                int level = j + 1;
                block.addEffect(AttrEnum.AVAILIBILITY,      (byte)(1 * level));
                block.addEffect(AttrEnum.COST,              (byte)(20 * level));
            }
        }
        
        //block 9
        blockGroup = blockArray[8];
        for(int j = 0; j < blockGroup.length; j++)
        {
            DrugPart block = blockGroup[j];
            int level = j + 1;
            block.addEffect(AttrEnum.AVAILIBILITY,      (byte)(2 * level));
            block.addEffect(AttrEnum.COST,              (byte)(40 * level));
        }
        
        //blocks 10-13 (9-12)
        for(i = 9; i < 13; i++)
        {
            blockGroup = blockArray[i];
            for(int j = 0; j < blockGroup.length; j++)
            {
                DrugPart block = blockGroup[j];
                int level = j + 1;
                block.addEffect(AttrEnum.AVAILIBILITY,      (byte)(2 * level));
                block.addEffect(AttrEnum.COST,              (byte)(30 * level));
            }
        }
        return blockArray;
    }
    
    public static DrugPart[] getFoundationArray()
    {
        DrugPart tank = new DrugPart("(Tank)");
        tank.addEffect(AttrEnum.BODY,(byte) 2);
        tank.addEffect(AttrEnum.WILL,(byte) 1);
        tank.addEffect(AttrEnum.CHARISMA,(byte) -2);
        tank.addEffect("Pain Resistance 3");
        
        DrugPart defender = new DrugPart("(Defender)");
        defender.addEffect(AttrEnum.AGILITY,(byte) 1);
        defender.addEffect(AttrEnum.REACTION,(byte) 1);
        defender.addEffect(AttrEnum.INTUITION,(byte) 1);
        defender.addEffect(AttrEnum.STRENGTH,(byte) -1);
        defender.addEffect(AttrEnum.LOGIC,(byte) -1);
        
        DrugPart genius = new DrugPart("(Genius)");
        genius.addEffect(AttrEnum.LOGIC,(byte) 2);
        genius.addEffect(AttrEnum.INTUITION,(byte) 2);
        genius.addEffect(AttrEnum.WILL,(byte) -1);
        genius.addEffect(AttrEnum.REACTION,(byte) -1);
        
        DrugPart charmer = new DrugPart("(Charmer)");
        charmer.addEffect(AttrEnum.CHARISMA,(byte) 1);
        charmer.addEffect(AttrEnum.SOCIALLIMIT,(byte) 1);
        charmer.addEffect(AttrEnum.AGILITY,(byte) -1);
        
        DrugPart warrior = new DrugPart("(Warrior)");
        warrior.addEffect(AttrEnum.STRENGTH,(byte) 1);
        warrior.addEffect(AttrEnum.AGILITY,(byte) 1);
        warrior.addEffect(AttrEnum.BODY,(byte) 1);
        warrior.addEffect(AttrEnum.WILL,(byte) -1);
        
        
        DrugPart[] fArray = new DrugPart[5];
        fArray[0] = tank;
        fArray[1] = defender;
        fArray[2] = genius;
        fArray[3] = charmer;
        fArray[4] = warrior;
        
        //all foundations share base drug avail/cost/addictionRating/addictionThreshold
        for(int i = 0; i < fArray.length; i++)
        {
            DrugPart found = fArray[i];
            found.addEffect(AttrEnum.AVAILIBILITY, (byte)4);
            found.addEffect(AttrEnum.COST, (byte)75);
            found.addEffect(AttrEnum.ADDICTIONRATING, (byte)6);
            found.addEffect(AttrEnum.ADDICTIONTHRESHOLD, (byte)2);
        }
           
        return fArray;
    }
    
    public static void printBlockArrayStats(DrugPart[][] blocks)
    {
        for(int i = 0; i < blocks.length; i++)
            printDrugParts(blocks[i]);
    }
    public static void printDrugParts(DrugPart[] parts)
    {
        for(int i = 0; i < parts.length; i++)
            parts[i].printStats();
    }
}
