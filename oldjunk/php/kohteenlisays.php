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
 */

// initiating a connection to database and inform about an error
// REMEMBER TO GET YOUR CREDENTIALS RIGHT

$y_tiedot = "host=dbhost.name port=1234 dbname=nameofdb user=dbuser password=password";

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

// isset function inspects for an input
// POST is a method for extracting data from form by calling the form
// the argument is from the name of the form "tallenna"
if (isset($_POST['tallenna']))
{

    // this will acquire the latest value for kohdeid which will be appended
    // with on as we are preparing for inserting a new record
    $tulos = pg_query("SELECT MAX(id)+1 FROM kohde");
    settype($kohdeid, "integer");

    // this tests whether the above query returned a value - in case not, the
    // record to be inserted is the first record and this value should be set
    // accordingly
    if (!$tulos)
    {
       	$kohdeid = 1;
    }

    // if the above query returns tha latest id appended with one, the result
    // will be used as the id for this record to be inserted
    else
    {
    	$rivi = pg_fetch_row($tulos);
       	$kohdeid = $rivi[0];
    }

	// character strings are protected prior to query execution
    $osoite = pg_escape_string($_POST['osoite']);
    $asiakas = pg_escape_string($_POST['asiakas']);

    // if there's correct input in the fields, data is added to database
    $tiedot_ok = trim($osoite) != '' && $asiakas != '';

    if ($tiedot_ok)
    {
        $kysely = "INSERT INTO kohde(id, osoite)
		 VALUES('$kohdeid', '$osoite')";
        $paivitys = pg_query($kysely);

        // viesti variable is used as a flag to check for the success of operation
        // the value contains error message in case of an error
        // function pg_last_error() is used to extract the connection related error message

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
        // an error is provided due to unsuccessful execution
        else
            $viesti = 'Kohdetta ei lisätty: ' . pg_last_error($yhteys);
    }
    // an error is provided due to incorrect input
    else
        $viesti = 'Annetut tiedot puutteelliset - tarkista, ole hyvä! '. pg_last_error($yhteys);

}

// database connection is closed
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
	    <!-- the form is sent to the same page (vs. calling the form) -->
	    <form action="kohteenlisays.php" method="post">

	    <h2>Kohteen lisääminen tietokantaan</h2>

	    <?php if (isset($viesti)) echo '<p style="color:purple">'.$viesti.'</p>'; ?>

		<!-- PHP applications always refer to the names of columns (name) -->
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

		<!-- hidden column is used a safety measure since e.g IE might not send values
     in submit type column when the form is sent by pressing Enter key

     by investigating this value a script can be enabled to analyse whether
     program control originates from form -->

		<input class="button" type="hidden" name="tallenna" value="jep" />
		<input class="button" type="submit" value="Lisää kohde" />
		</form>
		<footer>Created by Henri A. Juvonen in April 2016. Copying allowed.</footer>
	</body>
</html>
