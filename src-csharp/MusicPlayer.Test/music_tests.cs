using Xunit;

namespace MusicPlayer.Test
{
    public class music_tests
    {


        [Fact]
        public void can_parse_single_artist()
        {
            var line = "World's Smallest Violin,1,AJR,OK ORCHESTRA,2021,0,0,";
            var music = Music.parseFileString(line);
            Assert.NotNull(music);
            Assert.Equal("World's Smallest Violin", music.getTitle());
            Assert.Equal("OK ORCHESTRA", music.getAlbum());
            Assert.Collection(
                music.getArtists(),
                a => {Assert.Equal("AJR", a);} 
                            );
            Assert.Equal(2021, music.getYear());
            Assert.Equal(0, music.getPlayCount());
            Assert.Equal(0, music.getRating());            
        }
    [Fact]
        public void can_parse_multi_artist()
        {
            var line = "World's Smallest Violin,2,AJR,MJ,OK ORCHESTRA,2021,0,0,";
            var music = Music.parseFileString(line);
            Assert.NotNull(music);
            Assert.Equal("World's Smallest Violin", music.getTitle());
            Assert.Equal("OK ORCHESTRA", music.getAlbum());
            Assert.Collection(
                music.getArtists(),
                a => {Assert.Equal("AJR", a);},
                a => {Assert.Equal("MJ", a);} 
                            );
            Assert.Equal(2021, music.getYear());
            Assert.Equal(0, music.getPlayCount());
            Assert.Equal(0, music.getRating());            
        }
    }
}