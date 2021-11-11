
package model.tools;

import model.metier.Client;

public class ProcessAdresse{
	public static Client normalize(Client adresse) {
		// Normalyse pays
		if (adresse.getPays() != null) {
			normalizePays(adresse);
		}
		 if(adresse.getVille() != null) {
			normalizeVille(adresse);
		}
		 if (adresse.getCode_postal() != null) {
			 normalizeCodePostal(adresse);

		 }
		 if (adresse.getVoie() != null) {
			normalizeVoie(adresse);

		}
		 if (adresse.getNo_rue() != null) {
			normalizeNo_rue(adresse);
		}
		return adresse;
	}

	public static void normalizePays(Client adresse) {
		String pays = adresse.getPays().toLowerCase();
		if ("letzebuerg".equalsIgnoreCase(pays)) {
			adresse.setPays("Luxembourg");
		} else if ("belgium".equalsIgnoreCase(pays)) {
			adresse.setPays("Belgique");
		} else if ("switzerland".equalsIgnoreCase(pays) || ("schweiz".equals(pays))) {
			adresse.setPays("Suisse");
		}
	}

	public static void normalizeVille(Client adresse) {
		String ville = adresse.getVille().substring(0, 1).toUpperCase() + adresse.getVille().substring(1);
		ville = ville.trim();
		if (ville.contains(" ")) {
			ville = ville.replace(" ", "-");
			adresse.setVille(ville);
		}
		if (ville.contains("St")) {
			adresse.setVille(ville.replace("St", "Saint"));
			
		} else if (ville.contains("Ste")) {
			adresse.setVille(ville.replace("Ste", "Sainte"));
		}
	}

	public static void normalizeCodePostal(Client adresse) {
		String codePostal = adresse.getCode_postal();

			codePostal = codePostal.replace("L-","");
			 codePostal = codePostal.replace("l-", "");
			
		while (codePostal.length() < 5) {
			codePostal = 0 + codePostal;
		}
		
		adresse.setCode_postal(codePostal);
	}

	public static void normalizeVoie(Client adresse) {
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
	        //Pour chaque mots de la voie verifie si l'un des mots est a changer par son non abbr�viation
	        for (int i = 0; i < nomvoie.length; i++) {
	            for (String[] a : Change) {
	                //V�rifie si l'un des mots de toChangeContent[x][0] correspond a un mot de abc et si oui le remplace par toChangeContent[x][1]
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
	public static void normalizeNo_rue(Client adresse) {
		String no_rue = adresse.getNo_rue();
				adresse.setNo_rue(no_rue + ",");
	}
}
