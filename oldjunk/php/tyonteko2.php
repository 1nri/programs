<?php

/**
 * @author henrijuvonen
 * created during the spring of 2016
 *
 * modified 2.2.2020
 * translated comments, started recomposing the structure for further development
 */

// luodaan tietokantayhteys ja ilmoitetaan mahdollisesta virheestä
session_start();
$y_tiedot = "host=dbhost.name port=1234 dbname=nameofdb user=dbuser password=password";

if (!$yhteys = pg_connect($y_tiedot))
   die("Tietokantayhteyden luominen epäonnistui.");

$_SESSION['kohde'] = '';
$_SESSION['kohdeid'] = '';
$_SESSION['tyoid'] = '';
$options = '';
$options2 = 'Valitse kohde ensin.';
$tulos = pg_query("SELECT osoite FROM kohde");
if(!$tulos)
	$options = 'Lisää ensimmäinen kohde ensin!';
else
{
	//$options ="<option>Lisää ilman kohdetta</option>";
	while ($rivi = pg_fetch_row($tulos))
	{
	   	$options .= "<option>$rivi[0]</option>";
	}
}

if (isset($_POST['tallenna']))
{
	$_SESSION['kohde'] = pg_escape_string($_POST['kohde']);

	// kohde tulee olla valittu
	if($_SESSION['kohde'] != '')
	{
		$tulos2 = pg_query("SELECT id FROM kohde WHERE osoite = '$_SESSION['kohde']'");
		//$tulos2 = pg_query("SELECT pvm FROM kohde WHERE osoite = '$_SESSION['kohde']'");

		if(!$tulos2)
		{
			echo 'kohdetta ei löytynyt?';
			$options2 = 'Lisää työ kohteeseen.';
		}
		else
		{
			$rivi2 = pg_fetch_row($tulos2);
			$_SESSION['kohdeid'] = $rivi2[0];
			$tulos3 = pg_query("SELECT pvm FROM tyo, tyokohde WHERE kohdeid =
			$_SESSION['kohdeid'] AND tyoid = tyo.id");
			$options2 = '';

			// päivämäärä tulee olla valittu
			if($_SESSION['pvm'] != '')
			{
				// listataan päivämäärät, joina kohteessa on työskennelty
				while($rivi3 = pg_fetch_row($tulos3))
				{
					$options2 .= "<option>$rivi3[0]</option>";
				}
		}
	}

	$_SESSION['pvm'] = pg_escape_string($_POST['pvm']);

	if($_SESSION['pvm'] != '')
	{
		$tulos4 = pg_query("SELECT id FROM tyo WHERE pvm = '$_SESSION['pvm']'");

		if(!$tulos4)
		{
			echo 'työtä ei löytynyt?'
			//$options3 = 'Lisää työ kohteeseen.';
		}
		else
		{
			$rivi4 = pg_fetch_row($tulos4);
			$_SESSION['tyoid'] = $rivi4[0];
		}
	}

	if($_SESSION['tyoid'] != '')
	{
		/*
		työtyyppi(urakka/tunti)
		työn tyyppi(asennus/suunnittelu)
		tarvikkeet
		*/
	}
}

pg_close($yhteys);
?>

<html lang ="fi">
<head>
	<meta charset="UTF-8" />
	<title>Tehdyn työn kirjaus</title>
	<link rel="stylesheet" type="text/css" href="mystyle.css" />
</head>
<body>
	<h1>
		Tmi Sähkötärsky
	</h1>
    <!-- Lomake lähetetään samalle sivulle (vrt lomakkeen kutsuminen) -->
    <form action="tyonlisays.php" method="post">

    <h2>Työn lisääminen tietokantaan</h2>

    <?php if (isset($viesti)) echo '<p style="color:purple">'.$viesti.'</p>'; ?>

	<!-- id |    pvm     |   tyyppi    | tyotyyppi | tunnit
	PHP-ohjelmassa viitataan kenttien nimiin (name)
		listaus kohteista
		listaus kohteessa tehtyjen töiden päivämääristä
		yksittäinen työ tiettynä päivämääränä
		työtyyppi(urakka/tunti)
		työn tyyppi(asennus/suunnittelu)
		tarvikkeet

	-->
	<table border="0" cellspacing="0" cellpadding="3">
	    <tr>
    	    <td>Kohteet</td>
    	    <tr></tr>
    	    <td>
    	    <!--<input type="text" name="asiakas" value="" /> -->
    	    	<select name="kohde" value=$options>
					<?php echo $options;?>
				</select>
			</td>
	    </tr>
		<tr>
    	    <td>Päivämäärät, joina työtä on kohteessa tehty</td>
    	    <tr></tr>
    	    <td>
    	    <!--<input type="text" name="asiakas" value="" /> -->
    	    	<select name="pvm" value=$options2>
					<?php echo $options2;?>
				</select>
			</td>
	    </tr>
	    <tr>
    	    <td>Työn laatu</td>
    	    <tr></tr>
    	    <td>
    	    	<input type="radio" name="tyyppi" value="asennus" checked> Asennus<br>
				<input type="radio" name="tyyppi" value="suunnittelu"> Suunnittelu<br>
			</td>
	    </tr>
	    <tr>
    	    <td>Laskutusperuste</td>
    	    <tr></tr>
    	    <td>
    	    	<input type="radio" name="tyypinnimi" value="tunti" checked> Tunti<br>
				<input type="radio" name="tyypinnimi" value="urakka"> Urakka<br>
			</td>
	    </tr>
	    <tr>
	    	<td>Tuntien määrä (ei pakollinen, jos urakka; hyödyllinen jos tunti)</td>
	    	<tr></tr>
	    	<td><input type="text" name="tunnit" value="" /></td>
	    </tr>
	</table>

	<br />
	<div>
	<a class="button" href="kohteenlisays.php" onclick="document.location='kohteenlisays.php'; return false">Lisää uusi kohde</a>
	</div>
	Lomake aukeaa samassa ikkunassa.
	<br />
	<br />

	<!-- hidden-kenttää käytetään varotoimena, esim. IE ei välttämättä
	 lähetä submit-tyyppisen kentön arvoja jos lomake lähetetään
	 enterin painalluksella. Tätä arvoa tarkkailemalla voidaan
	 skriptissä helposti päätellä, saavutaanko lomakkeelta. -->

	<input type="hidden" name="tallenna" value="jep" />
	<input type="submit" value="Lisää työ" />
	</form>
	<form action="tyonlisays.php" method="post">
		<input type="submit" name="exit" value="Nollaa istunto" />
	</form>
	<footer>Created by Henri A. Juvonen in April 2016. Copying allowed.</footer>
</body>
</html>
