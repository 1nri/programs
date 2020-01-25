<?php
function laske_alv($hinta, $alv = 23)
{
	$alv_osuus = $hinta * $alv/100;
	return $alv_osuus;
}

$verollinenhinta = 100 + laske_alv(100, 14);
echo $verollinenhinta;
?>
