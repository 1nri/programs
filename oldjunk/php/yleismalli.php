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
 * modified 7.2. & 8.2.
 * translated further
 *
 */

// initiating a connection to database and inform about an error

session_start();

// REMEMBER TO GET YOUR CREDENTIALS RIGHT

$y_tiedot = "host=dbhost.name port=1234 dbname=nameofdb user=dbuser password=password";

if (!$yhteys = pg_connect($y_tiedot))
   die("Not able to connect to database server");

// isset function inspects for an input
// POST is a method for extracting data from form by calling the form
// the argument is from the name of the form "tallenna"

if (isset($_POST['tallenna']))
{
    // character strings are protected prior to query execution

    // 2.2.2020 henrijuvonen
    // it should be considered whether to create a new module for the database operation
    $kurssinro = intval($_POST['kurssinro']);
    $etunimi_o = pg_escape_string($_POST['etunimi']);
    $sukunimi_o = pg_escape_string($_POST['sukunimi']);
    $k_nimi = pg_escape_string($_POST['kurssi']);
    $o_pisteet = intval($_POST['pisteet']);

    // if there's correct input in the fields, data is added to database

    $tiedot_ok = $kurssinro != 0 && trim($etunimi_o) != '' && trim($sukunimi_o) != '' && trim($k_nimi) != '' && $o_pisteet != '';

    if ($tiedot_ok)
    {
        $kysely = "INSERT INTO yliopisto.kurssi(knro, etunimi_o, sukunimi_o, k_nimi, o_pisteet)
		 VALUES($kurssinro, '$etunimi_o', '$sukunimi_o', '$k_nimi', '$o_pisteet')";
        $paivitys = pg_query($kysely);

        // viesti variable is used as a flag to check for the success of operation
        // the value contains error message in case of an error
        // function pg_last_error() is used to extract the connection related error message

        if ($paivitys && (pg_affected_rows($paivitys) > 0))
            $viesti = 'Kurssi lisätty!';
        else
            $viesti = 'Kurssia ei lisätty: ' . pg_last_error($yhteys);
    }

    // an error is provided due to incorrect input

    else
        $viesti = 'Insufficient input - verify the task, please!';

}

// database connection is closed

pg_close($yhteys);

?>

<html>
 <head>
  <title>Yleismalli</title>
 </head>
 <body>

    <!-- the form is sent to the same page (vs. calling the form) -->
    <form action="yleismalli.php" method="post">

    <h2>Adding a course to database</h2>

    <?php if (isset($viesti)) echo '<p style="color:red">'.$viesti.'</p>'; ?>

	<!-- PHP applications always refer to the names of columns (name) -->
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

	<!-- hidden column is used a safety measure since e.g IE might not send values
   in submit type column when the form is sent by pressing Enter key

   by investigating this value a script can be enabled to analyse whether
   program control originates from form -->

	<input type="hidden" name="tallenna" value="jep" />
	<input type="submit" value="Lisää kurssi" />
	</form>
  <footer>Created by Henri A. Juvonen in April 2016. Copying allowed.</footer>
</body>
</html>
