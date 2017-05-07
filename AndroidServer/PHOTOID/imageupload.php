<?php

$file=base64_decode($_POST["myfile"]);


 $photo = imagecreatefromstring($file);

//create a random name 
$RandomStr = md5(microtime());
$name = substr($RandomStr, 0, 3);
$name .= date('Y-m-d');
$name .= '.jpg';

//assign name and upload your image
imagejpeg($photo, 'files/'.$name, 100);

echo $name;
 ?>