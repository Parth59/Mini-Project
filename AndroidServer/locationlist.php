<?php


$conn=new mysqli("localhost","root","","bpp");
if($conn->connect_error)
{
		die($conn->connect_error);
}

$sql="select distinct(area) from location";
		
$result=$conn->query($sql);

	if($result->num_rows > 0)
		{
			while($row = $result->fetch_assoc())
			{
				echo $row["area"]."\n";
			}

		}

?>