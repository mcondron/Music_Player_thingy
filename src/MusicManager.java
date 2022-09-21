import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MusicManager {
    ArrayList<String> menu = new ArrayList<String>();

    Scanner sc = new Scanner(System.in);
    MusicLibrary playlist;

    public MusicManager() {
        createMenu();
        while (true) {
            printMenu();
            choice();
        }
    }

        public void createMenu() {
            menu.add("Welcome to your Music Library!");
            menu.add("Please select an option");
            menu.add("1. Load playlist");
            menu.add("2. List songs in playlist");
            menu.add("3. Look up a song by title");
            menu.add("4. Add a song to the playlist");
            menu.add("5. Remove a song from the playlist");
            menu.add("6. List all songs by an artist");
            menu.add("7. Save the playlist");
            menu.add("8. Exit the system");
        }

        public void printMenu() {
            System.out.println();
            for (String item: menu){
                System.out.println(item);
            }
        }

        public void choice() {
            System.out.println("Please enter the number of your selection: ");
            int num = sc.nextInt();

            switch(num) {
                case 1: loadNewPlaylist();
                    break;
                case 2: playlist.printSongs();
                    break;
                case 3: printASongByTitle();
                    break;
                case 4: addSongToPlaylist();
                    break;
                case 5: removeSongFromPlaylist();
                    break;
                case 6: printSongsByArtist();
                    break;
                case 7: savePlaylistToFile();
                        break;
                case 8: System.out.println("good bye");
                    System.exit(0);
                    break;
            }
        }

    public static ArrayList<Music> loadMusicFromFile()
    {
        ArrayList<Music> musicList = new ArrayList<Music>();

        JFileChooser chooser = new JFileChooser(); //a file chooser
        chooser.setFileFilter(new FileNameExtensionFilter("CSV", "csv")); //for csv files
        int returnVal = chooser.showOpenDialog(chooser); //show the dialog to OPEN
        if(returnVal == JFileChooser.APPROVE_OPTION) //if they picked something
        {
            try {
                Scanner sc = new Scanner(chooser.getSelectedFile()); //parse it with Scanner
                while(sc.hasNextLine())
                {
                    Music music = Music.parseFileString(sc.nextLine());
                    if(music != null)
                        musicList.add(music);
                }
                sc.close();
            }
            catch(Exception e) {
                System.out.println("Error loading from file: " + e);
                e.printStackTrace();
            }
        }
        return musicList;
    }

    public void loadNewPlaylist(){
        playlist = new MusicLibrary(loadMusicFromFile());
    }

    public void printASongByTitle(){
        System.out.println("Please enter the song title");
        sc.nextLine();
        String song = sc.nextLine();
        System.out.println(playlist.returnASongByTitle(song));
    }

    public void addSongToPlaylist(){
        System.out.println("Please enter song information");
        sc.nextLine();
        Music newSong = Music.parseFileString(sc.nextLine());
        playlist.addSong(newSong);
    }

    public void removeSongFromPlaylist(){
        System.out.println("Please enter the name of the song");
        sc.nextLine();
        String exSong = sc.nextLine();
        if(playlist.removeSong(exSong)){
            System.out.println("Song deleted");
        }
        else{
            System.out.println("Song not found");
        }
    }

    public void printSongsByArtist(){
        System.out.println("Please enter the name of the artist");
        sc.nextLine();
        String artist = sc.nextLine();
        System.out.println(playlist.returnSongsByArtist(artist));
    }

    public void savePlaylistToFile(){//this method doesn't work right, it overwrites the file, but it saves the file blank
        JFileChooser chooser = new JFileChooser(); //JFileChooser methods are copy and pasted from Mrs. H's code
        chooser.setFileFilter(new FileNameExtensionFilter("CSV", "csv"));
        int returnVal = chooser.showOpenDialog(chooser);
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
            try {
                FileWriter writer = new FileWriter(chooser.getSelectedFile()); //FileWriter methods are adapted from Geeks for Geeks
                ArrayList<String> updatedPlaylist = playlist.returnSongs();
                String updatedPlaylistSting = new String();
                for(int i = 0; i < updatedPlaylist.size(); i++) {
                    updatedPlaylistSting = updatedPlaylistSting + updatedPlaylist.get(i);
                }
                writer.write(updatedPlaylistSting);
            }
            catch(Exception e) { //also from Mrs. H
                System.out.println("Error saving from file: " + e);
                e.printStackTrace();
            }
        }

    }

}


