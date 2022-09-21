import java.util.ArrayList;

public class MusicLibrary {
    ArrayList<Music> playlist;
    public MusicLibrary(ArrayList<Music> newPlaylist){
        playlist = newPlaylist;
    }


    public void printSongs() {
        for(int i = 0; i < playlist.size(); i++){
            System.out.println(playlist.get(i));
        }
    }

    public ArrayList<String> returnSongs(){
        ArrayList<String> stringPlaylist= new ArrayList<>();
        for(int i = 0; i < playlist.size(); i++){
            stringPlaylist.add(playlist.get(i).toString());
        }
        return stringPlaylist;
    }

    public String returnASongByTitle(String title){
        for(int i = 0; i < playlist.size(); i++){
            if(playlist.get(i).getTitle().equals(title)){
               return playlist.get(i).toString();
            }
        }
        return "Song not found";
    }


    public void addSong(Music newSong){
        playlist.add(newSong);
    }

    public boolean removeSong(String title){
        int count = 0;
        for(int i = 0; i < playlist.size(); i++){
            if(playlist.get(i).getTitle().equals(title)){
                playlist.remove(i);
                count ++;
            }
        }
        if(count > 0){
            return true;
        }
        return false;
    }

    public ArrayList<String> returnSongsByArtist(String artist){
        ArrayList<String> songs = new ArrayList<>();
        int count = 0;
        for(int i = 0; i < playlist.size(); i++){
            for(int j = 0; j < playlist.get(i).getArtists().size(); j++){
                if(playlist.get(i).getArtists().get(j).equals(artist)) {
                    songs.add(playlist.get(i).toString());
                    count++;
                }
            }
        }
        if(count == 0){
            songs.add("no songs found by that artist");
        }
        return songs;
    }
}

