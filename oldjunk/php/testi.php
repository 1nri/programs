<?php

/**
 * @author henrijuvonen
 * created during the spring of 2016
 *
 * modified 2.2.2020
 * translated comments, started recomposing the structure for further development
 */

function laske_alv($hinta, $alv = 23)
{
	$alv_osuus = $hinta * $alv/100;
	return $alv_osuus;
}

$verollinenhinta = 100 + laske_alv(100, 14);
echo $verollinenhinta;
?>
