package structureDeDonnees;

/**
 * La classe Musique est une classe pour structurer les donnees. Dans les JTables de bibliotheque et LectureDeFichier, on lui passe
 * un tableau d'object dont la ligne est une instance de la classe Musique. Cette ligne est composee des classes TitleClass, AlbumClass , GenreClass ....
 *  
 * @author Benjamin Ruytoor et Aurore Allart
 * @version 9 mai 2013
 */
public class Musique{
	/*
	 * Cette strategie est discutable mais plustard si le programme doit evoluer il est logique d'avoir un decoupage de la sorte.
	 * Mais il est vrai que d'utiliser une classe pour stocker une information c'est beaucoup
	 */
	
	private TitleClass title;
	private AlbumClass album;
	private ArtistClass artist;
	private GenreClass genre;
	private YearClass year;
	private DurationClass duration;
	
	public Musique(String title, String album, String artist, String genre, String year, String duration){
		this.title=new TitleClass(title, this);
		this.album=new AlbumClass(album, this);
		this.artist=new ArtistClass(artist, this);
		this.genre=new GenreClass(genre, this);
		this.year=new YearClass(year, this);
		this.duration=new DurationClass(duration, this);
	}
	
	/**
	 * retourne le titre de la musique
	 * @return le titre de la musique
	 */
	public TitleClass getTitle() {
		return title;
	}

	/**
	 * retourne l'album de la musique 
	 * @return l'album
	 */
	public AlbumClass getAlbum() {
		return album;
	}

	/**
	 * retourne l'artiste de la musique
	 * @return l'artiste
	 */
	public ArtistClass getArtist() {
		return artist;
	}

	/**
	 * retourne le genre de la musique
	 * @return le genre
	 */
	public GenreClass getGenre() {
		return genre;
	}

	/**
	 * retourne l'annee de sortie de la musique
	 * @return l'annee de sortie
	 */
	public YearClass getYear() {
		return year;
	}

	/**
	 * retourne la duree du morceau de musique
	 * @return la duree du morceau
	 */
	public DurationClass getDuration() {
		return duration;
	}
}
