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
	pg_query("BEGIN");    
    // tällä hankitaan viimeisin asiakasid, jota lisätään yhdellä uutta asiakasta 
    // lisätessä
    
    $tulos = pg_query("SELECT MAX(id)+1 FROM tarvike");
    settype($tarvikeid, "integer");
    
    // tässä testataan, palauttiko ylempi kysely mitään - jos ei, on kyseessä yrityksen
    // ensimmäinen asiakas ja tunniste (id) asetetaan ykköseksi    
    if (!$tulos)
    {
       	$tarvikeid = 1;
    }
    
    // kyselyn palauttaessa viimeisimmän id:n lisättynä yhdellä, annetaan tulos uuden 
    // asiakkaan tunnisteeksi (id)
    
    else
    {
    	$rivi = pg_fetch_row($tulos);
       	$tarvikeid = $rivi[0];
    }
    
	// suojataan merkkijonot ennen kyselyn suorittamista
    // id |    nimi    | hinta | veroprosentti | varastossa 
    $nimi = pg_escape_string($_POST['nimi']);
    $hinta = intval($_POST['hinta']);
    $veroprosentti = intval($_POST['vero']);
    $varastossa = intval($_POST['varasto']);

    // jos kenttiin on syötetty jotain, lisätään tiedot kantaan
    $tiedot_ok = trim($nimi) != '' && $hinta >= 0 && $veroprosentti >= 0 && $varastossa >= 0;	

    if ($tiedot_ok)
    {
        $kysely = "INSERT INTO tarvike(id, nimi, hinta, veroprosentti, varastossa) 
        VALUES('$tarvikeid', '$nimi', '$hinta', '$veroprosentti', '$varastossa');";
        $paivitys = pg_query($kysely);

        // asetetaan viesti-muuttuja lisäämisen onnistumisen mukaan
		// lisätään virheilmoitukseen myös virheen syy (pg_last_error)
        if ($paivitys && (pg_affected_rows($paivitys) > 0))
            $viesti = 'Tarvike lisätty varastoon! Voit sulkea välilehden tai lisätä toisen tarvikkeen.';
        else
            $viesti = 'Tarviketta ei lisätty: ' . pg_last_error($yhteys);
    }
    else
        $viesti = 'Annetut tiedot puutteelliset - tarkista, ole hyvä!';

}

if($viesti == 'Tarvike lisätty varastoon! Voit sulkea välilehden tai lisätä toisen tarvikkeen.')
	pg_query("COMMIT");
else
	pg_query("ROLLBACK");
// suljetaan tietokantayhteys

pg_close($yhteys);

?>

<html lang ="fi">
	<head>
		<meta charset="UTF-8" />
		<title>
			Tarvikkeen lisääminen
		</title>
		<link rel="stylesheet" type="text/css" href="mystyle.css" />
	</head>
 	<body>
		<h1>
			Tmi Sähkötärsky
		</h1>
    	<!-- Lomake lähetetään samalle sivulle (vrt lomakkeen kutsuminen) -->
    	<form action="tarvikkeenlisays.php" method="post">
        <h2>Tarvikkeen lisääminen tietokantaan</h2>

    	<?php if (isset($viesti)) echo '<p style="color:purple">'.$viesti.'</p>'; ?>

		<!--PHP-ohjelmassa viitataan kenttien nimiin (name) -->
		<table border="0" cellspacing="0" cellpadding="3">
		    <tr>
    		    <td>Tarvikkeen nimi</td>
    		    <tr></tr>
    		    <td>
    		    	<input type="text" name="nimi" value='' />
    		    </td>
		    </tr>
		    <tr>
    		    <td>Tarvikkeen hinta</td>
    		    <tr></tr>
    		    <td>
    		    	<input type="text" name="hinta" value='' />
				</td>
	    	</tr>
	    	<tr>
	    		<td>Tarvikkeen veroprosentti</td>
	    		<tr></tr>
	    		<td>
	    			<input type="text" name="vero" value='' />
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>Tarvikkeen kappalemäärä varastossa</td>
				<tr></tr>
	    		<td>
	    			<input type="text" name="varasto" value='' />
	    		</td>
	    	</tr>
		</table>

		<br />

		<!-- hidden-kenttää käytetään varotoimena, esim. IE ei välttämättä
		 lähetä submit-tyyppisen kentön arvoja jos lomake lähetetään
		 enterin painalluksella. Tätä arvoa tarkkailemalla voidaan
		 skriptissä helposti päätellä, saavutaanko lomakkeelta. -->
	
		<input type="hidden" name="tallenna" value="jep" />
		<input type="submit" value="Lisää tarvike" />
		</form>
		<footer>Tehnyt Henri A. Juvonen huhtikuussa 2016. Kopiointi sallittu.</footer>
	</body>
</html>