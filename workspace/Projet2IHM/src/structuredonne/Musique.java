package structuredonne;

public class Musique {
	/*
	 * Musique est une classe pour sturcture les données.
	 * Dans une Jtable on lui passe un tableau d'objet une ligne est une instance de Musique
	 * cette ligne est composé des classe TitleClass, AlbumClass , GenreClass ....
	 * cette stratégie est discutable mais plutard si le programme doit évoluer il est logique d'avoir un découpage de la sorte.
	 * plutard GenreClasse deviendra une énumération
	 * AlbumClass aura une liste de Musique et ArtistClass une liste de d'album
	 * mais il est vrai que d'utiliser une class pour sotcker une information c'est beaucoup
	 */
	
	private TitleClass title;
	private AlbumClass album;
	private ArtistClass artist;
	private GenreClass genre;
	private YearClass year;
	private DurationClass duration;
	
	public Musique(String title,String album,String artist,String genre,String year,String duration){
		this.title=new TitleClass(title, this);
		this.album=new AlbumClass(album, this);
		this.artist=new ArtistClass(artist, this);
		this.genre=new GenreClass(genre, this);
		this.year=new YearClass(year, this);
		this.duration=new DurationClass(duration, this);
	}
	public TitleClass getTitle() {
		return title;
	}

	public AlbumClass getAlbum() {
		return album;
	}

	public ArtistClass getArtist() {
		return artist;
	}

	public GenreClass getGenre() {
		return genre;
	}

	public YearClass getYear() {
		return year;
	}

	public DurationClass getDuration() {
		return duration;
	}
}
