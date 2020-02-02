<html>

/**
 * @author henrijuvonen
 * created during the spring of 2016
 *
 * modified 2.2.2020
 * translated comments, started recomposing the structure for further development
 */

<body>
<?php

/*
<?php
$string = 'The quick brown fox jumps over the lazy dog.';
$patterns = array();
$patterns[0] = '/quick/';
$patterns[1] = '/brown/';
$patterns[2] = '/fox/';
$replacements = array();
$replacements[2] = 'bear';
$replacements[1] = 'black';
$replacements[0] = 'slow';
echo preg_replace($patterns, $replacements, $string);
?>
*/

//$itemList[] = preg_replace('/-.*/i', "", $temp['item-test']);
//if($item = strval('test-item'))
//{
	$item = strval('test-item');
	echo $item;
	if($itemList = preg_replace('/i/', "", $item))
	{
		echo "<br />";
		$itemList = preg_replace('/i/', "", $item);
		echo $itemList;
	}
	else
		echo 'Ei pelita tama';
//}
//else
	//echo 'Ei pelita tamakaan';

echo "<br />";
echo "<select><option>auto</option><select>";

?>
</body>
</html>
