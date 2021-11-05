package model.metier;

public class Client {
    private int id;
    private String nom;
    private String prenom;
    private String no_rue;
    private String voie;
    private String code_postal;
    private String ville;
    private String pays;
	private String adresse;
    
    public Client(String nom, String prenom, String no_rue, String voie, String code_postal, String ville,String pays,int id) {
   	 super();
   	 
   	 this.nom = nom;
   	 this.prenom = prenom;
   	 this.no_rue = no_rue;
   	 this.voie = voie;
   	 this.code_postal = code_postal;
   	 this.ville = ville;
   	 this.pays = pays;
   	 this.id = id;
    }




	public int getId() {
   	 return id;
    }

    public void setId(int id) {
   	 this.id = id;
    }

    public String getNom() {
   	 return nom;
    }

    public void setNom(String nom) {
   	 this.nom = nom;
    }

    public String getPrenom() {
   	 return prenom;
    }

    public void setPrenom(String prenom) {
   	 this.prenom = prenom;
    }

    public String getNo_rue() {
   	 return no_rue;
    }

    public void setNo_rue(String no_rue) {
   	 this.no_rue = no_rue;
    }

    public String getVoie() {
   	 return voie;
    }

    public void setVoie(String voie) {
   	 this.voie = voie;
    }

    public String getCode_postal() {
   	 return code_postal;
    }

    public void setCode_postal(String code_postal) {
   	 this.code_postal = code_postal;
    }

    public String getVille() {
   	 return ville;
    }

    public void setVille(String ville) {
   	 this.ville = ville;
    }

    public String getPays() {
   	 return pays;
    }

    public void setPays(String pays) {
   	 this.pays = pays;
    }

	public String getAdresse() {
			return adresse = no_rue + ' ' + voie + ' '+ville+' '+code_postal +' '+pays;

	}

    @Override
    public int hashCode() {
   	 final int prime = 31;
   	 int result = 1;
   	 result = prime * result + ((code_postal == null) ? 0 : code_postal.hashCode());
   	 result = prime * result + id;
   	 result = prime * result + ((no_rue == null) ? 0 : no_rue.hashCode());
   	 result = prime * result + ((nom == null) ? 0 : nom.hashCode());
   	 result = prime * result + ((pays == null) ? 0 : pays.hashCode());
   	 result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
   	 result = prime * result + ((ville == null) ? 0 : ville.hashCode());
   	 result = prime * result + ((voie == null) ? 0 : voie.hashCode());
   	 return result;
    }

    @Override
    public boolean equals(Object obj) {
   	 if (this == obj)
   		 return true;
   	 if (obj == null)
   		 return false;
   	 if (getClass() != obj.getClass())
   		 return false;
   	 Client other = (Client) obj;
   	 if (code_postal == null) {
   		 if (other.code_postal != null)
   			 return false;
   	 } else if (!code_postal.equals(other.code_postal))
   		 return false;
   	 if (id != other.id)
   		 return false;
   	 if (no_rue == null) {
   		 if (other.no_rue != null)
   			 return false;
   	 } else if (!no_rue.equals(other.no_rue))
   		 return false;
   	 if (nom == null) {
   		 if (other.nom != null)
   			 return false;
   	 } else if (!nom.equals(other.nom))
   		 return false;
   	 if (pays == null) {
   		 if (other.pays != null)
   			 return false;
   	 } else if (!pays.equals(other.pays))
   		 return false;
   	 if (prenom == null) {
   		 if (other.prenom != null)
   			 return false;
   	 } else if (!prenom.equals(other.prenom))
   		 return false;
   	 if (ville == null) {
   		 if (other.ville != null)
   			 return false;
   	 } else if (!ville.equals(other.ville))
   		 return false;
   	 if (voie == null) {
		 return other.voie == null;
   	 } else return voie.equals(other.voie);
	}

    @Override
    public String toString() {
   	 return  id + " " + nom + " " + prenom ;
    }

    
}

