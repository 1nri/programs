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

$options = 'test2';
$options .= '.rivi[0].';
$tulos4 = pg_query("SELECT osoite FROM kohde");
if(!$tulos4)
	$options = 'Lisää ensimmäinen kohde ensin!';
else
{
	$options ="<option>Lisää ilman kohdetta</option>";
	while ($rivi4 = pg_fetch_row($tulos4))
	{
	   	$options .= "<option>$rivi4[0]</option>";
	}
	/*
	$options = 'Lisää ensimmäinen asiakas ensin?';
	*/
}

/* isset funktiolla jäädään odottamaan syötettä.
POST on tapa tuoda tietoa lomaketta (tavallaan kutsutaan lomaketta).
Argumentti tallenna saadaan lomakkeen napin nimestä.
*/

if (isset($_POST['tallenna']))
{

    // tällä hankitaan viimeisimmän työn tunniste, jota lisätään yhdellä uutta työtä lisätessä

    $tulos = pg_query("SELECT MAX(id)+1 FROM tyo");
    settype($tyoid, "integer");

    // tässä testataan, palauttiko ylempi kysely mitään - jos ei, on kyseessä yrityksen
    // ensimmäinen työ ja tunniste (id) asetetaan ykköseksi
    if (!$tulos)
    {
       	$tyoid = 1;
    }

    // kyselyn palauttaessa viimeisimmän id:n lisättynä yhdellä, annetaan tulos uuden
    // työn tunnisteeksi (id)

    else
    {
    	$rivi = pg_fetch_row($tulos);
       	$tyoid = $rivi[0];
    }

    $tyotyyppi = pg_escape_string($_POST['tyypinnimi']);
    $tulos2 = pg_query("SELECT id FROM tyotyyppi WHERE nimi = '$tyotyyppi'");

    if($tulos2)
    {
	    $rivi2 = pg_fetch_row($tulos2);
    	$tyotyyppi = $rivi2[0];
    }
	// suojataan merkkijonot ennen kyselyn suorittamista
	//  id |    pvm     |   tyyppi    | tyotyyppi | tunnit

    $kohde = pg_escape_string($_POST['kohde']);
    $pvm = pg_escape_string($_POST['pvm']);
    $tyyppi = pg_escape_string($_POST['tyyppi']);
    $tunnit = intval($_POST['tunnit']);

    // jos kenttiin on syötetty jotain, lisätään tiedot kantaan

    $tiedot_ok = trim($pvm) != '' && trim($tyyppi) != '' && trim($tyotyyppi) <3 && trim($tyotyyppi) >0 && trim($tunnit) > -1;

    if ($tiedot_ok)
    {
    	if($tyyppi = 'asennus')
    	{
    		$tulos2 = pg_query("SELECT");
    	}

    	// INSERT INTO tyo(id, pvm, tyyppi, tyotyyppi, tunnit)
    	// VALUES((SELECT MAX(id) FROM tyo) + 1, '03-20-2016', 'asennus', 1, 2);

        $kysely = "INSERT INTO tyo(id, pvm, tyyppi, tyotyyppi, tunnit)
		 VALUES('$tyoid', '$pvm', '$tyyppi', '$tyotyyppi', '$tunnit')";
        $paivitys = pg_query($kysely);

        // asetetaan viesti-muuttuja lisäämisen onnistumisen mukaan
		// lisätään virheilmoitukseen myös virheen syy (pg_last_error)

        if ($paivitys && (pg_affected_rows($paivitys) > 0))
        {
            $viesti = 'Työ lisätty! Voit sulkea välilehden tai lisätä muita töitä.';

        	if(trim($kohde) != '')
			{
				$tulos3 = pg_query("SELECT id FROM kohde WHERE osoite = '$kohde'");

				if($tulos3)
				{
					$rivi3 = pg_fetch_row($tulos3);
					$kohdeid = $rivi3[0];
					$kysely3 = "INSERT INTO tyokohde(tyoid, kohdeid)
					VALUES('$tyoid','$kohdeid')";
					$paivitys3 = pg_query($kysely3);

					if ($paivitys3 && (pg_affected_rows($paivitys3) > 0))
						$viesti = 'Työ lisätty kohteeseen! Voit sulkea välilehden tai lisätä toisen kohteen.';
				}

			}
        }
        else
            $viesti = 'Työtä ei lisätty: ' . pg_last_error($yhteys);
    }
    else
        $viesti = 'Annetut tiedot puutteelliset - tarkista työ, ole hyvä!' . pg_last_error($yhteys);

}

// suljetaan tietokantayhteys

pg_close($yhteys);

?>

<html lang ="fi">
 <head>
 <meta charset="UTF-8" />
 <title>Työn lisääminen</title>
 <link rel="stylesheet" type="text/css" href="mystyle.css" />
<!--
<style>
div
{
	padding: 3px;
}
div a
{
	border: 1px solid black;
	text-decoration: none;
	color: black;
	background: lightgray;
}

a.button
{
	text-align: center;
}

p.menu
{
	border: 1px black solid
	margin: 0.5em;
}

footer
{
	font-size: 0.75em;
}
</style>
-->
 </head>
 <body>

    <!-- Lomake lähetetään samalle sivulle (vrt lomakkeen kutsuminen) -->
    <form action="tyonlisays.php" method="post">

    <h2>Työn lisääminen tietokantaan</h2>

    <?php if (isset($viesti)) echo '<p style="color:purple">'.$viesti.'</p>'; ?>

	<!-- id |    pvm     |   tyyppi    | tyotyyppi | tunnit
	PHP-ohjelmassa viitataan kenttien nimiin (name)
	-->
	<table border="0" cellspacing="0" cellpadding="3">
	    <tr>
    	    <td>Päivämäärä (kk-pp-vvvv)</td>
    	    <tr></tr>
    	    <td><input type="text" name="pvm" value="" /></td>
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
	    <tr>
    	    <td>Työkohde (ei pakollinen)</td>
    	    <tr></tr>
    	    <td>
    	    <!--<input type="text" name="asiakas" value="" /> -->
    	    	<select name="kohde" value=$options>
					<?php echo $options;?>
				</select>
			</td>
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
