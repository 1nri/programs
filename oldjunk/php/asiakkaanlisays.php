<?php

// luodaan tietokantayhteys ja ilmoitetaan mahdollisesta virheestä
$y_tiedot = "host=dbstud.sis.uta.fi port=5432 dbname=tiko2016db26 user=a531883 
password=_4ntiquE!";

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
    $tulos = pg_query("SELECT MAX(id)+1 FROM asiakas");
    settype($asiakasid, "integer");

    // tässä testataan, palauttiko ylempi kysely mitään - jos ei, on kyseessä 
    // yrityksen ensimmäinen asiakas ja tunniste (id) asetetaan ykköseksi    
   	if (!$tulos)
   	{
   	   	$asiakasid = 1;
   	}

   	// kyselyn palauttaessa viimeisimmän id:n lisättynä yhdellä, annetaan tulos uuden 
   	// asiakkaan tunnisteeksi (id)
   	else
   	{
   		$rivi = pg_fetch_row($tulos);
   	   	$asiakasid = $rivi[0];
   	}

	// suojataan merkkijonot ennen kyselyn suorittamista
   	$nimi = pg_escape_string($_POST['nimi']);
   	$kanta = pg_escape_string($_POST['kanta_asiakas']);
   	$tili = pg_escape_string($_POST['tilinumero']);
   	$puh = pg_escape_string($_POST['puhelinnumero']);

    // jos kenttiin on syötetty jotain, lisätään tiedot kantaan
    $tiedot_ok = trim($nimi) != '' && trim($kanta) != '' && trim($tili) != '' && 
    trim($puh) != '';	

    if ($tiedot_ok)
    {
    	// asiakkaan lisäys kantaan
        $kysely = "INSERT INTO asiakas(id, nimi, kanta_asiakas, tilinumero, puhelinnumero) 
        VALUES('$asiakasid', '$nimi', '$kanta', '$tili', '$puh')";
        $paivitys = pg_query($kysely);

        // asetetaan viesti-muuttuja lisäämisen onnistumisen mukaan
		// lisätään virheilmoitukseen myös virheen syy (pg_last_error)
        if ($paivitys && (pg_affected_rows($paivitys) > 0))
            $viesti = 'Asiakas lisätty! Voit sulkea välilehden tai lisätä toisen asiakkaan.';
        else
            $viesti = 'Asiakasta ei lisätty: ' . pg_last_error($yhteys);
    }

    else
        $viesti = 'Annetut tiedot puutteelliset - tarkista, ole hyvä!';
}

if($viesti == 'Asiakas lisätty! Voit sulkea välilehden tai lisätä toisen asiakkaan.')
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
			Asiakkaan lisääminen
		</title>
		<link rel="stylesheet" type="text/css" href="mystyle.css" />
	</head>
	<body>
		<h1>
			Tmi Sähkötärsky
		</h1>
	    <!-- Lomake lähetetään samalle sivulle (vrt lomakkeen kutsuminen) -->
	    <form action="asiakkaanlisays.php" method="post">
	
	    	<h2>
	    		Asiakkaan lisääminen tietokantaan
	    	</h2>

		    <?php if (isset($viesti)) echo '<p style="color:purple">'.$viesti.'</p>'; ?>

			<!--PHP-ohjelmassa viitataan kenttien nimiin (name) -->
			<table border="0" cellspacing="0" cellpadding="3">
			    <tr>
		    	    <td>
		    	    	Asiakkaan etu- ja sukunimi
		    	    </td>
		    	    
		    	    <td>
		    	    	<input type="text" name="nimi" value="" />
		    	    </td>
			    </tr>
			    <tr>
		    	    <td>
		    	    	Asiakas kanta-asiakas?
		    	    </td>
		    	    <td>
		    	    	
		    	    	<input class="radio" type="radio" name="kanta_asiakas" value="true"> 
		    	    	Kyllä
		    	    	</input>
		    	    	<br />
						<input class="radio" type="radio" name="kanta_asiakas" value="false" checked>
						Ei
						</input>  
					</td>
			    </tr>
			    <tr>
			    	<td>
			    		Tilinumero
			    	</td>
			    	<td>
			    		<input type="text" name="tilinumero" value="" />
			    	</td>
			    </tr>
			    <tr>
			    	<td>
			    		Puhelinnumero
			    	</td>
			    	<td>
			    		<input type="text" name="puhelinnumero" value="" />
			    	</td>
			    </tr>
			</table>
		
			<br />
			
			<!-- hidden-kenttää käytetään varotoimena, esim. IE ei välttämättä
			 lähetä submit-tyyppisen kentön arvoja jos lomake lähetetään
			 enterin painalluksella. Tätä arvoa tarkkailemalla voidaan
			 skriptissä helposti päätellä, saavutaanko lomakkeelta. -->
		
			<input type="hidden" name="tallenna" value="jep" />
			<input type="submit" value="Lisää asiakas" />
		</form>
		<footer>
			Tehnyt Henri A. Juvonen huhtikuussa 2016. Kopiointi sallittu.
		</footer>
	</body>
</html>