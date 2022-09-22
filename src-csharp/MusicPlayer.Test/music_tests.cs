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
            Assert.Equal("World's Smallest Violin", music.Title);
            Assert.Equal("OK ORCHESTRA", music.Album);
            Assert.Collection(
                music.Artists,
                a => {Assert.Equal("AJR", a);} 
                            );
            Assert.Equal(2021, music.Year);
            Assert.Equal(0, music.PlayCount);
            Assert.Equal(0, music.Rating);            
        }
    [Fact]
        public void can_parse_multi_artist()
        {
            var line = "World's Smallest Violin,2,AJR,MJ,OK ORCHESTRA,2021,0,0,";
            var music = Music.parseFileString(line);
            Assert.NotNull(music);
            Assert.Equal("World's Smallest Violin", music.Title);
            Assert.Equal("OK ORCHESTRA", music.Album);
            Assert.Collection(
                music.Artists,
                a => {Assert.Equal("AJR", a);},
                a => {Assert.Equal("MJ", a);} 
                            );
            Assert.Equal(2021, music.Year);
            Assert.Equal(0, music.PlayCount);
            Assert.Equal(0, music.Rating);            
        }
    }
}