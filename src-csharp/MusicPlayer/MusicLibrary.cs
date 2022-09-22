

public class MusicLibrary
{
    List<Music> playlist;
    public MusicLibrary(List<Music> newPlaylist)
    {
        playlist = newPlaylist;
    }


    public void printSongs()
    {
        for (int i = 0; i < playlist.Count(); i++)
        {
            Console.WriteLine(playlist[i]);
        }
    }

    public List<string> returnSongs()
    {
        List<string> stringPlaylist = new List<string>();
        for (int i = 0; i < playlist.Count(); i++)
        {
            stringPlaylist.Add(playlist[i].ToString());
        }
        return stringPlaylist;
    }

    public string returnASongByTitle(string title)
    {
        for (int i = 0; i < playlist.Count(); i++)
        {
            if (playlist[i].Title.Equals(title,StringComparison.Ordinal))
            {
                return playlist[i].ToString();
            }
        }
        return "Song not found";
    }


    public void addSong(Music newSong)
    {
        playlist.Add(newSong);
    }

    public bool removeSong(string title)
    {
        int count = 0;
        for (int i = 0; i < playlist.Count(); i++)
        {
            if (playlist[i].Title.Equals(title, StringComparison.Ordinal))
            {
                playlist.RemoveAt(i);
                count++;
            }
        }
        if (count > 0)
        {
            return true;
        }
        return false;
    }

    public List<string> returnSongsByArtist(string artist)
    {
        var songs = new List<string>();
        int count = 0;
        for (int i = 0; i < playlist.Count(); i++)
        {
            var songEntry = playlist[i];
            for (int j = 0; j < songEntry.Artists.Count; j++)
            {
                if (songEntry.Artists[j].Equals(artist, StringComparison.Ordinal))
                {
                    songs.Add(songEntry.ToString());
                    count++;
                }
            }
        }
        if (count == 0)
        {
            songs.Add("no songs found by that artist");
        }
        return songs;
    }
}

