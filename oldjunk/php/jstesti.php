<HTML>
<HEAD>
<meta charset="UTF-8" />
    <TITLE> Add/Remove dynamic rows in HTML table </TITLE>
    <SCRIPT language="javascript">
        function addRow(tableID)
        {

            var table = document.getElementById(tableID);

            var rowCount = table.rows.length;
            var row = table.insertRow(rowCount);

            var cell1 = row.insertCell(0);
            var element1 = document.createElement("input");
            element1.type = "checkbox";
            element1.name="chkbox[]";
            cell1.appendChild(element1);

            /*var cell2 = row.insertCell(1);
            cell2.innerHTML = rowCount + 1;

            var cell3 = row.insertCell(2);
            var element2 = document.createElement("input");
            element3.type = "text";
            element3.name = "count[]";
            cell3.appendChild(element3);
			*/
			var cell2 = row.insertCell(1);
            var element2 = document.createElement("input");
            element2.type = "text";
            element2.name = "txtbox[]";
			element2.value = "";
            //element2.content = "<?php echo 'jep'; ?>";
            cell2.appendChild(element2);
            
            var cell3 = row.insertCell(2);
            var element3 = document.createElement("input");
            element3.type = "text";
            element3.name = "count[]";
            element3.value = "";
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

    </SCRIPT>
</HEAD>
<BODY>


 <form action="" method="post">
 <?php $i= 1; ?>
    <TABLE id="dataTable" border="1">
        <TR>
			<TD><INPUT type="checkbox" name="chkbox[]"/>
			</TD>
			<TD> 
				<INPUT type="text" name="txtbox[]" value="" /> 
			</td>
			<td>
				<INPUT type="text" name="count[]" value="" />
			</TD>
		</TR>
    </TABLE>
    <input name="saveNewSales" type="submit" value="Tallenna" id="button2" style="text-align:center"/>
 </form>
 <INPUT type="button" value="Lisää rivi" onClick="addRow('dataTable')" />
 <INPUT type="button" value="Poista valitsemasi rivi(t)" onClick="deleteRow('dataTable')" />

 <p text-size=0.6em>Valitse rivi vain kun haluat poistaa sen.</p>
 <?php

/*
foreach($_POST as $name => $content) 
{ // Most people refer to $key => $value
   echo "The HTML name: $name <br />";
   echo "The content of it: $content <br />";
   
}
*/
//$array_txt[] = null;
//$array_count[] = null;
foreach($_POST['txtbox'] as $value)
{
	// tarvikkeet taulukoksi
	$array_txt[] = strval($value);
	echo "arvo : $value <br />";
}
foreach($_POST['count'] as $c)
{
	//tarvikkeiden lukumäärä taulukkoon
	$array_count[] = intval($c);
	echo "lukema : $c <br />";
}
if($array_txt[0] != '')
{
	foreach($array_txt as $txt)
	{
		echo "arvo : $txt <br />";
		
		
	}
	$j = 0;
	foreach($array_count as $cnt)
	{
		$i = 0;
		
		echo "$cnt testi $array_txt[$j] <br />";
		while($i < $cnt)
		{
			$i = $i + 1;
			echo $i;
		}
		echo "<br />";
		$j = $j + 1;
	}
	echo "done <br />";
}
		/*while($i <= $c)
		{
			echo "$i <br />";
			$i = $i+1;
		}
	}
	echo "$c <br />";
	unset($c);
	echo "next item <br />";
	*/
//}
?>

</BODY>
</HTML>