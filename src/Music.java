import java.util.ArrayList;
import java.util.Scanner;

public class Music {
    /**
     * A class to represent a music file in a collection.
     *
     * @author Elisa Heinricher
     * @version Fall2022
     */

    //instance variables
    private String title;
    private int year;
    private String album;
    private ArrayList<String> artists;
    private int playCount;
    private double rating;

    /**
     * Constructor: full
     * Creates a new Music object from the given parameters
     *
     * @param title     The title of the Music
     * @param year      The year the Music was released
     * @param album     The album to which the music belongs, provide "single" if the music was released separately
     * @param artists   The artists that perform the music
     * @param playCount The number of times the music has been played
     * @param rating    The music's rating
     */

    public Music(String title, ArrayList<String> artists, String album, int year, int playCount, double rating) {
        this.title = title;
        this.artists = artists;
        this.album = album;
        this.year = year;
        this.playCount = playCount;
        this.rating = rating;
    }

    /**
     * Creates a new Music1 object from the given parameters
     * Number of plays starts at 0, and movie has default rating.
     *
     * @param title   The title of the music
     * @param year    The year the music was released
     * @param album   The album to which the music belongs, provid single if the music was released separately
     * @param artists A list of artists that peform the music
     */
    public Music(String title, ArrayList<String> artists, String album, int year) {
        this(title, artists, album, year, 0, 5.0);
    }

    /**
     * Creates a new Music object with the given title and year.
     * The album is set to "Unknown" and the musician list is empty.
     * @param title The title of the music

     */
    public Music(String title, ArrayList<String> artists, String album) {
        this(title, artists, album, 0, 0, 5.0);
    }

    /**
     * Gets the artists in the film.
     *
     * @return the artists as a String
     */
    public ArrayList<String> getArtists() {
        return artists;
    }

    /**
     * Gets the album title
     *
     * @return album as a String
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Gets the title of the music
     *
     * @return the title as a String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the year the music was released
     *
     * @return the year as an int
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets the number of times this music has been played
     *
     * @return the playCount as an int
     */
    public int getPlayCount() {
        return playCount;
    }

    /**
     * Gets the music's current rating
     *
     * @return the rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * "Plays" the music (currently just increments the number of times the music has been played).
     */
    public void play() {
        this.playCount++;
    }

    /**
     * Sets the music's rating
     *
     * @param rating the rating to set
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public void setArtists(String newArtists){
        for(int i = 0; i < artists.size(); i++){
            artists.remove(i);
        }
        artists.add(newArtists);
    }

    public void setAlbum(String newAlbum){
        this.album = newAlbum;
    }

    public void setYear(int newYear){
        this.year = newYear;
    }

    public void setPlayCount(int newCount){
        this.playCount = newCount;
    }


    public String toString(){
        return title + "," + artistsToString(artists) + "," + album + "," + year + "," + playCount + "," + rating;
    }

    public String artistsToString(ArrayList<String> artists){
        String artistsString = new String();
        for(int i = 0; i < artists.size(); i++){
            artistsString = artistsString + "," + artists.get(i);
        }
        return artistsString;
    }

    private static final String DELIM = ","; //for file writing

    public static Music parseFileString(String input)
    {
        Music newMusic = null; //start as null

        Scanner sc = new Scanner(input);
        sc.useDelimiter(DELIM);
        try{ //in case we fail to parse anything, such as format didn't work
            String title = sc.next();
            int numArtists = sc.nextInt();
            ArrayList<String> artists = new ArrayList<>();
            for(int i = 0; i < numArtists; i++){
            artists.add(sc.next());}
            String album = sc.next();
            int year = sc.nextInt(); //year is an int
            int playCount = sc.nextInt();
            double rating = sc.nextDouble();

            newMusic = new Music(title, artists, album, year, playCount, rating);
        }
        catch(Exception e){
            System.out.println("Failed to parse music: " + input);
        }
        sc.close();

        return newMusic;
    }

}
