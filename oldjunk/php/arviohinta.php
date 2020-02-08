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

function laske_alv($hinta, $alv = 24)
{
	$alv_osuus = $hinta * $alv/100;
	return $alv_osuus;
}

function laske_ktv($hinta, $ktv = 45)
{
	$kvahennys = ($hinta * ($ktv/100)) - 100;
	if($kvahennys > 2400)
		$kvahennys =  2400;
	if($kvahennys < 0)
		$kvahennys = 0;
	return $kvahennys;
}

// initiating a connection to database and inform about an error
session_start();
$y_tiedot = "host=dbhost.name port=1234 dbname=nameofdb user=dbuser password=password";

if (!$yhteys = pg_connect($y_tiedot))
   die("Tietokantayhteyden luominen epäonnistui.");

pg_query("BEGIN");
// List of objects for the user to select
$osoite_options = '';
$osoitetulos = pg_query("SELECT osoite FROM kohde");

// checkin that anything exists wihtin the list
if(!$osoitetulos)
	$osoite_options = 'Lisää ensimmäinen kohde ensin!';

else
{
	while ($rivi_osoitteita = pg_fetch_row($osoitetulos))
	{
	   	$osoite_options .= "<option>$rivi_osoitteita[0]</option>";
	}
}

// list of equipment for the user to select
$tarvike_otions = '';
$tarviketulos = pg_query("SELECT nimi, varastossa FROM tarvike WHERE varastossa > 0");

if(!$tarviketulos)
	$tarvike_options = 'Varasto tyhjä!!';

else
{

	while ($tarvikerivi = pg_fetch_row($tarviketulos))
	{
   		$tarvike_options .= "<option>$tarvikerivi[0]</option>";
   		$varastotila = "varastossa $tarvikerivi[1] kpl";
	}
}

// different work categories for the user to select

$laatutulos = pg_query("SELECT laatu FROM tyonlaatu");
if(!$laatutulos)
{
	$viesti = "Tietokannasta puuttuu työlaadut (asennus / suunnittelu). ";
}

else
{
	while($laaturivi = pg_fetch_row($laatutulos))
	{
		$laatu[] = $laaturivi[0];
	}
}

// sending the form
if(isset($_POST['tallenna']))
{

	$kohde = $_POST['kohde'];
	$tyyppi = $_POST['tyyppi'];

	if(empty($tyyppi))
	{
		$viesti = "Työn laatu jäi valitsematta. ";
	}

	else
	{
		$i = 0;
		while($i < count($tyyppi))
		{

			if($tyyppi[$i] == 'suunnittelu')
				$s_tuntilkm = intval($_POST['s_tunnit']);

			if($tyyppi[$i] == 'asennus')
				$a_tuntilkm = intval($_POST['a_tunnit']);

			$i++;
		}
	}

	$kohdeid_kysely = pg_query("SELECT id FROM kohde WHERE osoite = '$kohde'");
	if($kohdeid_kysely)
	{
		while($tulosrivi = pg_fetch_row($kohdeid_kysely))
			$kohdeid = $tulosrivi[0];

	}

	else
		$viesti = 'Annetut kohdetiedot puutteelliset - tarkista, ole hyvä! '. pg_last_error($yhteys);

	// array structure for the equipment
	foreach($_POST['tarvike'] as $value)
		$array_tarv[] = strval($value);

	// array structure for the quantity of each equipment
	foreach($_POST['lkm'] as $kpl)
		$array_lkm[] = intval($kpl);

	// total count of equipment
	for ($i = 0; $i < count($array_tarv); $i++)
	{
		$j = 0;

		while($j < $array_lkm[$i])
		{

			if($kohdeid != '' && $tyyppi[0] != '' && $laatu[0] != '' && $array_tarv[$i] != ''
			&& $array_lkm[$i] != '')
			{
				$hinta_kysely = pg_query("SELECT hinta FROM tarvike WHERE nimi = '$array_tarv[$i]'");

				if($hinta_kysely)
				{

					$hintatulos = pg_fetch_row($hinta_kysely);
					$verottomat_hinnat[] = $hintatulos[0];
					$verolliset_hinnat[] = $hintatulos[0] + laske_alv($hintatulos[0]);
				}

			}

			else
			{

				if($kohdeid == '')
					$viesti = 'Virhe kohteen valinnassa. ';

				if($tyyppi[0] == '')
					$viesti = 'Virhe työn laadun valinnassa. ';

				if($laatu == '')
					$viesti = 'Virhe laskutusperusteen valinnassa. ';

				if($array_tarv[0] == '')
					$viesti = 'Virhe tarvikkeiden valinnassa. ';

				if($array_lkm[$i] == '')
					$viesti = 'Virhe tarvikkeiden lukumäärässä?';

				$viesti .= pg_last_error($yhteys);
			}
			$j = $j + 1;
		}
	}

	$h = 0;
	$tunnityht = 0;
	$tuntihinnat = 0;

	if($a_tuntilkm > 0)
		$tunnityht = $tunnityht + $a_tuntilkm;

	if($s_tuntilkm > 0)
		$tunnityht = $tunnityht + $s_tuntilkm;

	while($h < $tunnityht)
	{
		$tyotyyppi = $_POST['tyypinnimi'];
		$tyon_hinta_kysely = pg_query("SELECT hinta FROM tyotyyppi WHERE nimi = '$tyotyyppi'");

		if($tyon_hinta_kysely)
		{
			$tyonhintatulos = pg_fetch_row($tyon_hinta_kysely);
			$verottomat_hinnat[] = $tyonhintatulos[0];
			$verolliset_hinnat[] = $tyonhintatulos[0];
			$tuntihinnat = $tuntihinnat + $tyonhintatulos[0];
		}

		$h = $h + 1;
	}

	$hinta_yht_veroton = 0;
	$hinta_yht_verollinen = 0;

	foreach($verottomat_hinnat as $value)
		$hinta_yht_veroton = $hinta_yht_veroton + $value;

	foreach($verolliset_hinnat as $value)
		$hinta_yht_verollinen = $hinta_yht_verollinen + $value;

	$viesti = "Arvioitu hinta työlle kohteessa $kohde ($tunnityht tuntia) tarvikkeineen ";
	$viesti .= $hinta_yht_veroton . " euroa ennen veroja. <br />";
	$viesti .= "Veroineen (yleinen alv. 24%) arvioitu hinta on $hinta_yht_verollinen. <br />";
	$viesti .= "Arvio sisältää seuraavat työt: <br />";

	foreach($tyyppi as $value)
	{
		if($value == 'suunnittelu')
		{
			$viesti .= "$value, $s_tuntilkm tuntia <br />";
		}
		if($tyyppi[$i] == 'asennus')
			$viesti .= "$value, $a_tuntilkm tuntia <br />";;
	}

	$viesti .= "Arvio sisältää seuraavat tarvikkeet: <br />";

	foreach($array_tarv as $key => $value)
	{
		if($array_lkm[$key] > 0)
			$viesti .= "$value, $array_lkm[$key] kpl <br />";
	}

	$vahennysarvio = round(laske_ktv($tuntihinnat), 2);
	$viesti .= "Työstä on mahdollisuus saada kotitalousvähennystä $vahennysarvio euroa.";
	pg_query("COMMIT");
}

?>


<html lang ="fi">
<head>
	<meta charset="UTF-8" />
	<title>Hinta-arvion tekeminen</title>
	<link rel="stylesheet" type="text/css" href="mystyle.css" />
	<script language="javascript">
        function addRow(tableID)
        {

            var table = document.getElementById(tableID);
            var rowCount = table.rows.length;
            var row = table.insertRow(rowCount);

			/*
			This will be implemented with the piece of code below
			<td><input type="checkbox" name="chkbox[]"/></td>
			*/
            var cell1 = row.insertCell(0);
            cell1.id = "check";
            var element1 = document.createElement("input");
            element1.id = "check";
            element1.type = "checkbox";
            element1.name="chkbox[]";
            cell1.appendChild(element1);

			/*
            This will hopefully be implemented with the piece of code below
            <select name="tarvike[]" value=$options2>
				<?php echo $options2;?>
			</select>
			*/
            var cell2 = row.insertCell(1);
            var element2 = document.createElement("select");
            element2.name = "tarvike[]";
            element2.innerHTML = "<?php echo $tarvike_options;?>";
            cell2.appendChild(element2);

			/*
			This will be implemented with the piece of code below
			<input type="text" name="lkm" value="lukumäärä" />
			*/
			var cell3 = row.insertCell(2);
			cell3.id = "kpl";
            var element3 = document.createElement("input");
            element3.id = "kpl";
            element3.type = "text";
            element3.name = "lkm[]";
            element3.innerHTML = "kappaletta käytetty";
            cell3.appendChild(element3);

        }

        function deleteRow(tableID)
        {

            try
            {
           		var table = document.getElementById(tableID);
	            var rowCount = table.rows.length;

	            for(var i=0; i<rowCount; i++)
	            {
    	            var row = table.rows[i];
        	        var chkbox = row.cells[0].childNodes[0];

            	    if(null != chkbox && true == chkbox.checked)
            	    {
                	    table.deleteRow(i);
	                    rowCount--;
    	                i--;
        	        }
            	}
            }

            catch(e)
            {
                alert(e);
            }
		}

	</script>
</head>
<body>
	<div class="content">
	<h1>
		Tmi Sähkötärsky
	</h1>
	<!-- Form is sent to the same page (compare to calling the form) -->
	<form class="box1" action="arviohinta.php" method="post">
    <h2>
    	Työn hinta-arvio
    </h2>

    <?php if (isset($viesti)) echo '<p style="color:purple">'.$viesti.'</p>'; ?>

	<!-- PHP application refer to column names (name) -->
	<table border="0" cellspacing="0" cellpadding="3">
		<tr>
    	    <td>Työkohde</td>
    	    <tr />
    	    <td>
    	    	<!-- This produces a listing of all object(s) owned by a
				customer -->
    	    	<select name="kohde" value=$osoite_options>
					<?php echo $osoite_options;?>
				</select>
			</td>
	    </tr>
	    <tr>
    	    <td>
    	    	Työn laatu
    	    </td>
    	</tr>
    	<tr>
    	    <td>
    	    	<input id="check" class="check" type="checkbox" name="tyyppi[]" value="suunnittelu">
	    	    	<?php echo $laatu[0]; ?>
				</input>
				<td>
	    			Tuntien määrä
	    		</td>
	    		<td>
	    			<input type="text" name="s_tunnit" value="0" />
	    		</td>
			</td>
		</tr>
		<tr>
			<td>
				<input id="check" class="check" type="checkbox" name="tyyppi[]" value="asennus">
					<?php echo $laatu[1]; ?>
				</input>
				<td>
	    			Tuntien määrä
	    		</td>
	    		<td>
	    			<input type="text" name="a_tunnit" value="0" />
		    	</td>
			</td>
	    </tr>
	    <tr>
    	    <td>Laskutusperuste</td>
    	    <td>
    	    	<input class="radio" type="radio" name="tyypinnimi" value="tunti" checked>
    	    	Tunti
    	    	</input>
    	    	<br />
				<input class="radio" type="radio" name="tyypinnimi" value="urakka">
				Urakka
				</input>
			</td>
	    </tr>
	</table>
	<!-- dynamic listing of equipment -->
	<table id="dataTable" border="0" cellspacing="0" cellpadding="3">
        <tr>
        	<td>Varastossa olevia tarvikkeita</td>
        </tr>
        <tr>
			<td>
				<input id="check" type="checkbox" name="chkbox[]"/>
			</td>
			<td>
				<!-- This produces a listing of all equipment stored in the
				database -->
				<select name="tarvike[]" value=$tarvike_options>
					<?php echo $tarvike_options; ?>
				</select>
			</td>
			<td id="kpl">
				<input id="kpl" type="text" name="lkm[]" value="kappaletta käytetty" />
			</td>
		</tr>
    </table>
    <p font-size=0.6em>Valitse tarvikerivi vain kun haluat poistaa sen.</p>
    <!-- Button for adding rows -->
 	<input class="button" type="button" value="Lisää rivi" onClick="addRow('dataTable')" />
 	<!-- Button for removing rows -->
 	<input class="button" type="button" value="Poista valitsemasi rivi(t)"
 	onClick="deleteRow('dataTable')" />
	<br />

	<!--
	hidden column is used a safety measure since e.g IE might not send values
   in submit type column when the form is sent by pressing Enter key

   by investigating this value a script can be enabled to analyse whether
   program control originates from form
	-->
	<br />
	<input class="button" type="hidden" name="tallenna" value="jep" />
	<input class="button" type="submit" value="Tee hinta-arvio"/>
	</form>

	<footer>Created by Henri A. Juvonen in April 2016. Copying allowed.</footer>
	</div>
</body>
</html>
