<?php

// luodaan tietokantayhteys ja ilmoitetaan mahdollisesta virheestä
$y_tiedot = "host=dbstud.sis.uta.fi port=5432 dbname=tiko2016db26 user=a531883 password=_4ntiquE!";

if (!$yhteys = pg_connect($y_tiedot))
   die("Tietokantayhteyden luominen epäonnistui.");

pg_query("BEGIN");
$options = '';
$tulos2 = pg_query("SELECT nimi FROM asiakas");
if(!$tulos2)
	$options = 'Lisää ensimmäinen asiakas ensin!';

else
{
	while ($rivi = pg_fetch_row($tulos2))
	{
	   	$options .= "<option>$rivi[0]</option>";
	}
}

// isset funktiolla jäädään odottamaan syötettä.
// POST on tapa tuoda tietoa lomaketta (tavallaan kutsutaan lomaketta).
// Argumentti tallenna saadaan lomakkeen napin nimestä.
if (isset($_POST['tallenna']))
{
    
    // tällä hankitaan viimeisin kohdeid, jota lisätään yhdellä uutta kohdetta lisätessä
    $tulos = pg_query("SELECT MAX(id)+1 FROM kohde");
    settype($kohdeid, "integer");

    // tässä testataan, palauttiko ylempi kysely mitään - jos ei, on kyseessä yrityksen
    // ensimmäinen kohde ja tunniste (id) asetetaan ykköseksi
    if (!$tulos)
    {
       	$kohdeid = 1;
    }
    
    // kyselyn palauttaessa viimeisimmän id:n lisättynä yhdellä, annetaan tulos uuden 
    // kohteen tunnisteeksi (id)
    else
    {
    	$rivi = pg_fetch_row($tulos);
       	$kohdeid = $rivi[0];
    }
    
	// suojataan merkkijonot ennen kyselyn suorittamista
    $osoite = pg_escape_string($_POST['osoite']);
    $asiakas = pg_escape_string($_POST['asiakas']);

    // jos kenttiin on syötetty jotain, lisätään tiedot kantaan
    $tiedot_ok = trim($osoite) != '' && $asiakas != '';	

    if ($tiedot_ok)
    {
        $kysely = "INSERT INTO kohde(id, osoite)
		 VALUES('$kohdeid', '$osoite')";
        $paivitys = pg_query($kysely);
        
        // asetetaan viesti-muuttuja lisäämisen onnistumisen mukaan
		// lisätään virheilmoitukseen myös virheen syy (pg_last_error)

        if ($paivitys && (pg_affected_rows($paivitys) > 0))
        {
            		
			if(trim($asiakas) != '')
			{
				$tulos3 = pg_query("SELECT id FROM asiakas WHERE nimi = '$asiakas'");
			
				if($tulos3)
				{
					$rivi = pg_fetch_row($tulos3);
					$asiakasid = $rivi[0];
					$kysely3 = "INSERT INTO omistus(kohdeid, asiakasid) 
					VALUES('$kohdeid','$asiakasid')";
					$paivitys3 = pg_query($kysely3);
					
					if ($paivitys3 && (pg_affected_rows($paivitys3) > 0))
						$viesti = 'Kohde lisätty asiakkaalle! Voit sulkea välilehden tai lisätä toisen kohteen.';
				}
			}
        }
        
        else
            $viesti = 'Kohdetta ei lisätty: ' . pg_last_error($yhteys);
    }
    else
        $viesti = 'Annetut tiedot puutteelliset - tarkista, ole hyvä! '. pg_last_error($yhteys);

}

// suljetaan tietokantayhteys
if($viesti == 'Kohde lisätty asiakkaalle! Voit sulkea välilehden tai lisätä toisen kohteen.')
{
	pg_query("COMMIT");
}

else
{
	pg_query("ROLLBACK");
}
pg_close($yhteys);

?>

<html lang ="fi">
	<head>
		<meta charset="UTF-8" />
		<title>Kohteen lisääminen</title>
		<link rel="stylesheet" type="text/css" href="mystyle.css" />
	</head>
	<body>
 		<h1>
			Tmi Sähkötärsky
		</h1>
		<a id="button" href="asiakkaanlisays.php" onclick="document.location='asiakkaanlisays.php'; return false">
			<div class="button">
				Lisää uusi asiakas 
			</div>
		</a>
		<i>Lomake aukeaa samassa ikkunassa.</i>
		<br />
	    <!-- Lomake lähetetään samalle sivulle (vrt lomakkeen kutsuminen) -->
	    <form action="kohteenlisays.php" method="post">
	
	    <h2>Kohteen lisääminen tietokantaan</h2>
	
	    <?php if (isset($viesti)) echo '<p style="color:purple">'.$viesti.'</p>'; ?>
	
		<!--PHP-ohjelmassa viitataan kenttien nimiin (name) -->
		<table border="0" cellspacing="0" cellpadding="3">
		    <tr>
	    	    <td>Kohteen osoite (Katu nro postinro kaupunki)</td>
	    	    <tr></tr>
	    	    <td><input type="text" name="osoite" value="" /></td>
		    </tr>
		    <tr>
	    	    <td>Kohteen omistavan asiakkaan etu- ja sukunimi</td>
	    	    <tr></tr>
	    	    <td>
	    	    	<select name="asiakas" value=$options>
						<?php echo $options;?>
					</select>
				</td>
		    </tr>
		</table>
		<br />
	
		
		<br />
	
		<!-- hidden-kenttää käytetään varotoimena, esim. IE ei välttämättä
		 lähetä submit-tyyppisen kentön arvoja jos lomake lähetetään
		 enterin painalluksella. Tätä arvoa tarkkailemalla voidaan
		 skriptissä helposti päätellä, saavutaanko lomakkeelta. -->
	
		<input class="button" type="hidden" name="tallenna" value="jep" />
		<input class="button" type="submit" value="Lisää kohde" />
		</form>
		<footer>
			Tehnyt Henri A. Juvonen huhtikuussa 2016. Kopiointi sallittu.
		</footer>
	</body>
</html>