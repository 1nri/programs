<?php

// luodaan tietokantayhteys ja ilmoitetaan mahdollisesta virheestä

session_start();
$y_tiedot = "host=dbstud.sis.uta.fi port=5432 dbname=tiko2016db26 user=a531883 password=_4ntiquE!";

if (!$yhteys = pg_connect($y_tiedot))
   die("Tietokantayhteyden luominen epäonnistui.");

// isset funktiolla jäädään odottamaan syötettä.
// POST on tapa tuoda tietoa lomaketta (tavallaan kutsutaan lomaketta).
// Argumentti tallenna saadaan lomakkeen napin nimestä.

if (isset($_POST['tallenna']))
{
    // suojataan merkkijonot ennen kyselyn suorittamista
    // suojataan merkkijonot ennen kyselyn suorittamista

    $kurssinro = intval($_POST['kurssinro']);
    $etunimi_o = pg_escape_string($_POST['etunimi']);
    $sukunimi_o = pg_escape_string($_POST['sukunimi']);
    $k_nimi = pg_escape_string($_POST['kurssi']);
    $o_pisteet = intval($_POST['pisteet']);

    // jos kenttiin on syötetty jotain, lisätään tiedot kantaan

    $tiedot_ok = $kurssinro != 0 && trim($etunimi_o) != '' && trim($sukunimi_o) != '' && trim($k_nimi) != '' && $o_pisteet != '';

    if ($tiedot_ok)
    {
        $kysely = "INSERT INTO yliopisto.kurssi(knro, etunimi_o, sukunimi_o, k_nimi, o_pisteet)
		 VALUES($kurssinro, '$etunimi_o', '$sukunimi_o', '$k_nimi', '$o_pisteet')";
        $paivitys = pg_query($kysely);

        // asetetaan viesti-muuttuja lisäämisen onnistumisen mukaan
		// lisätään virheilmoitukseen myös virheen syy (pg_last_error)

        if ($paivitys && (pg_affected_rows($paivitys) > 0))
            $viesti = 'Kurssi lisätty!';
        else
            $viesti = 'Kurssia ei lisätty: ' . pg_last_error($yhteys);
    }
    else
        $viesti = 'Annetut tiedot puutteelliset - tarkista, ole hyvä!';

}

// suljetaan tietokantayhteys

pg_close($yhteys);

?>

<html>
 <head>
  <title>Yleismalli</title>
 </head>
 <body>

    <!-- Lomake lähetetään samalle sivulle (vrt lomakkeen kutsuminen) -->
    <form action="yleismalli.php" method="post">

    <h2>Kurssin lisäys tietokantaan</h2>

    <?php if (isset($viesti)) echo '<p style="color:red">'.$viesti.'</p>'; ?>

	<!--PHP-ohjelmassa viitataan kenttien nimiin (name) -->
	<table border="0" cellspacing="0" cellpadding="3">
	    <tr>
    	    <td>Kurssinumero</td>
    	    <td><input type="text" name="kurssinro" value="" /></td>
	    </tr>
	    <tr>
    	    <td>Opettajan etunimi</td>
    	    <td><input type="text" name="etunimi" value="" /></td>
	    </tr>
	    <tr>
	    	<td>Opettajan sukunimi</td>
	    	<td><input type="text" name="sukunimi" value="" /></td>
	    </tr>
	    <tr>
	    	<td>Kurssin nimi</td>
	    	<td><input type="text" name="kurssi" value="" /></td>
	    </tr>
	    <tr>
	    	<td>Opintopisteiden lukumäärä</td>
	    	<td><input type="text" name="pisteet" value="" /> </td>
	    </tr>
	</table>

	<br />

	<!-- hidden-kenttää käytetään varotoimena, esim. IE ei välttämättä
	 lähetä submit-tyyppisen kentön arvoja jos lomake lähetetään
	 enterin painalluksella. Tätä arvoa tarkkailemalla voidaan
	 skriptissä helposti päätellä, saavutaanko lomakkeelta. -->

	<input type="hidden" name="tallenna" value="jep" />
	<input type="submit" value="Lisää kurssi" />
	</form>

</body>
</html>