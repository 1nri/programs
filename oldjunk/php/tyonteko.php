<?php

/**
 * @author henrijuvonen
 * created during the spring of 2016
 *
 * modified 2.2.2020
 * translated comments, started recomposing the structure for further development
 *
 * modified 3.2.2020
 * continued translation and recomposure
 *
 */

// initiating a connection to database and inform about an error

session_start();

// REMEMBER TO GET YOUR CREDENTIALS RIGHT

$y_tiedot = "host=dbhost.name port=1234 dbname=nameofdb user=dbuser password=password";

if (!$yhteys = pg_connect($y_tiedot))
   die("Not able to connect to database server");

$options = 'test2';
$options .= '.rivi[0].';
$tulos4 = pg_query("SELECT osoite FROM kohde");
if(!$tulos4)
	$options = 'Add first object first!';
else
{
	$options ="<option>Add without object</option>";
	while ($rivi4 = pg_fetch_row($tulos4))
	{
	   	$options .= "<option>$rivi4[0]</option>";
	}
	/*
	$options = 'Add the first customer first?';
	*/
}

// isset function inspects for an input
// POST is a method for extracting data from form by calling the form
// the argument is from the name of the form "tallenna"

if (isset($_POST['tallenna']))
{

    // this function retrieves the identifier of the latest tyo record
    // the id is simultaneously incremented by one for creating a new tyo record


    $tulos = pg_query("SELECT MAX(id)+1 FROM tyo");
    settype($tyoid, "integer");

    // this tests whether a record was retrieved - if not, we're inserting the
    // first record and id will be set as one

    if (!$tulos)
    {
       	$tyoid = 1;
    }

    // if a record was retrieved and the id was incremented by one, the value
    // is used as an id of the new tyo record

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
    // character strings are protected prior to query execution

    // 3.2.2020 henrijuvonen
    // it should be considered whether to create a new module for the database operation
    //  id |    pvm     |   tyyppi    | tyotyyppi | tunnit

    $kohde = pg_escape_string($_POST['kohde']);
    $pvm = pg_escape_string($_POST['pvm']);
    $tyyppi = pg_escape_string($_POST['tyyppi']);
    $tunnit = intval($_POST['tunnit']);

    // if there's correct input in the fields, data is added to database

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

        // viesti variable is used as a flag to check for the success of operation
        // the value contains error message in case of an error
        // function pg_last_error() is used to extract the connection related error message

        if ($paivitys && (pg_affected_rows($paivitys) > 0))
        {
            $viesti = 'The task has been recorded! You may close this window or
            add other tasks.';

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
						$viesti = 'The work has been recorded for this object! You may
            close this tab or continue recording tasks to another object.';
				}

			}
        }
        else
            $viesti = 'Task was not recorded: ' . pg_last_error($yhteys);
    }
    else
        $viesti = 'Insufficient input - verify the task, please!' . pg_last_error($yhteys);

}

// close the database connection

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

    <!-- the form is sent to the same page (vs. calling the form) -->
    <form action="tyonlisays.php" method="post">

    <h2>Työn lisääminen tietokantaan</h2>

    <?php if (isset($viesti)) echo '<p style="color:purple">'.$viesti.'</p>'; ?>

	<!-- id |    pvm     |   tyyppi    | tyotyyppi | tunnit
  PHP applications always refer to the names of columns (name))
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

  <!-- hidden column is used a safety measure since e.g IE might not send values
   in submit type column when the form is sent by pressing Enter key

   by investigating this value a script can be enabled to analyse whether
   program control originates from form -->

	<input type="hidden" name="tallenna" value="jep" />
	<input type="submit" value="Lisää työ" />
	</form>
	<form action="tyonlisays.php" method="post">
		<input type="submit" name="exit" value="Nollaa istunto" />
	</form>
	<footer>Created by Henri A. Juvonen in April 2016. Copying allowed.</footer>
</body>
</html>
