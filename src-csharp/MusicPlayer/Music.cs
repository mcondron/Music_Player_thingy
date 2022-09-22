public class Music
{
    /**
     * A class to represent a music file in a collection.
     *
     * @author Elisa Heinricher
     * @version Fall2022
     * @ported to C# by CCondron
     */

    //instance properties
    public string Title { get => _title; set => _title = value; } //explict property with inline fucntions
    private string _title;
    public int Year { get => _year; set => _year = value; }
    private int _year;
    public string Album { get => _album; set => _album = value; }
    private string _album;
    public List<string> Artists //explic property with explict getters and setters to protect private instance
    {
        get { return new List<string>(_artists); }
        set
        {
            if (_artists == null)
            {
                _artists = new List<string>(value);
            }
            else
            {
                _artists.Clear();
                _artists.AddRange(value);
            }
        }
    }
    private List<string> _artists;
    public int PlayCount { get => _playCount; } //note: play count cannot be set externally, so no set function is defined
    private int _playCount;
    public double Rating { get; set; } // example auto property with implict backing field

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

    public Music(string title, List<string> artists, string album, int year, int playCount, double rating)
    {
        _title = title ?? string.Empty; //null resolution operator
        _artists = artists ?? new List<string>();
        _album = album ?? string.Empty;
        _year = year;
        _playCount = playCount;
        Rating = rating;
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
    public Music(string title, List<string> artists, string album, int year) : this(title, artists, album, year, 0, 5.0) { }

    /**
     * Creates a new Music object with the given title and year.
     * The album is set to "Unknown" and the musician list is empty.
     * @param title The title of the music

     */
    public Music(string title, List<string> artists, string album) : this(title, artists, album, 0, 0, 5.0) { }


    /**
     * "Plays" the music (currently just increments the number of times the music has been played).
     */
    public void play()
    {
        _playCount++;
    }



    public override string ToString()
    {
        return _title + "," + _artists.Count() + artistsToString(_artists) + "," + _album + "," + _year + "," + _playCount + "," + Rating;
    }

    public string artistsToString(List<string> artists)
    {
        string artistsString = "";
        for (int i = 0; i < artists.Count(); i++)
        {
            artistsString = artistsString + "," + artistsString[i];
        }
        return artistsString;
    }

    private static string DELIM = ","; //for file writing

     

    public static bool TryParseFileString(string input, out Music? music)
    {
        var fields = input.Split(DELIM);
        try
        { //in case we fail to parse anything, such as format didn't work
            var artists = new List<string>();
            var fieldPosition = 0;
            string title = fields[fieldPosition++]; // post increment [var]++ will take the current value then increment the var
            int numArtists = int.Parse(fields[fieldPosition++]);
            for (int i = 0; i < numArtists; i++)
            {
                artists.Add(fields[fieldPosition++]);
            }
            string album = fields[fieldPosition++];
            int year = int.Parse(fields[fieldPosition++]); //year is an int
            int playCount = int.Parse(fields[fieldPosition++]);
            double rating = double.Parse(fields[fieldPosition++]);

            music = new Music(title, artists, album, year, playCount, rating);
            return true;
        }
        catch (Exception e)
        {
            Console.WriteLine($"Failed to parse music: {input} error: {e.Message}"); // c # string interpolation 
            music = null;
            return false;
        }

    }

}
