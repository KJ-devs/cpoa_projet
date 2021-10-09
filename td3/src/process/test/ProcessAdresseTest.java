<<<<<<< HEAD
package process.test;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import metier.Adresse;
import process.ProcessAdresse;

public class ProcessAdresseTest {
	private ProcessAdresse process;
	
	@Before
	public void setUp() {
		this.process = new ProcessAdresse();
	}
	//Pays
	 @Test
 	public void testNormalizePaysCasSimple() {
		Adresse adresse = new Adresse();
		adresse.setPays("letzebuerg");
		assertEquals("Luxembourg",process.normalize(adresse).getPays());
	} 
	 @Test
     public void testNormalizePaysCasSimple2() {
        Adresse adresse = new Adresse();
        adresse.setPays("belgium");
        assertEquals("Belgique",process.normalize(adresse).getPays());
    } 
    @Test
     public void testNormalizePaysCasSimple3() {
        Adresse adresse = new Adresse();
        adresse.setPays("switzerland");
        assertEquals("Suisse",process.normalize(adresse).getPays());
    } 
    @Test
     public void testNormalizePaysCasSimple4() {
        Adresse adresse = new Adresse();
        adresse.setPays("Schweiz");
        assertEquals("Suisse",process.normalize(adresse).getPays());
    }
	// VILLE
	public void testNormalizeVilleCasSimple() {
		Adresse adresse = new Adresse();
		adresse.setVille(" St Moulin les metz ");
		assertEquals("Sainte-Moulin-les-metz",process.normalize(adresse).getVille()); // Fonctionne 
	} 
	@Test
	public void testNormalizeVilleCasSimple2() {
		Adresse adresse = new Adresse();
		adresse.setVille(" Ste Moulin les metz");
		assertEquals("Sainte-Moulin-les-metz",process.normalize(adresse).getVille());
	}
	// codePostal
	@Test
    public void testNormalizeCodePostalCasSimple() {
        Adresse adresse = new Adresse();
        adresse.setCodePostal("L-520");
        assertEquals("00520",process.normalize(adresse).getCodePostal()); //fonctionne pour les 0 mais pas L-
    } 
	 public void testNormalizeCodePostalCasSimple2() {
	        Adresse adresse = new Adresse();
	        adresse.setCodePostal("l-520");
	        assertEquals("00520",process.normalize(adresse).getCodePostal()); //fonctionne pour les 0 mais pas L-
	    } 
     @Test
    public void testNormalizeCodePostalCasSimple3() {
        Adresse adresse = new Adresse();
        adresse.setCodePostal("8200");
        assertEquals("08200",process.normalize(adresse).getCodePostal()); //fonctionne pour les 0 mais pas L-
    }
	// numero Voie
	@Test
	public void testNormalizeNumVoieCasSimple() {
		Adresse adresse = new Adresse();
		adresse.setNumVoie("3");
		assertEquals("3,",process.normalize(adresse).getNumVoie()); // Fonctionne 
	} 
	// Voie
	@Test
    public void testNormalizeVoieCasSimple() {
        Adresse adresse = new Adresse();
        adresse.setVoie("boul des bogoss");
        assertEquals("boulevard des bogoss",process.normalize(adresse).getVoie());
    }
 @Test
    public void testNormalizeVoieCasSimple1() {
        Adresse adresse = new Adresse();
        adresse.setVoie("boul. des bogoss");
        assertEquals("boulevard des bogoss",process.normalize(adresse).getVoie());
    }
 @Test
    public void testNormalizeVoieCasSimple2() {
        Adresse adresse = new Adresse();
        adresse.setVoie("bd des bogoss");
        assertEquals("boulevard des bogoss",process.normalize(adresse).getVoie());
    }
 @Test
    public void testNormalizeVoieCasSimple3() {
        Adresse adresse = new Adresse();
        adresse.setVoie("av. des bogoss");
        assertEquals("avenue des bogoss",process.normalize(adresse).getVoie());
    }
 @Test
    public void testNormalizeVoieCasSimple4() {
        Adresse adresse = new Adresse();
        adresse.setVoie("faub. des bogoss");
        assertEquals("faubourg des bogoss",process.normalize(adresse).getVoie());
    }
 @Test
    public void testNormalizeVoieCasSimple5() {
        Adresse adresse = new Adresse();
        adresse.setVoie("fg des bogoss");
        assertEquals("faubourg des bogoss",process.normalize(adresse).getVoie());
    }
 @Test
    public void testNormalizeVoieCasSimple6() {
        Adresse adresse = new Adresse();
        adresse.setVoie("pl. des bogoss");
        assertEquals("place des bogoss",process.normalize(adresse).getVoie());
    }
=======
package process.test;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import metier.Adresse;
import process.ProcessAdresse;

public class ProcessAdresseTest {
	private ProcessAdresse process;
	
	@Before
	public void setUp() {
		this.process = new ProcessAdresse();
	}
	//Pays
	 @Test
 	public void testNormalizePaysCasSimple() {
		Adresse adresse = new Adresse();
		adresse.setPays("letzebuerg");
		assertEquals("Luxembourg",process.normalize(adresse).getPays());
	} 
	 @Test
     public void testNormalizePaysCasSimple2() {
        Adresse adresse = new Adresse();
        adresse.setPays("belgium");
        assertEquals("Belgique",process.normalize(adresse).getPays());
    } 
    @Test
     public void testNormalizePaysCasSimple3() {
        Adresse adresse = new Adresse();
        adresse.setPays("switzerland");
        assertEquals("Suisse",process.normalize(adresse).getPays());
    } 
    @Test
     public void testNormalizePaysCasSimple4() {
        Adresse adresse = new Adresse();
        adresse.setPays("Schweiz");
        assertEquals("Suisse",process.normalize(adresse).getPays());
    }
	// VILLE
	public void testNormalizeVilleCasSimple() {
		Adresse adresse = new Adresse();
		adresse.setVille(" St Moulin les metz ");
		assertEquals("Sainte-Moulin-les-metz",process.normalize(adresse).getVille()); // Fonctionne 
	} 
	@Test
	public void testNormalizeVilleCasSimple2() {
		Adresse adresse = new Adresse();
		adresse.setVille(" Ste Moulin les metz");
		assertEquals("Sainte-Moulin-les-metz",process.normalize(adresse).getVille());
	}
	// codePostal
	@Test
    public void testNormalizeCodePostalCasSimple() {
        Adresse adresse = new Adresse();
        adresse.setCodePostal("L-520");
        assertEquals("00520",process.normalize(adresse).getCodePostal()); //fonctionne pour les 0 mais pas L-
    } 
	 public void testNormalizeCodePostalCasSimple2() {
	        Adresse adresse = new Adresse();
	        adresse.setCodePostal("l-520");
	        assertEquals("00520",process.normalize(adresse).getCodePostal()); //fonctionne pour les 0 mais pas L-
	    } 
     @Test
    public void testNormalizeCodePostalCasSimple3() {
        Adresse adresse = new Adresse();
        adresse.setCodePostal("8200");
        assertEquals("08200",process.normalize(adresse).getCodePostal()); //fonctionne pour les 0 mais pas L-
    }
	// numero Voie
	@Test
	public void testNormalizeNumVoieCasSimple() {
		Adresse adresse = new Adresse();
		adresse.setNumVoie("3");
		assertEquals("3,",process.normalize(adresse).getNumVoie()); // Fonctionne 
	} 
	// Voie
	@Test
    public void testNormalizeVoieCasSimple() {
        Adresse adresse = new Adresse();
        adresse.setVoie("boul des bogoss");
        assertEquals("boulevard des bogoss",process.normalize(adresse).getVoie());
    }
 @Test
    public void testNormalizeVoieCasSimple1() {
        Adresse adresse = new Adresse();
        adresse.setVoie("boul. des bogoss");
        assertEquals("boulevard des bogoss",process.normalize(adresse).getVoie());
    }
 @Test
    public void testNormalizeVoieCasSimple2() {
        Adresse adresse = new Adresse();
        adresse.setVoie("bd des bogoss");
        assertEquals("boulevard des bogoss",process.normalize(adresse).getVoie());
    }
 @Test
    public void testNormalizeVoieCasSimple3() {
        Adresse adresse = new Adresse();
        adresse.setVoie("av. des bogoss");
        assertEquals("avenue des bogoss",process.normalize(adresse).getVoie());
    }
 @Test
    public void testNormalizeVoieCasSimple4() {
        Adresse adresse = new Adresse();
        adresse.setVoie("faub. des bogoss");
        assertEquals("faubourg des bogoss",process.normalize(adresse).getVoie());
    }
 @Test
    public void testNormalizeVoieCasSimple5() {
        Adresse adresse = new Adresse();
        adresse.setVoie("fg des bogoss");
        assertEquals("faubourg des bogoss",process.normalize(adresse).getVoie());
    }
 @Test
    public void testNormalizeVoieCasSimple6() {
        Adresse adresse = new Adresse();
        adresse.setVoie("pl. des bogoss");
        assertEquals("place des bogoss",process.normalize(adresse).getVoie());
    }
>>>>>>> 9d9e20b16feab847078d39820c6ac69bd832e952
}