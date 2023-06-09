package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.Account.*;
import static org.junit.jupiter.api.Assertions.*;


//represents a class for marine animal testing
public class MarineAnimalTest {

    private MarineAnimal belugaWhale;
    private MarineAnimal narWhale;
    private MarineAnimal whaleShark;



    @BeforeEach
    public void setup() {
        narWhale = new MarineAnimal("North Atlantic Right Whale", "CE", "Ocean", 366);
        belugaWhale = new MarineAnimal("Beluga Whale", "NT", "Ocean", 150000);
        whaleShark = new MarineAnimal("Whale Shark", "E", "Ocean", 200000);
    }

    @Test
    public void testMarineAnimalsNARWhale(){
        assertEquals(0, narWhale.getDonation());
        assertEquals("North Atlantic Right Whale", narWhale.getName());
        assertEquals("Marine Animal", narWhale.getSpecies());
        assertEquals("CE", narWhale.getStatus());
        assertEquals(366, narWhale.getPopulation());
        assertEquals("Ocean", narWhale.getHabitat());
    }

    @Test
    public void testMarineAnimalBeluga(){
        assertEquals(0, belugaWhale.getDonation());
        assertEquals(150000, belugaWhale.getPopulation());
        assertEquals("NT", belugaWhale.getStatus());
        assertEquals("Ocean", belugaWhale.getHabitat());
        assertEquals("Beluga Whale", belugaWhale.getName());
        assertEquals("Marine Animal", belugaWhale.getSpecies());
    }

    @Test
    public void testMarineAnimalWhaleShark(){
        assertEquals(0, whaleShark.getDonation());
        assertEquals("Ocean", whaleShark.getHabitat());
        assertEquals("Whale Shark", whaleShark.getName());
        assertEquals("E", whaleShark.getStatus());
        assertEquals(200000, whaleShark.getPopulation());
        assertEquals("Marine Animal", whaleShark.getSpecies());
    }

    @Test
    public void testAddDonation1(){
        narWhale.addDonation(getMinDonation());
        assertEquals(getMinDonation(), narWhale.getDonation());
    }

    @Test
    public void testAddDonationMiddle(){
        narWhale.addDonation(getMaxDonation()/2);
        assertEquals(getMaxDonation()/2, narWhale.getDonation());

    }

    @Test
    public void testAddDonationOneLessThanMax(){
        narWhale.addDonation(getMaxDonation() - 1);
        assertEquals(getMaxDonation() - 1,narWhale.getDonation() );
    }

    @Test
    public void testAddDonationMaximum(){
        narWhale.addDonation(getMaxDonation());
        assertEquals(getMaxDonation(), narWhale.getDonation());
    }

    @Test
    public void testBelugatoJson() {
        JSONObject json = belugaWhale.toJson();
        String name = json.getString("name");
        assertEquals(name, "Beluga Whale");
        String habitat = json.getString("habitat");
        assertEquals(habitat, "Ocean");
        Double population = json.getDouble("population");
        assertEquals(population, 150000);
        Double donations = json.getDouble("donations");
        assertEquals(donations, 0);
        String species = json.getString("species");
        assertEquals(species, "Marine Animal");
        String status = json.getString("status");
        assertEquals(status, "NT");

    }

    @Test
    public void testWhaleSharktoJson() {
        JSONObject json = whaleShark.toJson();
        String name = json.getString("name");
        assertEquals(name, "Whale Shark");
        String habitat = json.getString("habitat");
        assertEquals(habitat, "Ocean");
        Double population = json.getDouble("population");
        assertEquals(population, 200000);
        Double donations = json.getDouble("donations");
        assertEquals(donations, 0);
        String species = json.getString("species");
        assertEquals(species, "Marine Animal");
        String status = json.getString("status");
        assertEquals(status, "E");

    }

    @Test
    public void testNarWhaletoJson() {
        JSONObject json = narWhale.toJson();
        String name = json.getString("name");
        assertEquals(name, "North Atlantic Right Whale");
        String habitat = json.getString("habitat");
        assertEquals(habitat, "Ocean");
        Double population = json.getDouble("population");
        assertEquals(population, 366);
        Double donations = json.getDouble("donations");
        assertEquals(donations, 0);
        String species = json.getString("species");
        assertEquals(species, "Marine Animal");
        String status = json.getString("status");
        assertEquals(status, "CE");

    }

    @Test
    public void testEquals() {
       MarineAnimal testMarine = new MarineAnimal("Whale Shark", "E", "Ocean", 200000);
       assertTrue(whaleShark.equals(testMarine));
       assertEquals(whaleShark.hashCode(), testMarine.hashCode());
    }

    @Test
    public void testNotEquals() {
        MarineAnimal testMarine = new MarineAnimal("Whale Shark", "E", "Ocean", 200000);
        assertFalse(belugaWhale.equals(testMarine));
        assertFalse(belugaWhale.equals("Beluga Whale"));
        assertNotEquals(belugaWhale.hashCode(), testMarine.hashCode());
    }



}
