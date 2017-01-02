package control;

/* This program is licensed under the terms of the GPLV3 or newer*/
/* Written by Johannes Putzke*/
/* eMail: die_eule@gmx.net*/ 


public class Shoutcast2 {
	
	/**
	 * Get the genres with all subgenres back
	 * @return the Genres with all subgenres. Every Line of the array
	 * starts with the genre. All other are the subgenres for this genre
	 */
	public String[][] getGenres() {
		String[][] genres = new String[24][];
		
		String[] tmpGenres = new String[23];
		
		tmpGenres[0] = "Alternative";
		tmpGenres[1] = "Adult Altergenres";
		tmpGenres[2] = "Britpop";
		tmpGenres[3] = "Classic Alternative";
		tmpGenres[4] = "College";
		tmpGenres[5] = "Dancepunk";
		tmpGenres[6] = "Dream Pop";
		tmpGenres[7] = "Emo";
		tmpGenres[8] = "Goth";
		tmpGenres[9] = "Grunge";
		tmpGenres[10] = "Hardcore";
		tmpGenres[11] = "Indie Pop";
		tmpGenres[12] = "Indie Rock";
		tmpGenres[13] = "Industrial";
		tmpGenres[14] = "LoFi";
		tmpGenres[15] = "Modern Rock";
		tmpGenres[16] = "New Wave";
		tmpGenres[17] = "Noise Pop";
		tmpGenres[18] = "Post Punk";
		tmpGenres[19] = "Power Pop";
		tmpGenres[20] = "Punk";
		tmpGenres[21] = "Ska";
		tmpGenres[22] = "Xtreme";
		
		genres[0] = tmpGenres;
		tmpGenres = new String[8];
		
		tmpGenres[0] = "Blues";
		tmpGenres[1] = "Acoustic Blues";
		tmpGenres[2] = "Cajun and Zydeco";
		tmpGenres[3] = "Chicago Blues";
		tmpGenres[4] = "Contemporary Blues";
		tmpGenres[5] = "Country Blues";
		tmpGenres[6] = "Delta Blues";
		tmpGenres[7] = "Electric Blues";
		
		genres[1] = tmpGenres;
		tmpGenres = new String[12];
		
		tmpGenres[0] = "Classical";
		tmpGenres[1] = "Baroque";
		tmpGenres[2] = "Chamber";
		tmpGenres[3] = "Choral";
		tmpGenres[4] = "Classical Period";
		tmpGenres[5] = "Early Classical";
		tmpGenres[6] = "Impressionist";
		tmpGenres[7] = "Modern";
		tmpGenres[8] = "Opera";
		tmpGenres[9] = "Piano";
		tmpGenres[10] = "Romantic";
		tmpGenres[11] = "Symphony";
		
		genres[2] = tmpGenres;
		tmpGenres = new String[10];
		
		tmpGenres[0] = "Country";
		tmpGenres[1] = "Alt Country";
		tmpGenres[2] = "Americana";
		tmpGenres[3] = "Bluegrass";
		tmpGenres[4] = "Classic Country";
		tmpGenres[5] = "Contemporary Bluegrass";
		tmpGenres[6] = "Contemporary Country";
		tmpGenres[7] = "Honky Tonk";
		tmpGenres[8] = "Hot Country Hits";
		tmpGenres[9] = "Western";
		
		genres[3] = tmpGenres;
		tmpGenres = new String[9];
		
		tmpGenres[0] = "Decades";
		tmpGenres[1] = "00s";
		tmpGenres[2] = "30s";
		tmpGenres[3] = "40s";
		tmpGenres[4] = "50s";
		tmpGenres[5] = "60s";
		tmpGenres[6] = "70s";
		tmpGenres[7] = "80s";
		tmpGenres[8] = "90s";


		genres[4] = tmpGenres;
		tmpGenres = new String[7];
		
		tmpGenres[0] = "Easy Listening";
		tmpGenres[1] = "Exotica";
		tmpGenres[2] = "Light Rock";
		tmpGenres[3] = "Lounge";
		tmpGenres[4] = "Orchestral Pop";
		tmpGenres[5] = "Polka";
		tmpGenres[6] = "Space Age Pop";
		
		genres[5] = tmpGenres;
		tmpGenres = new String[21];
		
		tmpGenres[0] = "Electronic";
		tmpGenres[1] = "Acid House";
		tmpGenres[2] = "Ambient";
		tmpGenres[3] = "Big Beat";
		tmpGenres[4] = "Breakbeat";
		tmpGenres[5] = "Dance";
		tmpGenres[6] = "Demo";
		tmpGenres[7] = "Disco";
		tmpGenres[8] = "Downtempo";
		tmpGenres[9] = "Drum and Bass";
		tmpGenres[10] = "Electro";
		tmpGenres[11] = "Garage";
		tmpGenres[12] = "Hard House";
		tmpGenres[13] = "House";
		tmpGenres[14] = "IDM";
		tmpGenres[15] = "Jungle";
		tmpGenres[16] = "Progressive";
		tmpGenres[17] = "Techno";
		tmpGenres[18] = "Trance";
		tmpGenres[19] = "Tribal";
		tmpGenres[20] = "Trip Hop";
		
		genres[6] = tmpGenres;
		tmpGenres = new String[8];
		
		tmpGenres[0] = "Folk";
		tmpGenres[1] = "Alternative Folk";
		tmpGenres[2] = "Contemporary Folk";
		tmpGenres[3] = "Folk Rock";
		tmpGenres[4] = "New Acoustic";
		tmpGenres[5] = "Old Time";
		tmpGenres[6] = "Traditional Folk";
		tmpGenres[7] = "World Folk";

		genres[7] = tmpGenres;
		tmpGenres = new String[12];
		
		tmpGenres[0] = "Inspirational";
		tmpGenres[1] = "Christian";
		tmpGenres[2] = "Christian Metal";
		tmpGenres[3] = "Christian Rap";
		tmpGenres[4] = "Christian Rock";
		tmpGenres[5] = "Classic Christian";
		tmpGenres[6] = "Contemporary Gospel";
		tmpGenres[7] = "Gospel";
		tmpGenres[8] = "Praise and Worship";
		tmpGenres[9] = "Sermons/Services";
		tmpGenres[10] = "Southern Gospel";
		tmpGenres[11] = "Traditional Gospel";
		
		genres[8] = tmpGenres;
		tmpGenres = new String[30];
		
		tmpGenres[0] = "International";
		tmpGenres[1] = "African";
		tmpGenres[2] = "Arabic";
		tmpGenres[3] = "Asian";
		tmpGenres[4] = "Bollywood";
		tmpGenres[5] = "Brazilian";
		tmpGenres[6] = "Caribbean";
		tmpGenres[7] = "Celtic";
		tmpGenres[8] = "Chinese";
		tmpGenres[9] = "European";
		tmpGenres[10] = "Filipino";
		tmpGenres[11] = "French";
		tmpGenres[12] = "Greek";
		tmpGenres[13] = "German";
		tmpGenres[14] = "Hawaiian and Pacific";
		tmpGenres[15] = "Hindi";
		tmpGenres[16] = "Indian";
		tmpGenres[17] = "Japanese";
		tmpGenres[18] = "Jewish";
		tmpGenres[19] = "Klezmer";
		tmpGenres[20] = "Korean";
		tmpGenres[21] = "Mediterranean";
		tmpGenres[22] = "Middle Eastern";
		tmpGenres[23] = "North American";
		tmpGenres[24] = "Soca";
		tmpGenres[25] = "South American";
		tmpGenres[26] = "Tamil";
		tmpGenres[27] = "World";
		tmpGenres[28] = "Worldbeat";
		tmpGenres[29] = "Zouk";
		
		genres[9] = tmpGenres;
		tmpGenres = new String[14];
		
		tmpGenres[0] = "Jazz";
		tmpGenres[1] = "Acid Jazz";
		tmpGenres[2] = "Avant Garde";
		tmpGenres[3] = "Big Band";
		tmpGenres[4] = "Bop";
		tmpGenres[5] = "Classic Jazz";
		tmpGenres[6] = "Cool Jazz";
		tmpGenres[7] = "Fusion";
		tmpGenres[8] = "Hard Bop";
		tmpGenres[9] = "Latin Jazz";
		tmpGenres[10] = "Smooth Jazz";
		tmpGenres[11] = "Swing";
		tmpGenres[12] = "Vocal Jazz";
		tmpGenres[13] = "World Fusion";

		genres[10] = tmpGenres;
		tmpGenres = new String[18];
		
		tmpGenres[0] = "Latin";
		tmpGenres[1] = "Bachata";
		tmpGenres[2] = "Banda";
		tmpGenres[3] = "Bossa Nova";
		tmpGenres[4] = "Cumbia";
		tmpGenres[5] = "Latin Dance";
		tmpGenres[6] = "Latin Pop";
		tmpGenres[7] = "Latin Rap and Hip-Hop";
		tmpGenres[8] = "Latin Rock";
		tmpGenres[9] = "Mariachi";
		tmpGenres[10] = "Merengue";
		tmpGenres[11] = "Ranchera";
		tmpGenres[12] = "Reggaeton";
		tmpGenres[13] = "Regional Mexican";
		tmpGenres[14] = "Salsa";
		tmpGenres[15] = "Tango";
		tmpGenres[16] = "Tejano";
		tmpGenres[17] = "Tropicalia";

		genres[11] = tmpGenres;
		tmpGenres = new String[11];
		
		tmpGenres[0] = "Metal";
		tmpGenres[1] = "Black Metal";
		tmpGenres[2] = "Classic Metal";
		tmpGenres[3] = "Extreme Metal";
		tmpGenres[4] = "Grindcore";
		tmpGenres[5] = "Hair Metal";
		tmpGenres[6] = "Heavy Metal";
		tmpGenres[7] = "Metalcore";
		tmpGenres[8] = "Power Metal";
		tmpGenres[9] = "Progressive Metal";
		tmpGenres[10] = "Rap Metal";
		
		genres[12] = tmpGenres;
		tmpGenres = new String[6];
		
		tmpGenres[0] = "New Age";
		tmpGenres[1] = "Environmental";
		tmpGenres[2] = "Ethnic Fusion";
		tmpGenres[3] = "Healing";
		tmpGenres[4] = "Meditation";
		tmpGenres[5] = "Spiritual";

		genres[13] = tmpGenres;
		tmpGenres = new String[13];
		
		tmpGenres[0] = "Pop";
		tmpGenres[1] = "Adult Contemporary";
		tmpGenres[2] = "Barbershop";
		tmpGenres[3] = "Bubblegum Pop";
		tmpGenres[4] = "Dance Pop";
		tmpGenres[5] = "Idols";
		tmpGenres[6] = "JPOP";
		tmpGenres[7] = "KPOP";
		tmpGenres[8] = "Oldies";
		tmpGenres[9] = "Soft Rock";
		tmpGenres[10] = "Teen Pop";
		tmpGenres[11] = "Top 40";
		tmpGenres[12] = "World Pop";
	
		genres[14] = tmpGenres;
		tmpGenres = new String[5];
		
		tmpGenres[0] = "Public Radio";
		tmpGenres[1] = "College";
		tmpGenres[2] = "News";
		tmpGenres[3] = "Sports";
		tmpGenres[4] = "Talk";
		
		genres[15] = tmpGenres;
		tmpGenres = new String[1];
		
		tmpGenres[0] = "R&B and Urban";
//		tmpGenres[1] = "Classic R&B";
//		tmpGenres[2] = "Contemporary R&B";
//		tmpGenres[3] = "Doo Wop";
//		tmpGenres[4] = "Funk";
//		tmpGenres[5] = "Motown";
//		tmpGenres[6] = "Neo-Soul";
//		tmpGenres[7] = "Quiet Storm";
//		tmpGenres[8] = "Soul";
//		tmpGenres[9] = "Urban Contemporary";

		genres[16] = tmpGenres;
		tmpGenres = new String[12];
		
		tmpGenres[0] = "Rap";
		tmpGenres[1] = "Alternative Rap";
		tmpGenres[2] = "Dirty South";
		tmpGenres[3] = "East Coast Rap";
		tmpGenres[4] = "Freestyle";
		tmpGenres[5] = "Gangsta Rap";
		tmpGenres[6] = "Hip Hop";
		tmpGenres[7] = "Mixtapes";
		tmpGenres[8] = "Old School";
		tmpGenres[9] = "Turntablism";
		tmpGenres[10] = "Underground Hip-Hop";
		tmpGenres[11] = "West Coast Rap";
		
		genres[17] = tmpGenres;
		tmpGenres = new String[8];
		
		tmpGenres[0] = "Reggae";
		tmpGenres[1] = "Contemporary Reggae";
		tmpGenres[2] = "Dancehall";
		tmpGenres[3] = "Dub";
		tmpGenres[4] = "Pop Reggae";
		tmpGenres[5] = "Ragga";
		tmpGenres[6] = "Reggae Roots";
		tmpGenres[7] = "Rock Steady";
		
		genres[18] = tmpGenres;
		tmpGenres = new String[16];

		tmpGenres[0] = "Rock";
		tmpGenres[1] = "Adult Album Alternative";
		tmpGenres[2] = "British Invasion";
		tmpGenres[3] = "Classic Rock";
		tmpGenres[4] = "Garage Rock";
		tmpGenres[5] = "Glam";
		tmpGenres[6] = "Hard Rock";
		tmpGenres[7] = "Jam Bands";
		tmpGenres[8] = "JROCK";
		tmpGenres[9] = "Piano Rock";
		tmpGenres[10] = "Prog Rock";
		tmpGenres[11] = "Psychedelic";
		tmpGenres[12] = "Rock & Roll";
		tmpGenres[13] = "Rockabilly";
		tmpGenres[14] = "Singer and SongwriterSurf";
		tmpGenres[15] = "Surf";
		
		genres[19] = tmpGenres;
		tmpGenres = new String[1];
		
		tmpGenres[0] = "Seasonal/Holiday";
//		tmpGenres[1] = "Anniversary";
//		tmpGenres[2] = "Birthday";
//		tmpGenres[3] = "Christmas";
//		tmpGenres[4] = "Halloween";
//		tmpGenres[5] = "Hanukkah";
//		tmpGenres[6] = "Honeymoon";
//		tmpGenres[7] = "Kwanzaa";
//		tmpGenres[8] = "Valentine";
//		tmpGenres[9] = "Wedding";
//		tmpGenres[10] = "Winter";
		
		genres[20] = tmpGenres;
		tmpGenres = new String[6];
		
		tmpGenres[0] = "Soundtracks";
		tmpGenres[1] = "Anime";
		tmpGenres[2] = "Kids";
		tmpGenres[3] = "Original Score";
		tmpGenres[4] = "Showtunes";
		tmpGenres[5] = "Video Game Music";
		
		genres[21] = tmpGenres;
		tmpGenres = new String[14];
		
		tmpGenres[0] = "Talk";
		tmpGenres[1] = "Blog";
		tmpGenres[2] = "Comedy";
		tmpGenres[3] = "Community";
		tmpGenres[4] = "Educational";
		tmpGenres[5] = "Government";
		tmpGenres[6] = "News";
		tmpGenres[7] = "Old Time Radio";
		tmpGenres[8] = "Other Talk";
		tmpGenres[9] = "Political";
		tmpGenres[10] = "Scanner";
		tmpGenres[11] = "Spoken Word";
		tmpGenres[12] = "Sports";
		tmpGenres[13] = "Technology";

		genres[22] = tmpGenres;
		tmpGenres = new String[21];
		
		tmpGenres[0] = "Themes";
		tmpGenres[1] = "Adult";
		tmpGenres[2] = "Best Of";
		tmpGenres[3] = "Chill";
		tmpGenres[4] = "Eclectic";
		tmpGenres[5] = "Experimental";
		tmpGenres[6] = "Female";
		tmpGenres[7] = "Heartache";
		tmpGenres[8] = "Instrumental";
		tmpGenres[9] = "LGBT";
		tmpGenres[10] = "Love and Romance";
		tmpGenres[11] = "Party Mix";
		tmpGenres[12] = "Patriotic";
		tmpGenres[13] = "Rainy Day Mix";
		tmpGenres[14] = "Reality";
		tmpGenres[15] = "Sexy";
		tmpGenres[16] = "Shuffle";
		tmpGenres[17] = "Travel Mix";
		tmpGenres[18] = "Tribute";
		tmpGenres[19] = "Trippy";
		tmpGenres[20] = "Work Mix";

		genres[23] = tmpGenres;
		return genres;

	}
}
