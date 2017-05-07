<?php


$conn=new mysqli("localhost","root","","bpp");
if($conn->connect_error)
{
		die($conn->connect_error);
}




//IMAGE UPLOADED PART

$path="files/";
$upload_image=$_POST["imagename"];

$stringinuse = "python oriental/test_images.py ".$path.$upload_image;
//echo $stringinuse;

$command = escapeshellcmd($stringinuse);
$output = shell_exec($command);
echo $output;

//SEARCH BY AREA

if($_POST["area"]!="")
{
		$sql="select * from birdspecies NATURAL JOIN location where area='".$_POST["area"]."'";
		$result=$conn->query($sql);

		if($result->num_rows > 0)
		{
			while($row = $result->fetch_assoc())
			{
				echo strtolower($row["primarybirdname"]).":".strtolower($row["area"])."\n";
			}

		}

}

?>
