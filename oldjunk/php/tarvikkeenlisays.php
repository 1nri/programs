<?php

/**
 * @author henrijuvonen
 * created during the spring of 2016
 *
 * modified 2.2.2020
 * translated comments, started recomposing the structure for further development
 *
 * modified 7.2. & 8.2.
 * translated further
 *
 */

// initiating a connection to database and inform about an error
// REMEMBER TO GET YOUR CREDENTIALS RIGHT

session_start();
$y_tiedot = "host=dbhost.name port=1234 dbname=nameofdb user=dbuser password=password";

if (!$yhteys = pg_connect($y_tiedot))
   die("Tietokantayhteyden luominen epäonnistui.");

   // isset function inspects for an input
   // POST is a method for extracting data from form by calling the form
   // the argument is from the name of the form "tallenna"

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

    // character strings are protected prior to query execution
    // id |    nimi    | hinta | veroprosentti | varastossa
    $nimi = pg_escape_string($_POST['nimi']);
    $hinta = intval($_POST['hinta']);
    $veroprosentti = intval($_POST['vero']);
    $varastossa = intval($_POST['varasto']);

    // if there's correct input in the fields, data is added to database

    $tiedot_ok = trim($nimi) != '' && $hinta >= 0 && $veroprosentti >= 0 && $varastossa >= 0;

    if ($tiedot_ok)
    {
        $kysely = "INSERT INTO tarvike(id, nimi, hinta, veroprosentti, varastossa)
        VALUES('$tarvikeid', '$nimi', '$hinta', '$veroprosentti', '$varastossa');";
        $paivitys = pg_query($kysely);

        // viesti variable is used as a flag to check for the success of operation
        // the value contains error message in case of an error
        // function pg_last_error() is used to extract the connection related error message
        if ($paivitys && (pg_affected_rows($paivitys) > 0))
            $viesti = 'Tarvike lisätty varastoon! Voit sulkea välilehden tai lisätä toisen tarvikkeen.';
            // an error is provided due to unsuccessful execution
        else
            $viesti = 'Tarviketta ei lisätty: ' . pg_last_error($yhteys);
    }
    // an error is provided due to incorrect input
    else
        $viesti = 'Annetut tiedot puutteelliset - tarkista, ole hyvä!';

}

if($viesti == 'Tarvike lisätty varastoon! Voit sulkea välilehden tai lisätä toisen tarvikkeen.')
	pg_query("COMMIT");
else
	pg_query("ROLLBACK");
// database connection is closed

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
    	<!-- the form is sent to the same page (vs. calling the form) -->
    	<form action="tarvikkeenlisays.php" method="post">
        <h2>Tarvikkeen lisääminen tietokantaan</h2>

    	<?php if (isset($viesti)) echo '<p style="color:purple">'.$viesti.'</p>'; ?>

		<!-- PHP applications always refer to the names of columns (name) -->
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

		<!-- hidden column is used a safety measure since e.g IE might not send values
     in submit type column when the form is sent by pressing Enter key

     by investigating this value a script can be enabled to analyse whether
     program control originates from form -->

		<input type="hidden" name="tallenna" value="jep" />
		<input type="submit" value="Lisää tarvike" />
		</form>
		<footer>Created by Henri A. Juvonen in April 2016. Copying allowed.</footer>
	</body>
</html>
