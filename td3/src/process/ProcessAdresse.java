package process;

import metier.Adresse;

public class ProcessAdresse {
	public Adresse normalize(Adresse adresse) {
		// Normalyse pays
		if (adresse.getPays() != null) {
			normalizePays(adresse);
		}
		else if(adresse.getVille() != null) {
			normalizeVille(adresse);
		} else if (adresse.getCodePostal() != null) {
			normalizeCodePostal(adresse);
			
		}else if (adresse.getVoie() != null) {
			normalizeVoie(adresse);
			
		} else if (adresse.getNumVoie() != null) {
			normalizeNumVoie(adresse);
		}
		return adresse;
	}

	private void normalizePays(Adresse adresse) {
		String pays = adresse.getPays().toLowerCase();
		if ("letzebuerg".equalsIgnoreCase(pays)) {
			adresse.setPays("Luxembourg");
		} else if ("belgium".equalsIgnoreCase(pays)) {
			adresse.setPays("Belgique");
		} else if ("switzerland".equalsIgnoreCase(pays) || ("schweiz".equals(pays))) {
			adresse.setPays("Suisse");
		}
	}

	private void normalizeVille(Adresse adresse) {
		String ville = adresse.getVille().substring(0, 1).toUpperCase() + adresse.getVille().substring(1);
		ville = ville.trim();
		if (ville.contains(" ")) {
			ville = ville.replace(" ", "-");
			adresse.setVille(ville);
		}
		if (ville.contains("St")) {
			adresse.setVille(ville.replace("St", "Saint"));
			
		} else if (ville.contains("Ste")) {
			adresse.setVille(ville.replace("St", "Sainte"));
		}
	}

	private void normalizeCodePostal(Adresse adresse) {
		String codePostal = adresse.getCodePostal();

			codePostal = codePostal.replace("L-","");
			 codePostal = codePostal.replace("l-", "");
			
		while (codePostal.length() < 5) {
			codePostal = 0 + codePostal;
		}
		
		adresse.setCodePostal(codePostal);
	}

	private void normalizeVoie(Adresse adresse) {
		String[] nomvoie = adresse.getVoie().toLowerCase().split(" ");
		String newVoie = "";
		   String[][] Change = {
	                {"boul", "boulevard"},
	                {"bd", "boulevard"},
	                {"av", "avenue"},
	                {"faub", "faubourg"},
	                {"fg", "faubourg"},
	                {"pl.", "place"}
	        };

	        

	        //Pour chaque mots de la voie vérifie si l'un des mots est a changer par son non abbréviation
	        for (int i = 0; i < nomvoie.length; i++) {
	            for (String[] a : Change) {
	                //Vérifie si l'un des mots de toChangeContent[x][0] correspond a un mot de abc et si oui le remplace par toChangeContent[x][1]
	                if (nomvoie[i].equals(a[0]) || (nomvoie[i].equals(a[0] + "."))) {
	                    nomvoie[i] = a[1];
	                    break;
	                }
	            }
	        }

	        for (String voie : nomvoie) {
	           newVoie = newVoie.concat(voie).concat(" ");
	       
	        }
	        newVoie = newVoie.trim();
	        adresse.setVoie(newVoie);
	    }
	private void normalizeNumVoie(Adresse adresse) {
		String numVoie = adresse.getNumVoie();
				adresse.setNumVoie(numVoie + ",");
	}
}
