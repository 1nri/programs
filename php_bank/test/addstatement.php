<?php

/**
* @author henrijuvonen
* created in 2020
*
* modified from another piece of code on 8.2.2020
* started recomposing the structure for further development
*/

// initiating a connection to database and inform about an error
// REMEMBER TO GET YOUR CREDENTIALS RIGHT
$y_tiedot = "host=dbhost.name port=1234 dbname=nameofdb user=dbuser password=password";

if (!$yhteys = pg_connect($y_tiedot))
   die("Connection unsuccessful, could not connect to database.");

// sending the form
if (isset($_POST['tallenna']))
{
	pg_query("BEGIN");
   	// this acquires the latest asiakasid which will be appended with one while
   	// inserting a new customer
    $tulos = pg_query("SELECT MAX(id)+1 FROM asiakas");
    settype($asiakasid, "integer");

    // this tests did the earlier query return anything
    // if not, this is the first customer of the company and id is set to one
   	if (!$tulos)
   	{
   	   	$asiakasid = 1;
   	}

   	// in case the query did return a value appended with one,
    // the result will be used as an id for the new customer to be inserted
   	else
   	{
   		$rivi = pg_fetch_row($tulos);
   	   	$asiakasid = $rivi[0];
   	}

	// strings of char will be protected prior to query execution
   	$nimi = pg_escape_string($_POST['nimi']);
   	$kanta = pg_escape_string($_POST['kanta_asiakas']);
   	$tili = pg_escape_string($_POST['tilinumero']);
   	$puh = pg_escape_string($_POST['puhelinnumero']);

    // if the input is correct, the data will inserted to database
    $tiedot_ok = trim($nimi) != '' && trim($kanta) != '' && trim($tili) != '' &&
    trim($puh) != '';

    if ($tiedot_ok)
    {
    	// inserting a customer
        $kysely = "INSERT INTO asiakas(id, nimi, kanta_asiakas, tilinumero, puhelinnumero)
        VALUES('$asiakasid', '$nimi', '$kanta', '$tili', '$puh')";
        $paivitys = pg_query($kysely);

        // viesti variable is used as a flag to check for the success of operation
        // the value contains error message in case of an error
        // function pg_last_error() is used to extract the connection related error message
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

// database connection is closed
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
	    <!-- the form is sent to the same page (vs. calling the form) -->
	    <form action="asiakkaanlisays.php" method="post">

	    	<h2>
	    		Asiakkaan lisääminen tietokantaan
	    	</h2>

		    <?php if (isset($viesti)) echo '<p style="color:purple">'.$viesti.'</p>'; ?>

			<!-- PHP applications always refer to the names of columns (name) -->
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

			<!-- hidden column is used a safety measure since e.g IE might not send values
       in submit type column when the form is sent by pressing Enter key
       by investigating this value a script can be enabled to analyse whether
       program control originates from form -->

			<input type="hidden" name="tallenna" value="jep" />
			<input type="submit" value="Lisää asiakas" />
		</form>
		<footer>
			Tehnyt Henri A. Juvonen huhtikuussa 2016. Kopiointi sallittu.
		</footer>
	</body>
</html>
