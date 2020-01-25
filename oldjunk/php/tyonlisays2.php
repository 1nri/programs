<?php

// luodaan tietokantayhteys ja ilmoitetaan mahdollisesta virheestä

session_start();
$y_tiedot = "host=dbstud.sis.uta.fi port=5432 dbname=tiko2016db26 user=a531883 
password=_4ntiquE!";

if (!$yhteys = pg_connect($y_tiedot))
   die("Tietokantayhteyden luominen epäonnistui.");
else
{
	pg_query("BEGIN");
	$nicestop = 'no';
	$options = '';
	$options2 = '';
	$tulos4 = pg_query("SELECT osoite FROM kohde");
	
	// Listaus kohteista
	if(!$tulos4)
		$options = 'Lisää ensimmäinen kohde ensin!';
	else
	{
		while ($rivi4 = pg_fetch_row($tulos4))
		{
		   	$options .= "<option>$rivi4[0]</option>";
		}
	}
	
	// Listaus työn laadusta (tunti/urakka)
	$tulos6 = pg_query("SELECT laatu FROM tyonlaatu");
	if($tulos6)
	{
		
		while($rivi6 = pg_fetch_row($tulos6))
		{
			$laatu[] = $rivi6[0];
		}
	}

	// Listaus varaston tarvikkeista
	$tulos5 = pg_query("SELECT nimi, varastossa FROM tarvike WHERE varastossa > 0");
	if(!$tulos5)
		$options2 = 'Lisää ensimmäinen tarvike ensin!';
	else
	{
		while ($rivi5 = pg_fetch_row($tulos5))
		{
	   		$options2 .= "<option>$rivi5[0]</option>";
	   		$varasto = "varastossa $rivi5[1] kpl";
		}
	}
	
	// isset funktiolla jäädään odottamaan syötettä.
	// POST on tapa tuoda tietoa lomaketta (tavallaan kutsutaan lomaketta).
	// Argumentti tallenna saadaan lomakkeen napin nimestä.
	if (isset($_POST['tallenna']))
	{
    
	    // tällä hankitaan viimeisimmän työn tunniste, jota kasvatetaan yhdellä uutta työtä 
	    // lisätessä
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
    	
    	// tuntityö / urakka
	    if($tulos2)
    	{
		    $rivi2 = pg_fetch_row($tulos2);
    		$tyotyyppi = $rivi2[0];
    	}
		
		// suojataan merkkijonot ennen kyselyn suorittamista
	    $kohde = pg_escape_string($_POST['kohde']);
	    $pvm = pg_escape_string($_POST['pvm']);
	    $tyyppi = pg_escape_string($_POST['tyyppi']);
	    $tunnit = intval($_POST['tunnit']);
	
	    // jos kenttiin on syötetty jotain, lisätään tiedot kantaan
	
	    $tiedot_ok = trim($pvm) != '' && trim($tyyppi) != '' && trim($tyotyyppi) <3 && 
	    trim($tyotyyppi) >0 && trim($tunnit) > -1;	
	
	    if ($tiedot_ok)
	    {
    		
    		$tulos7 = pg_query("SELECT id FROM tyonlaatu WHERE laatu = '$tyyppi'");
    		
    		if($tulos7)
    		{
    			$rivi7 = pg_fetch_row($tulos7);
    			$tyyppi = $rivi7[0];
    		
    	   		$kysely = "INSERT INTO tyo(id, pvm, tyotyyppi, tunnit, tyyppi)
				 VALUES('$tyoid', '$pvm', '$tyotyyppi', '$tunnit', '$tyyppi')";
    	    	$paivitys = pg_query($kysely);

		        // asetetaan viesti-muuttuja lisäämisen onnistumisen mukaan
				// lisätään virheilmoitukseen myös virheen syy (pg_last_error)
				if ($paivitys && (pg_affected_rows($paivitys) > 0))
				{
		        	
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
							{
								$viesti = 'Työ lisätty kohteeseen! Voit sulkea välilehden tai 
								lisätä uuden työn.';
								
								
								foreach($_POST['tarvike'] as $value)
								{
									$array_tarv[] = strval($value);
								}
								foreach($_POST['lkm'] as $kpl)
								{
									$array_lkm[] = intval($kpl);
								}
							
								// tarvikkeiden määrä
								for ($i = 0; $i < count($array_tarv); $i++)
								{
									$j = 0;

	    							while($j < $array_lkm[$i])
									{
										$paivitys4 = pg_query("INSERT INTO kaytetyttarvikkeet(tyoid, 
										tarvikeid) VALUES('$tyoid', (SELECT id FROM tarvike WHERE 
										nimi ='$array_tarv[0]' AND varastossa > 0))");
										
										if($paivitys4 && pg_affected_rows($paivitys4) > 0)
										{
											$j = $j+1;
											$nicestop = 'yes';
											$viesti = 'Työ lisätty tarvikkeineen kohteeseen! Voit sulkea 
											välilehden tai lisätä uuden työn.';
										}
										$j = $j + 1;
									}
								}
							}
						}
					}
				}
        		else
	        	    $viesti = 'Työtä ei lisätty: ' . pg_last_error($yhteys);
	    	}
	    }
	    else
	        $viesti = 'Annetut tiedot puutteelliset - tarkista työ, ole hyvä!' . pg_last_error($yhteys);
	}
}
if($nicestop == 'yes')
	pg_query("COMMIT");
else
	pg_query("ROLLBACK");
	
// suljetaan tietokantayhteys
pg_close($yhteys);
?>

<html lang ="fi">
	<head>
		<meta charset="UTF-8" />
		<title>Työn lisääminen</title>
		<link rel="stylesheet" type="text/css" href="mystyle.css" />
		<script language="javascript">
        	function addRow(tableID)
        	{

				var table = document.getElementById(tableID);
				var rowCount = table.rows.length;
	            var row = table.insertRow(rowCount);
	
				/*
				Tämä luodaan alla olevalla koodilla.
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
	            Tämä toivottavasti luodaan alla olevalla koodilla
	            <select name="tarvike[]" value=$options2>
					<?php echo $options2;?>
				</select>
				*/

				var cell2 = row.insertCell(1);
				var element2 = document.createElement("select");
				element2.name = "tarvike[]";
	            element2.innerHTML = "<?php echo $options2;?>";
	            cell2.appendChild(element2);
			
				/*
				Tämä luodaan alla olevalla koodilla.
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
				try {
	           		var table = document.getElementById(tableID);
		            var rowCount = table.rows.length;

		            for(var i=0; i<rowCount; i++) {
	    	            var row = table.rows[i];
	        	        var chkbox = row.cells[0].childNodes[0];
	            	    if(null != chkbox && true == chkbox.checked) {
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
		<h1>
			Tmi Sähkötärsky
		</h1>
		<a id="button" href="kohteenlisays.php" onclick="document.location='kohteenlisays.php'; return false">
			<div class="button">
				Lisää uusi kohde 
			</div>
		</a>
		<i>Lomake aukeaa samassa ikkunassa.</i>
		<br />
	    <!-- Lomake lähetetään samalle sivulle (vrt lomakkeen kutsuminen) -->
	    <form action="tyonlisays2.php" method="post">
	
	    <h2>Työn lisääminen tietokantaan</h2>
	
	    <?php if (isset($viesti)) echo '<p style="color:purple">'.$viesti.'</p>'; ?>

		<!-- PHP-ohjelmassa viitataan kenttien nimiin (name) -->
		<table border="0" cellspacing="0" cellpadding="3">
	    	<tr>
    	    	<td>
    	    		Päivämäärä (kk-pp-vvvv)
				</td>
				<td>
					<input type="text" name="pvm" value="" />
				</td>
		    </tr>
		    <tr>
	    	    <td>
	    	    	Työn laatu
	    	    </td>
	    	    <td>
	    	    	<input class="radio" type="radio" name="tyyppi" value="suunnittelu" checked>
		    	    	<?php echo $laatu[0]; ?>
					</input>
					<br />
					<input class="radio" type="radio" name="tyyppi" value="asennus">
						<?php echo $laatu[1]; ?>
					</input>
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
		    <tr>
		    	<td>
		    		Tuntien määrä
		    	</td>
		    	<td>
		    		<input type="text" name="tunnit" value="" />
		    	</td>
		    </tr>
		    <tr>
	    	    <td>Työkohde</td>
	    	    <tr />
	    	    <td>
	    	    	<select name="kohde" value=$options>
						<?php echo $options;?>
					</select>
				</td>
		    </tr>
		</table>
		<br />
	    <table id="dataTable" border="0" cellspacing="0" cellpadding="3">
	        <tr>
				<td id="check">
					<input id="check" type="checkbox" name="chkbox[]"/>
				</td>
				<td>
					<select name="tarvike[]" value=$options2>
						<?php echo $options2; ?>
					</select>
				</td>
				<td id="kpl">
					<input id="kpl" type="text" name="lkm[]" value="kappaletta käytetty" />
				</td>
			</tr>
    	</table>
	
		<p font-size=0.6em>Valitse rivi vain kun haluat poistaa sen.</p>	
	 	<input class="button" type="button" value="Lisää rivi" onClick="addRow('dataTable')" />
	 	<input class="button" type="button" value="Poista valitsemasi rivi(t)" 
 		onClick="deleteRow('dataTable')" />

		<br />
		
		<br />
	
		<!-- 
		hidden-kenttää käytetään varotoimena, esim. IE ei välttämättä
		lähetä submit-tyyppisen kentön arvoja jos lomake lähetetään
		enterin painalluksella. Tätä arvoa tarkkailemalla voidaan
		skriptissä helposti päätellä, saavutaanko lomakkeelta. 
		-->
	
		<input class="button" type="hidden" name="tallenna" value="jep" />
		<input class="button" type="submit" value="Lisää työ" />
	</form>
	<footer>
		Tehnyt Henri A. Juvonen huhtikuussa 2016. Kopiointi sallittu.
	</footer>
</body>
</html>